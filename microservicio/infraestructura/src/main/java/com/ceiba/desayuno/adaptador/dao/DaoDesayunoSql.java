package com.ceiba.desayuno.adaptador.dao;

import com.ceiba.desayuno.modelo.dto.DesayunoDto;
import com.ceiba.desayuno.modelo.dto.DetalleDesayunoDto;
import com.ceiba.desayuno.puerto.dao.DaoDesayuno;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DaoDesayunoSql implements DaoDesayuno {
    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace = "desayuno", value = "listar")
    private static String sqlListar;

    @SqlStatement(namespace = "desayuno", value = "encontrar")
    private static String sqlEncontrar;

    @SqlStatement(namespace = "desayuno", value = "detalle")
    private static String sqlDetalle;

    public DaoDesayunoSql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public List<DesayunoDto> listar(int offset, int limit) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("offset", offset);
        parametros.put("limit", limit);


        return customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .query(sqlListar, parametros, new MapeoDesayuno());
    }

    @Override
    public DesayunoDto encontrar(Long id) {
        DesayunoDto desayuno = customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .queryForObject(sqlEncontrar, Collections.singletonMap("id", id), new MapeoDesayuno());

        List<DetalleDesayunoDto> detalle = customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .query(sqlDetalle, Collections.singletonMap("id", id), new MapeoDetalleDesayuno());

        return desayuno.toBuilder()
                .detalle(detalle)
                .build();
    }
}
