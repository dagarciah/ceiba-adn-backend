package com.ceiba.agendamiento.consulta;

import com.ceiba.agendamiento.modelo.dto.AgendamientoDto;
import com.ceiba.agendamiento.puerto.dao.DaoAgendamiento;
import com.ceiba.dominio.ValidadorArgumento;

import org.springframework.stereotype.Component;

@Component
public class ManejadoDetalleAgendamiento {
    private final DaoAgendamiento dao;

    public ManejadoDetalleAgendamiento(DaoAgendamiento dao) {
        this.dao = dao;
    }

    public AgendamientoDto ejecutar(String codigo) {
        ValidadorArgumento.validarObligatorio(codigo, "El codigo unico de agendamiento es obligatorio.");
        return dao.detalle(codigo);
    }
}
