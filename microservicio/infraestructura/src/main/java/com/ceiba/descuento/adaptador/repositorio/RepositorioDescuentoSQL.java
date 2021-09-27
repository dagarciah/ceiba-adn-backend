package com.ceiba.descuento.adaptador.repositorio;

import com.ceiba.desayuno.modelo.entidad.DesayunoId;
import com.ceiba.descuento.modelo.entidad.Descuento;
import com.ceiba.descuento.puerto.repositorio.RepositorioDescuento;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RepositorioDescuentoSQL implements RepositorioDescuento {
    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace="descuento", value="crear")
    private static String sqlCrear;

    @SqlStatement(namespace="descuento", value="existe")
    private static String sqlExiste;

    @SqlStatement(namespace="descuento", value="crear_descuento_desayuno")
    private static String sqlCrearDescuentoDesayuno;

    public RepositorioDescuentoSQL(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public boolean existe(BigDecimal porcentaje, LocalDate fechaInicio, LocalDate fechaFin) {
        MapSqlParameterSource parametros = new MapSqlParameterSource();
        parametros.addValue("porcentaje", porcentaje);
        parametros.addValue("fechaInicio", fechaInicio);
        parametros.addValue("fechaFin", fechaFin);

        return customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .queryForObject(sqlExiste, parametros, Boolean.class);
    }

    @Override
    public Long crear(Descuento descuento) {
        Long descuentoId = customNamedParameterJdbcTemplate.crear(descuento, sqlCrear);
        crear(descuentoId, descuento.getDesayunos());

        return descuentoId;
    }

    private void crear(Long descuentoId, List<DesayunoId> desayunos) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("descuentoId", descuentoId);

        desayunos.forEach(desayuno -> {
            parametros.put("desayunoId", desayuno.getValor());
            customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                    .update(sqlCrearDescuentoDesayuno, parametros);
        });
    }
}
