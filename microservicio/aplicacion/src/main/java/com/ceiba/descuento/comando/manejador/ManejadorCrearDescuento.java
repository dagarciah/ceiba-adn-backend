package com.ceiba.descuento.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.descuento.comando.ComandoCrearDescuento;
import com.ceiba.descuento.comando.fabrica.FabricaDescuento;
import com.ceiba.descuento.modelo.entidad.Descuento;
import com.ceiba.descuento.servicio.ServicioCrearDescuento;
import org.springframework.stereotype.Component;

@Component
public class ManejadorCrearDescuento {
    private final FabricaDescuento fabrica;
    private final ServicioCrearDescuento servicio;

    public ManejadorCrearDescuento(FabricaDescuento fabrica, ServicioCrearDescuento servicio) {
        this.fabrica = fabrica;
        this.servicio = servicio;
    }

    public ComandoRespuesta<Long> ejecutar(ComandoCrearDescuento comando) {
        Descuento descuento = fabrica.crear(comando);
        return new ComandoRespuesta<>(servicio.ejecutar(descuento));
    }
}
