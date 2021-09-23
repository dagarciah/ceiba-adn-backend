package com.ceiba.agendamiento.comando;

import com.ceiba.agendamiento.modelo.entidad.EstadoAgendamiento;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class ComandoActualizarEstadoAgendamiento {
    private final EstadoAgendamiento estado;
    private final Long agendamientoId;
}
