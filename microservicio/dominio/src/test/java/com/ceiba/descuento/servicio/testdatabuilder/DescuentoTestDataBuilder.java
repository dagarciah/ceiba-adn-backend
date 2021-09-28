package com.ceiba.descuento.servicio.testdatabuilder;

import com.ceiba.desayuno.modelo.entidad.DesayunoId;
import com.ceiba.descuento.modelo.entidad.Descuento;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class DescuentoTestDataBuilder {

    private final BigDecimal porcentaje;
    private final LocalDate fechaInicio;
    private final LocalDate fechaFin;
    private final List<DesayunoId> desayunos;
    private final String etiqueta;
    private Long id;

    public DescuentoTestDataBuilder() {
        etiqueta = "Dia de madres";
        porcentaje = BigDecimal.TEN;
        fechaInicio = LocalDate.now();
        fechaFin = fechaInicio.plusWeeks(1);
        desayunos = Collections.singletonList(DesayunoId.de(1L));
    }

    public Descuento build() {
        return Descuento.builder()
                .id(id)
                .etiqueta(etiqueta)
                .porcentaje(porcentaje)
                .fechaInicio(fechaInicio)
                .fechaFin(fechaFin)
                .desayunos(desayunos)
                .build();
    }

    public DescuentoTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }
}
