package com.ceiba.agendamiento.adaptador.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ceiba.agendamiento.modelo.dto.EstadoAgendamientoDto;
import com.ceiba.infraestructura.jdbc.MapperResult;

import org.springframework.jdbc.core.RowMapper;

public class MapeoEstadoAgendamientoDto implements RowMapper<EstadoAgendamientoDto>, MapperResult {

    @Override
    public EstadoAgendamientoDto mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        if (resultSet.wasNull()) {
            return null;
        }

        return EstadoAgendamientoDto.builder()
                    .nombre(resultSet.getString("estado"))
                    .fechaCambio(extraerLocalDateTime(resultSet, "ultimo_cambio"))
                    .build();                
    }

}
