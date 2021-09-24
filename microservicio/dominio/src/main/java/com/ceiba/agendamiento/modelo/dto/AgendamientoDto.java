package com.ceiba.agendamiento.modelo.dto;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AgendamientoDto {
    private Long id;
    private Long desayunoId;
    private String codigo;
    private LocalDateTime programacion;
    private List<EstadoAgendamientoDto> estados;
    private EstadoAgendamientoDto estadoActual;

    @Builder(toBuilder = true)
    public AgendamientoDto(Long id, Long desayunoId, String codigo, LocalDateTime programacion,
            List<EstadoAgendamientoDto> estados, EstadoAgendamientoDto estadoActual) {

        validarObligatorio(id, "El identificador no puede ser nulo");

        this.id = id;
        this.desayunoId = desayunoId;
        this.codigo = codigo;
        this.programacion = programacion;
        this.estados = new ArrayList<>(estados);
        this.estadoActual = estadoActual;
    }

}
