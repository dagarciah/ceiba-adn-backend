package com.ceiba.agendamiento.excepcion;

import com.ceiba.agendamiento.modelo.entidad.FlujoEstadoAgendamiento;

public class ExcepcionEstadoAgendamientoNoValido extends RuntimeException {

    public ExcepcionEstadoAgendamientoNoValido(Long agendamientoId, FlujoEstadoAgendamiento esperado) {
        super(String.format("El agendamiento [%d] no puede pasar a estado \"%s\"", agendamientoId, esperado.name()));
    }
    
}
