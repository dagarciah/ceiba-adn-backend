package com.ceiba.desayuno.adaptador.dao;

import com.ceiba.desayuno.modelo.dto.DetalleDesayunoDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeoDetalleDesayuno implements RowMapper<DetalleDesayunoDto> {
    @Override
    public DetalleDesayunoDto mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return DetalleDesayunoDto.builder()
                .nombre(resultSet.getString("nombre"))
                .cantidad(resultSet.getBigDecimal("cantidad"))
                .unidad(resultSet.getString("unidad"))
                .build();
    }
}
