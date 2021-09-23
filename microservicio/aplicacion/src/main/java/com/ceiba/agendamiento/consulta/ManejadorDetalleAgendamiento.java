package com.ceiba.agendamiento.consulta;

import com.ceiba.agendamiento.modelo.dto.AgendamientoDto;
import com.ceiba.agendamiento.puerto.dao.DaoAgendamiento;

import org.springframework.stereotype.Component;

@Component
public class ManejadorDetalleAgendamiento {
    private final DaoAgendamiento dao;

    public ManejadorDetalleAgendamiento(DaoAgendamiento dao) {
        this.dao = dao;
    }

    public AgendamientoDto ejecutar(String codigo) {
        return dao.detalle(codigo);
    }
}
