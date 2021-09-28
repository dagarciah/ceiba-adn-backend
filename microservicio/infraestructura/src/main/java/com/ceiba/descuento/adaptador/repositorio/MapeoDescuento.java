package com.ceiba.descuento.adaptador.repositorio;

import com.ceiba.descuento.modelo.entidad.Descuento;
import com.ceiba.infraestructura.jdbc.MapperResult;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeoDescuento implements RowMapper<Descuento>, MapperResult {
    @Override
    public Descuento mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return Descuento.builder()
                .id(resultSet.getLong("id"))
                .etiqueta(resultSet.getString("etiqueta"))
                .porcentaje(resultSet.getBigDecimal("porcentaje"))
                .fechaInicio(extraerLocalDate(resultSet, "fecha_inicio"))
                .fechaFin(extraerLocalDate(resultSet, "fecha_fin"))
                .build();
    }
}
