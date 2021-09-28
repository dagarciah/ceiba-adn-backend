package com.ceiba.descuento.consulta;

import com.ceiba.descuento.modelo.entidad.Descuento;
import com.ceiba.descuento.puerto.dao.DaoDescuento;
import org.springframework.stereotype.Component;

@Component
public class ManejadorEncontrarDescuento {
    private final DaoDescuento dao;

    public ManejadorEncontrarDescuento(DaoDescuento dao) {
        this.dao = dao;
    }

    public Descuento ejecutar(Long id) {
        return dao.encontrar(id);
    }
}
