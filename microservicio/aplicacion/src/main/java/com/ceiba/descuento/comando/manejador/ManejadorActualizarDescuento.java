package com.ceiba.descuento.comando.manejador;

import com.ceiba.descuento.comando.ComandoDescuento;
import com.ceiba.descuento.comando.fabrica.FabricaDescuento;
import com.ceiba.descuento.modelo.entidad.Descuento;
import com.ceiba.descuento.servicio.ServicioActualizarDescuento;
import org.springframework.stereotype.Component;

@Component
public class ManejadorActualizarDescuento {
    private final FabricaDescuento fabrica;
    private final ServicioActualizarDescuento servicio;

    public ManejadorActualizarDescuento(FabricaDescuento fabrica, ServicioActualizarDescuento servicio) {
        this.fabrica = fabrica;
        this.servicio = servicio;
    }

    public void ejecutar(ComandoDescuento comando) {
        Descuento descuento = fabrica.crear(comando);
        servicio.ejecutar(descuento);
    }
}
