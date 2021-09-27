package com.ceiba.descuento.servicio.testdatabuilder;

import com.ceiba.descuento.comando.ComandoCrearDescuento;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class ComandoCrearDescuentoTestDataBuilder {

    private final String etiqueta;
    private final BigDecimal porcentaje;
    private final LocalDate fechaInicio;
    private final LocalDate fechaFin;
    private final List<Long> desayunos;

    public ComandoCrearDescuentoTestDataBuilder() {
        etiqueta = "Etiqueta";
        porcentaje = BigDecimal.TEN;
        fechaInicio = LocalDate.now();
        fechaFin = fechaInicio.plusWeeks(1);
        desayunos = Collections.singletonList(1L);
    }

    public ComandoCrearDescuento build() {
        return ComandoCrearDescuento.builder()
                .etiqueta(etiqueta)
                .porcentaje(porcentaje)
                .fechaInicio(fechaInicio)
                .fechaFin(fechaFin)
                .desayunos(desayunos)
                .build();
    }
}
