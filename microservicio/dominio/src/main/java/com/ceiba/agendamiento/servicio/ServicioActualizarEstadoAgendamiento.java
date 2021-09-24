package com.ceiba.agendamiento.servicio;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;

import com.ceiba.agendamiento.modelo.dto.EstadoAgendamientoDto;
import com.ceiba.agendamiento.modelo.entidad.Agendamiento;
import com.ceiba.agendamiento.modelo.entidad.FlujoEstadoAgendamiento;
import com.ceiba.agendamiento.modelo.entidad.EstadoAgendamiento;
import com.ceiba.agendamiento.puerto.repositorio.RepositorioAgendamiento;

public class ServicioActualizarEstadoAgendamiento {

    private final RepositorioAgendamiento repositorio;

    public ServicioActualizarEstadoAgendamiento(RepositorioAgendamiento repositorio) {
        this.repositorio = repositorio;
    }

    public EstadoAgendamientoDto ejecutar(Long agendamientoId, FlujoEstadoAgendamiento estado) {
        validarObligatorio(agendamientoId, "El identificador unico de agendamiento es obligatorio.");
        validarObligatorio(estado, "El estado a cambiar el agendamiento es obligatorio");

        Agendamiento agendamiento = repositorio.encontrar(agendamientoId);
        EstadoAgendamiento estadoActual = agendamiento.siguiente(estado);
                
        repositorio.actualizar(agendamiento);

        return EstadoAgendamientoDto.builder()
                .nombre(estadoActual.getNombre())
                .fechaCambio(estadoActual.getCreacion())
                .build();
    }
    
}
