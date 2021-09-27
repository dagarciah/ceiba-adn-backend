package com.ceiba.desayuno.controlador;

import com.ceiba.desayuno.consulta.ManejadorDetalleDesayuno;
import com.ceiba.desayuno.consulta.ManejadorListarDesayuno;
import com.ceiba.desayuno.modelo.dto.DesayunoDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/desayuno")
@Api(tags = {"Controlador consulta desayunos"})
public class ConsultaControladorDesayuno {
    private final ManejadorListarDesayuno manejadorListarDesayuno;
    private final ManejadorDetalleDesayuno manejadorDetalleDesayuno;

    public ConsultaControladorDesayuno(ManejadorListarDesayuno manejadorListarDesayuno, ManejadorDetalleDesayuno manejadorDetalleDesayuno) {
        this.manejadorListarDesayuno = manejadorListarDesayuno;
        this.manejadorDetalleDesayuno = manejadorDetalleDesayuno;
    }

    @GetMapping
    @ApiOperation("Consulta los desayunos")
    public List<DesayunoDto> listar(@RequestParam(required = false) Integer offset, @RequestParam(required = false) Integer limit) {
        return manejadorListarDesayuno.ejecutar(
                Optional.ofNullable(offset).orElse(0),
                Optional.ofNullable(limit).orElse(10));
    }

    @GetMapping("/{id}")
    @ApiOperation("Consulta un desayuno por id")
    public DesayunoDto detalle(@PathVariable("id") Long id) {
        return manejadorDetalleDesayuno.ejecutar(id);
    }
}
