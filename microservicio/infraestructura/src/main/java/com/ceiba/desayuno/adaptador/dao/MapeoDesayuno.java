package com.ceiba.desayuno.adaptador.dao;

import com.ceiba.desayuno.modelo.dto.DesayunoDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeoDesayuno implements RowMapper<DesayunoDto> {
    @Override
    public DesayunoDto mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return DesayunoDto.builder()
                .id(resultSet.getLong("id"))
                .nombre(resultSet.getString("nombre"))
                .descripcion(resultSet.getString("descripcion"))
                .imagen(resultSet.getString("imagen"))
                .build();
    }
}
