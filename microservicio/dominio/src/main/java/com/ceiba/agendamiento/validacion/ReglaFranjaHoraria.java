package com.ceiba.agendamiento.validacion;

import java.time.LocalDateTime;

import com.ceiba.agendamiento.excepcion.ExcepcionFechaAgendamientoNoValida;
import com.ceiba.agendamiento.modelo.entidad.FranjaHoraria;
import com.ceiba.dominio.ValidadorArgumento;

public class ReglaFranjaHoraria implements ReglaAgendamiento {
    private final FranjaHoraria franja;

    public ReglaFranjaHoraria(FranjaHoraria franja) {
        ValidadorArgumento.validarObligatorio(franja, "La franja horaria es obligatoria.");
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