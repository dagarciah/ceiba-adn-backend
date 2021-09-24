package com.ceiba.agendamiento.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.agendamiento.comando.ComandoSolicitudAgendamiento;
import com.ceiba.agendamiento.comando.fabrica.FabricaSolicitudAgendamiento;
import com.ceiba.agendamiento.modelo.entidad.ResultadoAgendamiento;
import com.ceiba.agendamiento.modelo.entidad.SolicitudAgendamiento;
import com.ceiba.agendamiento.servicio.ServicioCrearAgendamiento;
import com.ceiba.manejador.ManejadorComandoRespuesta;

import org.springframework.stereotype.Component;

@Component
public class ManejadorCrearAgendamiento
        implements ManejadorComandoRespuesta<ComandoSolicitudAgendamiento, ComandoRespuesta<ResultadoAgendamiento>> {

    private final ServicioCrearAgendamiento servicio;
    private final FabricaSolicitudAgendamiento fabrica;

    public ManejadorCrearAgendamiento(ServicioCrearAgendamiento servicio, FabricaSolicitudAgendamiento fabrica) {
        this.servicio = servicio;
        this.fabrica = fabrica;
    }

    @Override
    public ComandoRespuesta<ResultadoAgendamiento> ejecutar(ComandoSolicitudAgendamiento comando) {
        SolicitudAgendamiento solicitud = fabrica.crear(comando);
        return new ComandoRespuesta<>(servicio.ejecutar(solicitud));
    }

}
