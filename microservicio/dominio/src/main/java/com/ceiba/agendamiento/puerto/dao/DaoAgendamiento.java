package com.ceiba.agendamiento.puerto.dao;

import java.util.List;

import com.ceiba.agendamiento.modelo.dto.AgendamientoDto;
import com.ceiba.agendamiento.modelo.entidad.EstadoAgendamiento;

public interface DaoAgendamiento {
    /**
     * Lista todos los agendamientos
     * @return Un lista de agendamientos
     */
    List<AgendamientoDto> listar(List<EstadoAgendamiento> estados);

    /**
     * Obtiene el detalle de agendamientopor codigo dado
     * @param codigo - El codigo unico de agendamiento
     * @return Una instancia de {@link AgendamientoDto}
     */
    AgendamientoDto detalle(String codigo);
}
