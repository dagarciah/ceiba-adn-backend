package com.ceiba.agendamiento.servicio;

import com.ceiba.agendamiento.modelo.dto.AgendamientoDto;
import com.ceiba.agendamiento.modelo.entidad.Agendamiento;
import com.ceiba.agendamiento.modelo.entidad.AgendamientoSequencia;
import com.ceiba.agendamiento.modelo.entidad.SolicitudAgendamiento;
import com.ceiba.agendamiento.puerto.repositorio.RepositorioAgendamiento;

public class ServicioCrearAgendamiento {
    private final RepositorioAgendamiento repositorio;

    public ServicioCrearAgendamiento(RepositorioAgendamiento repositorio) {
        this.repositorio = repositorio;
    }

    public AgendamientoDto ejecutar(SolicitudAgendamiento solicitud) {
        Agendamiento agendamiento = Agendamiento.nuevo(AgendamientoSequencia.siguiente(), solicitud.getDesayunoId(), solicitud.getFecha());
        return repositorio.crear(agendamiento).aDto();
    }
}
