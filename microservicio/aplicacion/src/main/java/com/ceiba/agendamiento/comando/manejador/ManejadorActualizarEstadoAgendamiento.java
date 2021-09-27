package com.ceiba.agendamiento.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.agendamiento.comando.ComandoActualizarEstadoAgendamiento;
import com.ceiba.agendamiento.modelo.dto.EstadoAgendamientoDto;
import com.ceiba.agendamiento.servicio.ServicioActualizarEstadoAgendamiento;
import com.ceiba.manejador.ManejadorComandoRespuesta;

import org.springframework.stereotype.Component;

@Component
public class ManejadorActualizarEstadoAgendamiento implements ManejadorComandoRespuesta<ComandoActualizarEstadoAgendamiento, ComandoRespuesta<EstadoAgendamientoDto>>{

    private final ServicioActualizarEstadoAgendamiento actualizadorEstado;
    
    public ManejadorActualizarEstadoAgendamiento(ServicioActualizarEstadoAgendamiento actualizarEstado) {
        this.actualizadorEstado = actualizarEstado;
    }

    @Override
    public ComandoRespuesta<EstadoAgendamientoDto> ejecutar(ComandoActualizarEstadoAgendamiento comando) {
        return new ComandoRespuesta<>(actualizadorEstado.ejecutar(comando.getAgendamientoId(), comando.getEstado()));
    }
    
}
