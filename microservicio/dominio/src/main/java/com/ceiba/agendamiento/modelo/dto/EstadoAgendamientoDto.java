package com.ceiba.agendamiento.modelo.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class EstadoAgendamientoDto {
    private String nombre;
    private LocalDateTime fechaCambio;
}
