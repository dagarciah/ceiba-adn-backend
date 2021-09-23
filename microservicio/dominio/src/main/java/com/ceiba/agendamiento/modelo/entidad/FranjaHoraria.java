package com.ceiba.agendamiento.modelo.entidad;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.ceiba.dominio.ValidadorArgumento;

public final class FranjaHoraria {

    private static final DateTimeFormatter FORMATEADOR_TIEMPO = DateTimeFormatter.ofPattern("hh:mm a");

    private final LocalTime horaInicio;
    private final LocalTime horaFin;

    private FranjaHoraria(LocalTime horaInicio, LocalTime horaFin) {
        ValidadorArgumento.validarMenor(horaInicio, horaFin, "La hora inicio de franja debe ser menor a la hora fin.");

        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    public static FranjaHoraria con(int horaInicial, int minutoInicial, int horaFinal, int minutoFinal) {
        return new FranjaHoraria(LocalTime.of(horaInicial, minutoInicial), LocalTime.of(horaFinal, minutoFinal));
    }

    public static FranjaHoraria con(LocalTime horaInicio, LocalTime horaFin) {
        return new FranjaHoraria(horaInicio, horaFin);
    }

    public boolean esFueraFranja(LocalTime hora) {
        return hora.isBefore(horaInicio) || hora.isAfter(horaFin);
    }

    @Override
    public String toString() {
        return String.format("%s - %s", FORMATEADOR_TIEMPO.format(horaInicio), FORMATEADOR_TIEMPO.format(horaFin));
    }


}
