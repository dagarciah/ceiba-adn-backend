package com.ceiba.agendamiento.modelo.entidad;

import java.util.UUID;

public interface AgendamientoSequencia {
    static String siguiente() {
        return UUID.randomUUID().toString().split("-")[0].toUpperCase();
    }
}
