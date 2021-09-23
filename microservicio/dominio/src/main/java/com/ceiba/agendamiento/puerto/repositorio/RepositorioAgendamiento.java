package com.ceiba.agendamiento.puerto.repositorio;

import com.ceiba.agendamiento.modelo.entidad.Agendamiento;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;

public interface RepositorioAgendamiento {
     /**
     * Permite crear un agendamiento
     * @param usuario
     * @return el id de agendamiento
     */
    Agendamiento crear(Agendamiento agendamiento);

    /**
     * Permite encontrar un agendamiento por identificador
     * @param id - Identificador unico de agendamiento
     * @return una instancia de {@link Agendamiento}
     * @throws ExcepcionSinDatos cuando el agendamiento no existe.
     */
    Agendamiento encontrar(Long id);

    /**
     * Permite actualizar un agendamiento
     * 
     * @param agendamiento
     */
    void actualizar(Agendamiento agendamiento);
}
