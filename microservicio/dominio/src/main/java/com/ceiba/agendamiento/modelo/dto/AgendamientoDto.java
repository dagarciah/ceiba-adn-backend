package com.ceiba.agendamiento.modelo.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
public class AgendamientoDto {
    private Long id;
    private Long desayunoId;
    private String codigo;
    private LocalDateTime programacion;
    private List<EstadoAgendamientoDto> estados;
    private EstadoAgendamientoDto estadoActual;
}
