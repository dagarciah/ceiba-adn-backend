package com.ceiba.desayuno.consulta;

import com.ceiba.desayuno.modelo.dto.DesayunoDto;
import com.ceiba.desayuno.puerto.dao.DaoDesayuno;
import org.springframework.stereotype.Component;

@Component
public class ManejadorDetalleDesayuno {

    private final DaoDesayuno dao;

    public ManejadorDetalleDesayuno(DaoDesayuno dao) {
        this.dao = dao;
    }

    public DesayunoDto ejecutar(Long id) {
        return dao.encontrar(id);
    }
}
