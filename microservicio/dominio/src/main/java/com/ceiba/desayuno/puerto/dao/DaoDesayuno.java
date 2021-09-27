package com.ceiba.desayuno.puerto.dao;

import com.ceiba.desayuno.modelo.dto.DesayunoDto;

import java.util.List;

public interface DaoDesayuno {
    List<DesayunoDto> listar(int offset, int limit);

    DesayunoDto encontrar(Long id);
}
