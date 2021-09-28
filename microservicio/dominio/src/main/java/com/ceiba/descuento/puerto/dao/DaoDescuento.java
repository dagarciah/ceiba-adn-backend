package com.ceiba.descuento.puerto.dao;

import com.ceiba.descuento.modelo.entidad.Descuento;

import java.util.List;

public interface DaoDescuento {
    List<Descuento> listar();

    Descuento encontrar(Long id);
}
