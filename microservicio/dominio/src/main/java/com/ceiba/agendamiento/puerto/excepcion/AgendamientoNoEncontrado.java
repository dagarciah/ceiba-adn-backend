package com.ceiba.agendamiento.puerto.excepcion;

public class AgendamientoNoEncontrado extends RuntimeException {

    public AgendamientoNoEncontrado(String codigo) {
        super("Agendamiento con codigo " + codigo + " no encontrado.");
    }

}
