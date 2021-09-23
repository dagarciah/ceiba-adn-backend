package com.ceiba.agendamiento.puerto.dao;

import java.util.List;

import com.ceiba.agendamiento.validacion.ReglaAgendamiento;

public interface DaoReglaAgendamiento {
    /**
     * Permite listar las reglas de agendamiento activas
     * @return una lista de reglas
     */
    List<ReglaAgendamiento> listar();
}
