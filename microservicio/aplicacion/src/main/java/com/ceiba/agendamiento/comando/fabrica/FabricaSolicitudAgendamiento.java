package com.ceiba.agendamiento.comando.fabrica;

import java.time.LocalDateTime;

import com.ceiba.agendamiento.comando.ComandoSolicitudAgendamiento;
import com.ceiba.agendamiento.modelo.entidad.SolicitudAgendamiento;
import com.ceiba.agendamiento.servicio.ServicioValidadorFechaAgendamiento;
import com.ceiba.desayuno.modelo.entidad.DesayunoId;

import org.springframework.stereotype.Component;

@Component
public class FabricaSolicitudAgendamiento {
    
    private final ServicioValidadorFechaAgendamiento validadorFechaAgendamiento;

    public FabricaSolicitudAgendamiento(ServicioValidadorFechaAgendamiento validadorFechaAgendamiento) {
        this.validadorFechaAgendamiento = validadorFechaAgendamiento;
    }

    public SolicitudAgendamiento crear(ComandoSolicitudAgendamiento comando) {
        validadorFechaAgendamiento.validar(LocalDateTime.now(), comando.getFecha());
        return new SolicitudAgendamiento(DesayunoId.de(comando.getDesayuno()), comando.getFecha());
    }
}
