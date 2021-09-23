package com.ceiba.agendamiento.modelo.entidad;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.ceiba.agendamiento.excepcion.ExcepcionEstadoAgendamientoNoValido;
import com.ceiba.agendamiento.modelo.dto.AgendamientoDto;
import com.ceiba.agendamiento.modelo.dto.EstadoAgendamientoDto;
import com.ceiba.desayuno.modelo.entidad.DesayunoId;
import com.ceiba.dominio.ValidadorArgumento;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Agendamiento {
    private Long id;
    private String codigo;
    private Long desayunoId;
    private LocalDateTime programacion;
    private EstadoAgendamientoHistorico estadoActual;
    private List<EstadoAgendamientoHistorico> estados = new ArrayList<>();

    @Builder
    public Agendamiento(Long id, String codigo, Long desayunoId, LocalDateTime programacion,
            List<EstadoAgendamientoHistorico> estados) {
        ValidadorArgumento.validarObligatorio(codigo, "El codigo unico de agendamiento es obligatorio");
        ValidadorArgumento.validarObligatorio(desayunoId, "El identificador unico de desayuno es obligatorio");
        ValidadorArgumento.validarObligatorio(programacion, "La fecha de programacion de entrega es obligatoria");

        this.id = id;
        this.codigo = codigo;
        this.desayunoId = desayunoId;
        this.programacion = programacion;

        if (Objects.nonNull(estados)) {
            this.estados.addAll(estados);
            this.estadoActual = this.estados.stream().max(Comparator.comparing(EstadoAgendamientoHistorico::getCreacion))
                    .orElse(null);
        }
    }

    public static Agendamiento nuevo(String codigo, DesayunoId desayunoId, LocalDateTime programacion) {
        return new Agendamiento(null, codigo, desayunoId.getValor(), programacion, null);
    }

    public EstadoAgendamientoHistorico siguiente(EstadoAgendamiento nuevoEstado) {
        if (Objects.equals(EstadoAgendamiento.Cancelado, nuevoEstado)) {
            if (!this.estadoActual.esCancelable()) {
                throw new ExcepcionEstadoAgendamientoNoValido(id, EstadoAgendamiento.Cancelado);
            }
    
            this.estadoActual = new EstadoAgendamientoHistorico(null, id, LocalDateTime.now(), nuevoEstado);
            this.estados.add(this.estadoActual);
        } else {
            if(!Objects.equals(this.estadoActual.siguiente(), nuevoEstado)) {
                throw new ExcepcionEstadoAgendamientoNoValido(id, nuevoEstado);
            }

            if (!(this.estadoActual.esIgual(this.estadoActual.siguiente()))) {
                this.estadoActual = new EstadoAgendamientoHistorico(null, id, LocalDateTime.now(), this.estadoActual.siguiente());
                this.estados.add(this.estadoActual);
            }
        }

        return estadoActual;
    }

    public AgendamientoDto aDto() {
        List<EstadoAgendamientoDto> estados = this.estados.stream()
                .sorted(Comparator.comparing(EstadoAgendamientoHistorico::getCreacion))
                .map(e -> EstadoAgendamientoDto.builder().nombre(e.getNombre()).fechaCambio(e.getCreacion()).build())
                .collect(Collectors.toList());

        return AgendamientoDto.builder().id(id).codigo(codigo).programacion(programacion).estados(estados).build();
    }
}
