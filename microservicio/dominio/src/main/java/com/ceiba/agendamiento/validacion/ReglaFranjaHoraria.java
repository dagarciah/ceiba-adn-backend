package com.ceiba.agendamiento.validacion;

import java.time.LocalDateTime;

import com.ceiba.agendamiento.excepcion.ExcepcionFechaAgendamientoNoValida;
import com.ceiba.agendamiento.modelo.entidad.FranjaHoraria;

public class ReglaFranjaHoraria implements ReglaAgendamiento {
    private final FranjaHoraria franja;

    public ReglaFranjaHoraria(FranjaHoraria franja) {
        this.franja = franja;
    }

    @Override
    public ValidacionRegla validar(LocalDateTime fecha) {
        if (franja.esFueraFranja(fecha.toLocalTime())) {
            return ValidacionRegla.invalida(() -> ExcepcionFechaAgendamientoNoValida.fueraFranja(franja));
        }

        return ValidacionRegla.valida();
    }

}