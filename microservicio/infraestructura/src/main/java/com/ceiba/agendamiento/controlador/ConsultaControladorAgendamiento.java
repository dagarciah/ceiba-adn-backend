package com.ceiba.agendamiento.controlador;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.ceiba.agendamiento.consulta.ManejadorDetalleAgendamiento;
import com.ceiba.agendamiento.consulta.ManejadorListarAgendamiento;
import com.ceiba.agendamiento.modelo.dto.AgendamientoDto;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/agendamiento")
@Api(tags = { "Controlador consulta agendamiento" })
public class ConsultaControladorAgendamiento {
    private final ManejadorListarAgendamiento manejadorListarAgendamiento;
    private final ManejadorDetalleAgendamiento manejadoDetalleAgendamiento;

    public ConsultaControladorAgendamiento(ManejadorListarAgendamiento manejadorListarAgendamiento,
            ManejadorDetalleAgendamiento manejadoDetalleAgendamiento) {
        this.manejadorListarAgendamiento = manejadorListarAgendamiento;
        this.manejadoDetalleAgendamiento = manejadoDetalleAgendamiento;
    }

    @GetMapping
    @ApiOperation("Consulta los agendamientos")
    public List<AgendamientoDto> listar(@RequestParam(required = false, value = "estado") List<String> estados) {
        return manejadorListarAgendamiento.ejecutar(Optional.ofNullable(estados).orElseGet(Collections::emptyList));
    }

    @GetMapping("/{codigo}")
    @ApiOperation("Consulta el detalle de un agendamiento")
    public AgendamientoDto listar(@PathVariable("codigo") String codigo) {
        return manejadoDetalleAgendamiento.ejecutar(codigo);
    }
}
