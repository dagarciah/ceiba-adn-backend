package com.ceiba.agendamiento.puerto.dao;

import com.ceiba.agendamiento.modelo.dto.AgendamientoDto;

import java.util.List;

public interface DaoAgendamiento {
    /**
     * Lista todos los agendamientos
     * @return Un lista de agendamientos
     */
    List<AgendamientoDto> listar(List<String> estados);

    /**
     * Obtiene el detalle de agendamiento por codigo dado
     * @param codigo - El codigo unico de agendamiento
     * @return Una instancia de {@link AgendamientoDto}
     */
    AgendamientoDto detalle(String codigo);
}
