package com.ceiba.descuento.consulta;

import com.ceiba.descuento.modelo.entidad.Descuento;
import com.ceiba.descuento.puerto.dao.DaoDescuento;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListarDescuento {
    private final DaoDescuento dao;

    public ManejadorListarDescuento(DaoDescuento dao) {
        this.dao = dao;
    }

    public List<Descuento> ejecutar() {
        return dao.listar();
    }
}
