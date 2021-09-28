package com.ceiba.descuento.controlador;

import com.ceiba.descuento.consulta.ManejadorEncontrarDescuento;
import com.ceiba.descuento.consulta.ManejadorListarDescuento;
import com.ceiba.descuento.modelo.entidad.Descuento;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/descuento")
@Api(tags = {"Controlador consulta descuento"})
public class ConsultaControladorDescuento {
    private final ManejadorListarDescuento manejadorListarDescuento;
    private final ManejadorEncontrarDescuento manejadorEncontrarDescuento;

    public ConsultaControladorDescuento(ManejadorListarDescuento manejadorListarDescuento, ManejadorEncontrarDescuento manejadorEncontrarDescuento) {
        this.manejadorListarDescuento = manejadorListarDescuento;
        this.manejadorEncontrarDescuento = manejadorEncontrarDescuento;
    }

    @GetMapping
    @ApiOperation("Listar Descuentos")
    public List<Descuento> listar() {
        return manejadorListarDescuento.ejecutar();
    }

    @GetMapping("{id}")
    @ApiOperation("Encuentra un descuento especifico")
    public Descuento encontrar(@PathVariable("id") Long id) {
        return manejadorEncontrarDescuento.ejecutar(id);
    }
}
