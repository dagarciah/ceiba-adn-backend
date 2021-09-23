package com.ceiba.agendamiento.excepcion;

import com.ceiba.agendamiento.modelo.entidad.EstadoAgendamiento;

public class ExcepcionEstadoAgendamientoNoValido extends RuntimeException {

    public ExcepcionEstadoAgendamientoNoValido(Long agendamientoId, EstadoAgendamiento esperado) {
        super(String.format("El agendamiento [%d] no puede pasar a estado \"%s\"", agendamientoId, esperado.name()));
    }
    
}
