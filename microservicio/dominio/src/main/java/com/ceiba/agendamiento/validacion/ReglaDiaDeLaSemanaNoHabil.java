package com.ceiba.agendamiento.validacion;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import com.ceiba.agendamiento.excepcion.ExcepcionFechaAgendamientoNoValida;

public class ReglaDiaDeLaSemanaNoHabil implements ReglaAgendamiento {
    private final DayOfWeek diaDeLaSemana;

    public ReglaDiaDeLaSemanaNoHabil(DayOfWeek diaDeLaSemana) {
        this.diaDeLaSemana = diaDeLaSemana;
    }

    @Override
    public ValidacionRegla validar(LocalDateTime fecha) {
        if (diaDeLaSemana.equals(fecha.getDayOfWeek())) {
            return ValidacionRegla.invalida(ExcepcionFechaAgendamientoNoValida::diaNoHabil);
        }

        return ValidacionRegla.valida();
    }
}