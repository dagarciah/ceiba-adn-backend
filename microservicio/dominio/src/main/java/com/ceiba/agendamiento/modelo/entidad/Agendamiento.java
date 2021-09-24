package com.ceiba.agendamiento.modelo.entidad;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

import com.ceiba.agendamiento.excepcion.ExcepcionEstadoAgendamientoNoValido;
import com.ceiba.desayuno.modelo.entidad.DesayunoId;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Agendamiento {
    private Long id;
    private String codigo;
    private Long desayunoId;
    private LocalDateTime programacion;
    private HistoricoEstadoAgendamiento historico;

    @Builder(toBuilder = true)
    public Agendamiento(Long id, String codigo, Long desayunoId, LocalDateTime programacion,
            HistoricoEstadoAgendamiento historico) {
        validarObligatorio(codigo, "El codigo unico de agendamiento es obligatorio");
        validarObligatorio(desayunoId, "El identificador unico de desayuno es obligatorio");
        validarObligatorio(programacion, "La fecha de programacion de entrega es obligatoria");

        this.id = id;
        this.codigo = codigo;
        this.desayunoId = desayunoId;
        this.programacion = programacion;
        this.historico = Optional.ofNullable(historico).orElseGet(HistoricoEstadoAgendamiento::new);
    }

    public static Agendamiento nuevo(String codigo, DesayunoId desayunoId, LocalDateTime programacion) {
        return new Agendamiento(null, codigo, desayunoId.getValor(), programacion, null);
    }

    public EstadoAgendamiento siguiente(FlujoEstadoAgendamiento nuevoEstado) {
        EstadoAgendamiento estadoActual = historico.getActual();
        
        if (Objects.equals(FlujoEstadoAgendamiento.CANCELADO, nuevoEstado)) {
            if (!estadoActual.esCancelable()) {
                throw new ExcepcionEstadoAgendamientoNoValido(id, FlujoEstadoAgendamiento.CANCELADO);
            }

            estadoActual = new EstadoAgendamiento(null, id, LocalDateTime.now(), nuevoEstado);
            this.historico.agregar(estadoActual);
        } else {
            if (!Objects.equals(estadoActual.siguiente(), nuevoEstado)) {
                throw new ExcepcionEstadoAgendamientoNoValido(id, nuevoEstado);
            }

            if (!(estadoActual.esIgual(estadoActual.siguiente()))) {
                estadoActual = new EstadoAgendamiento(null, id, LocalDateTime.now(),
                        estadoActual.siguiente());
                this.historico.agregar(estadoActual);
            }
        }

        return estadoActual;
    }

    public EstadoAgendamiento getEstadoActual() {
        return historico.getActual();
    }

}
