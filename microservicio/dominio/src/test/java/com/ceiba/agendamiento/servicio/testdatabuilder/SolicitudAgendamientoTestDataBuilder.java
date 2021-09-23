package com.ceiba.agendamiento.servicio.testdatabuilder;

import java.time.LocalDateTime;

import com.ceiba.agendamiento.modelo.entidad.SolicitudAgendamiento;
import com.ceiba.desayuno.modelo.entidad.DesayunoId;

public class SolicitudAgendamientoTestDataBuilder {

    private DesayunoId desayunoId;
    private LocalDateTime fecha;

    private SolicitudAgendamientoTestDataBuilder() {
        this.desayunoId = DesayunoId.de(1L);
        this.fecha = LocalDateTime.now();
    }

    public static SolicitudAgendamientoTestDataBuilder defecto() {
        return new SolicitudAgendamientoTestDataBuilder();
    }

    public SolicitudAgendamientoTestDataBuilder conDesayunoId(DesayunoId id) {
        this.desayunoId = id;
        return this;
    }

    public SolicitudAgendamientoTestDataBuilder conFecha(LocalDateTime fecha) {
        this.fecha = fecha;
        return this;
    }

    public SolicitudAgendamiento build() {
        return new SolicitudAgendamiento(desayunoId, fecha);
    }
}