package com.ceiba.agendamiento.validacion;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.ceiba.agendamiento.excepcion.ExcepcionFechaAgendamientoNoValida;
import com.ceiba.dominio.ValidadorArgumento;

public class ReglaDiaFeriado implements ReglaAgendamiento {
    private final LocalDate diaFeriado;

    public ReglaDiaFeriado(LocalDate diaFeriado) {
        ValidadorArgumento.validarObligatorio(diaFeriado, "El la fecha del dia feriado es obligatoria.");
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