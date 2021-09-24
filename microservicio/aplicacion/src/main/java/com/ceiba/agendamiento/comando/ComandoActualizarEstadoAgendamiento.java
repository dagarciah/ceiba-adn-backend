package com.ceiba.agendamiento.comando;

import com.ceiba.agendamiento.modelo.entidad.FlujoEstadoAgendamiento;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class ComandoActualizarEstadoAgendamiento {
    private final FlujoEstadoAgendamiento estado;
    private final Long agendamientoId;
}
