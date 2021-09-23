package com.ceiba.agendamiento.adaptador.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ceiba.agendamiento.modelo.dto.AgendamientoDto;

import org.springframework.jdbc.core.RowMapper;

public class MapeoAgendamientoYEstadoDto implements RowMapper<AgendamientoDto> {
    private final MapeoEstadoAgendamientoDto mapeoEstadoAgendamiento = new MapeoEstadoAgendamientoDto();
    private final MapeoAgendamientoDto mapeoAgendamiento = new MapeoAgendamientoDto();

    @Override
    public AgendamientoDto mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        if (resultSet.wasNull()) {
            return null;
        }

        return mapeoAgendamiento.mapRow(resultSet, rowNum).toBuilder()
                .estadoActual(mapeoEstadoAgendamiento.mapRow(resultSet, rowNum))
                .build();
    }

}
