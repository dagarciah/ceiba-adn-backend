package com.ceiba.descuento.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.descuento.comando.ComandoDescuento;
import com.ceiba.descuento.comando.manejador.ManejadorActualizarDescuento;
import com.ceiba.descuento.comando.manejador.ManejadorCrearDescuento;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/descuento")
@Api(tags = {"Controlador comando descuento"})
public class ComandoControladorDescuento {

    private final ManejadorCrearDescuento manejadorCrearDescuento;
    private final ManejadorActualizarDescuento manejadorActualizarDescuento;

    public ComandoControladorDescuento(ManejadorCrearDescuento manejadorCrearDescuento, ManejadorActualizarDescuento manejadorActualizarDescuento) {
        this.manejadorCrearDescuento = manejadorCrearDescuento;
        this.manejadorActualizarDescuento = manejadorActualizarDescuento;
    }

    @PostMapping
    @ApiOperation("Crear descuento")
    public ComandoRespuesta<Long> crear(@RequestBody ComandoDescuento comando) {
        return manejadorCrearDescuento.ejecutar(comando);
    }

    @PutMapping("/{id}")
    @ApiOperation("Actualizar descuento")
    public void actualizar(@PathVariable("id") Long id, @RequestBody ComandoDescuento comando) {
        comando.setId(id);
        manejadorActualizarDescuento.ejecutar(comando);
    }
}
