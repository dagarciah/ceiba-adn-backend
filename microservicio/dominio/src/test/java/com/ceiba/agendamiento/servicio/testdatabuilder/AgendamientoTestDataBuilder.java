package com.ceiba.agendamiento.servicio.testdatabuilder;

import java.time.LocalDateTime;

import com.ceiba.agendamiento.modelo.entidad.Agendamiento;
import com.ceiba.desayuno.modelo.entidad.DesayunoId;

public class AgendamientoTestDataBuilder {

    private DesayunoId desayunoId;
    private LocalDateTime fecha;

    private AgendamientoTestDataBuilder() {
        this.desayunoId = DesayunoId.de(1L);
        this.fecha = LocalDateTime.now();
    }

    public static AgendamientoTestDataBuilder defecto() {
        return new AgendamientoTestDataBuilder();
    }

    public AgendamientoTestDataBuilder conDesayunoId(DesayunoId id) {
        this.desayunoId = id;
        return this;
    }

    public AgendamientoTestDataBuilder conFecha(LocalDateTime fecha) {
        this.fecha = fecha;
        return this;
    }

    public Agendamiento build() {
        return Agendamiento.builder().build();
    }
}