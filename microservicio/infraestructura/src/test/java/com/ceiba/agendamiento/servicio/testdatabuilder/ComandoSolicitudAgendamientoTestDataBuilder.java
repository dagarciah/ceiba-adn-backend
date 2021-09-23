package com.ceiba.agendamiento.servicio.testdatabuilder;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import com.ceiba.agendamiento.comando.ComandoSolicitudAgendamiento;

public class ComandoSolicitudAgendamientoTestDataBuilder {

    private Long desayunoId;
    private LocalDateTime fecha;

    public ComandoSolicitudAgendamientoTestDataBuilder() {
        desayunoId = 1L;
        fecha = LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).plusDays(1);
    }

    public ComandoSolicitudAgendamientoTestDataBuilder conFecha(LocalDateTime fecha) {
        this.fecha = fecha;
        return this;
    }

    public ComandoSolicitudAgendamientoTestDataBuilder conFecha(Long desayunoId) {
        this.desayunoId = desayunoId;
        return this;
    }

    public ComandoSolicitudAgendamiento build() {
        return new ComandoSolicitudAgendamiento(desayunoId, fecha);
    }
}
