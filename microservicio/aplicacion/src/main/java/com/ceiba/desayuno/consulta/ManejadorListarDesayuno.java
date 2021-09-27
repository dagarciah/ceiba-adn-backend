package com.ceiba.desayuno.consulta;

import com.ceiba.desayuno.modelo.dto.DesayunoDto;
import com.ceiba.desayuno.puerto.dao.DaoDesayuno;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListarDesayuno {
    private final DaoDesayuno dao;

    public ManejadorListarDesayuno(DaoDesayuno dao) {
        this.dao = dao;
    }

    public List<DesayunoDto> ejecutar(int offset, int limit) {
        return dao.listar(offset, limit);
    }
}
