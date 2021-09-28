package com.ceiba.descuento.comando.fabrica;

import com.ceiba.desayuno.modelo.entidad.DesayunoId;
import com.ceiba.descuento.comando.ComandoDescuento;
import com.ceiba.descuento.modelo.entidad.Descuento;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class FabricaDescuento {
    public Descuento crear(ComandoDescuento comando) {
        return Descuento.builder()
                .id(comando.getId())
                .etiqueta(comando.getEtiqueta())
                .porcentaje(comando.getPorcentaje())
                .fechaInicio(comando.getFechaInicio())
                .fechaFin(comando.getFechaFin())
                .desayunos(comando.getDesayunos().stream().map(DesayunoId::de).collect(Collectors.toList()))
                .build();
    }
}
