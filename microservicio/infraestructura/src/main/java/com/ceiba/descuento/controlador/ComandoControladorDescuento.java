package com.ceiba.descuento.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.descuento.comando.ComandoCrearDescuento;
import com.ceiba.descuento.comando.manejador.ManejadorCrearDescuento;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/descuento")
@Api(tags = {"Controlador comando descuento"})
public class ComandoControladorDescuento {

    private final ManejadorCrearDescuento manejadorCrearDescuento;

    public ComandoControladorDescuento(ManejadorCrearDescuento manejadorCrearDescuento) {
        this.manejadorCrearDescuento = manejadorCrearDescuento;
    }

    @PostMapping
    @ApiOperation("Crear descuento")
    public ComandoRespuesta<Long> crear(@RequestBody ComandoCrearDescuento comando) {
        return manejadorCrearDescuento.ejecutar(comando);
    }
}
