package com.ceiba.agendamiento.puerto.dao;

import java.util.List;

import com.ceiba.agendamiento.modelo.dto.AgendamientoDto;
import com.ceiba.agendamiento.modelo.entidad.FlujoEstadoAgendamiento;

public interface DaoAgendamiento {
    /**
     * Lista todos los agendamientos
     * @return Un lista de agendamientos
     */
    List<AgendamientoDto> listar(List<FlujoEstadoAgendamiento> estados);

    /**
     * Obtiene el detalle de agendamiento por codigo dado
     * @param codigo - El codigo unico de agendamiento
     * @return Una instancia de {@link AgendamientoDto}
     */
    AgendamientoDto detalle(String codigo);
}
