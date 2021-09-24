package com.ceiba.agendamiento.adaptador.repositorio;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ceiba.agendamiento.modelo.entidad.Agendamiento;
import com.ceiba.agendamiento.modelo.entidad.EstadoAgendamiento;
import com.ceiba.agendamiento.modelo.entidad.FlujoEstadoAgendamiento;
import com.ceiba.agendamiento.modelo.entidad.HistoricoEstadoAgendamiento;
import com.ceiba.infraestructura.jdbc.MapperResult;

import org.springframework.jdbc.core.RowMapper;

public class MapeoAgendamiento implements RowMapper<Agendamiento>, MapperResult {

    @Override
    public Agendamiento mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        if (resultSet.wasNull()) {
            return null;
        }

        return Agendamiento.builder()
                .id(resultSet.getLong("id"))
                .codigo(resultSet.getString("codigo"))
                .desayunoId(resultSet.getLong("desayuno_id"))
                .programacion(extraerLocalDateTime(resultSet, "programacion"))
                .historico(
                    new HistoricoEstadoAgendamiento(
                        EstadoAgendamiento.builder()
                            .id(resultSet.getLong("id"))
                            .estado(FlujoEstadoAgendamiento.valueOf(resultSet.getString("nombre")))
                            .creacion(extraerLocalDateTime(resultSet, "creacion"))
                            .build()
                    )
                )
                .build();
    }

}
