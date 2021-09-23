package com.ceiba.agendamiento.validacion;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.ceiba.agendamiento.excepcion.ExcepcionFechaAgendamientoNoValida;

public class ReglaDiaFeriado implements ReglaAgendamiento {
    private final LocalDate diaFeriado;

    public ReglaDiaFeriado(LocalDate diaFeriado) {
        this.diaFeriado = diaFeriado;
    }

    @Override
    public ValidacionRegla validar(LocalDateTime fecha) {
        if (diaFeriado.equals(fecha.toLocalDate())) {
            return ValidacionRegla.invalida(ExcepcionFechaAgendamientoNoValida::diaNoHabil);
        }

        return ValidacionRegla.valida();
    }
}