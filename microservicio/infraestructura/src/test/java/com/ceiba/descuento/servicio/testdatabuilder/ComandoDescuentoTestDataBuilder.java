package com.ceiba.descuento.servicio.testdatabuilder;

import com.ceiba.descuento.comando.ComandoDescuento;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class ComandoDescuentoTestDataBuilder {

    private final String etiqueta;
    private final List<Long> desayunos;
    private BigDecimal porcentaje;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public ComandoDescuentoTestDataBuilder() {
        etiqueta = "Etiqueta";
        porcentaje = BigDecimal.TEN;
        fechaInicio = LocalDate.now();
        fechaFin = fechaInicio.plusWeeks(1);
        desayunos = Collections.singletonList(1L);
    }

    public ComandoDescuento build() {
        return ComandoDescuento.builder()
                .etiqueta(etiqueta)
                .porcentaje(porcentaje)
                .fechaInicio(fechaInicio)
                .fechaFin(fechaFin)
                .desayunos(desayunos)
                .build();
    }

    public ComandoDescuentoTestDataBuilder conPorcentaje(BigDecimal porcentaje) {
        this.porcentaje = porcentaje;
        return this;
    }

    public ComandoDescuentoTestDataBuilder conFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
        return this;
    }

    public ComandoDescuentoTestDataBuilder conFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
        return this;
    }
}
