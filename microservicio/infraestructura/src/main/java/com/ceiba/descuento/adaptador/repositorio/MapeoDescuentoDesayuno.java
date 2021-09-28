package com.ceiba.descuento.adaptador.repositorio;

import com.ceiba.desayuno.modelo.entidad.DesayunoId;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeoDescuentoDesayuno implements RowMapper<DesayunoId> {
    @Override
    public DesayunoId mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return DesayunoId.de(resultSet.getLong("desayunoId"));
    }
}
