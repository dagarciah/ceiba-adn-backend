package com.ceiba.agendamiento.validacion;

import java.time.LocalDateTime;

public interface ReglaAgendamiento {
    ValidacionRegla validar(LocalDateTime fecha);
}