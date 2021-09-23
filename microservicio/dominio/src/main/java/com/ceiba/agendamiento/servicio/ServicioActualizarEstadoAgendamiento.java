package com.ceiba.agendamiento.servicio;

import com.ceiba.agendamiento.modelo.dto.EstadoAgendamientoDto;
import com.ceiba.agendamiento.modelo.entidad.Agendamiento;
import com.ceiba.agendamiento.modelo.entidad.EstadoAgendamiento;
import com.ceiba.agendamiento.modelo.entidad.EstadoAgendamientoHistorico;
import com.ceiba.agendamiento.puerto.repositorio.RepositorioAgendamiento;

public class ServicioActualizarEstadoAgendamiento {

    private final RepositorioAgendamiento repositorio;

    public ServicioActualizarEstadoAgendamiento(RepositorioAgendamiento repositorio) {
        this.repositorio = repositorio;
    }

    public EstadoAgendamientoDto ejecutar(Long agendamientoId, EstadoAgendamiento estado) {
        Agendamiento agendamiento = repositorio.encontrar(agendamientoId);
        EstadoAgendamientoHistorico estadoActual = agendamiento.siguiente(estado);
                
        repositorio.actualizar(agendamiento);

        return EstadoAgendamientoDto.builder()
                .nombre(estadoActual.getNombre())
                .fechaCambio(estadoActual.getCreacion())
                .build();
    }
    
}
