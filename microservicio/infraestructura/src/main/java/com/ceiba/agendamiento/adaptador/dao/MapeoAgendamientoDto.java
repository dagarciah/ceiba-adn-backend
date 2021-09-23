package com.ceiba.agendamiento.adaptador.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ceiba.agendamiento.modelo.dto.AgendamientoDto;
import com.ceiba.infraestructura.jdbc.MapperResult;

import org.springframework.jdbc.core.RowMapper;

public class MapeoAgendamientoDto implements RowMapper<AgendamientoDto>, MapperResult {

    @Override
    public AgendamientoDto mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        if (resultSet.wasNull()) {
            return null;
        }

        return AgendamientoDto.builder()
                .id(resultSet.getLong("id"))
                .codigo(resultSet.getString("codigo"))
                .desayunoId(resultSet.getLong("desayuno_id"))
                .programacion(extraerLocalDateTime(resultSet, "programacion"))
                .build();
    }

}
