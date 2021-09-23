package com.ceiba.agendamiento.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.agendamiento.comando.ComandoActualizarEstadoAgendamiento;
import com.ceiba.agendamiento.comando.ComandoSolicitudAgendamiento;
import com.ceiba.agendamiento.comando.manejador.ManejadorActualizarEstadoAgendamiento;
import com.ceiba.agendamiento.comando.manejador.ManejadorCrearAgendamiento;
import com.ceiba.agendamiento.modelo.dto.AgendamientoDto;
import com.ceiba.agendamiento.modelo.dto.EstadoAgendamientoDto;
import com.ceiba.agendamiento.modelo.entidad.EstadoAgendamiento;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/agendamiento")
@Api(tags = { "Controlador comando agendamiento" })
public class ComandoControladorAgendamiento {
    private final ManejadorCrearAgendamiento manejadorCrearAgendamiento;
    private final ManejadorActualizarEstadoAgendamiento manejadorActualizarEstadoAgendamiento;

    public ComandoControladorAgendamiento(ManejadorCrearAgendamiento manejadorCrearAgendamiento,
            ManejadorActualizarEstadoAgendamiento manejadorActualizarEstadoAgendamiento) {
        this.manejadorCrearAgendamiento = manejadorCrearAgendamiento;
        this.manejadorActualizarEstadoAgendamiento = manejadorActualizarEstadoAgendamiento;
    }

    @PostMapping
    @ApiOperation("Crear Agendamiento")
    public ComandoRespuesta<AgendamientoDto> crear(@RequestBody ComandoSolicitudAgendamiento comando) {
        return manejadorCrearAgendamiento.ejecutar(comando);
    }

    @PatchMapping("/{id}")
    @ApiOperation("Actualiza el estado de un agendamiento")
    public ComandoRespuesta<EstadoAgendamientoDto> actualizarEstado(@PathVariable("id") Long agendamientoId,
            @RequestParam("estado") EstadoAgendamiento estado) {
        return manejadorActualizarEstadoAgendamiento.ejecutar(
                ComandoActualizarEstadoAgendamiento.builder().agendamientoId(agendamientoId).estado(estado).build());
    }
}
