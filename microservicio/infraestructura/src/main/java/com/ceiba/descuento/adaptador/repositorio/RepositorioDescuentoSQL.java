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
import java.util.*;

@Repository
public class RepositorioDescuentoSQL implements RepositorioDescuento {
    private static final String PARAMETRO_DESCUENTO_ID = "descuentoId";

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace="descuento", value="crear")
    private static String sqlCrear;

    @SqlStatement(namespace="descuento", value="actualizar")
    private static String sqlActualizar;

    @SqlStatement(namespace="descuento", value="existe")
    private static String sqlExiste;

    @SqlStatement(namespace="descuento", value="existeIncluyendoId")
    private static String sqlExisteIncluyendoId;

    @SqlStatement(namespace="descuento", value="crearDescuentoDesayuno")
    private static String sqlCrearDescuentoDesayuno;

    @SqlStatement(namespace="descuento", value="eliminarDescuentoDesayuno")
    private static String sqlEliminarDescuentoDesayuno;

    @SqlStatement(namespace="descuento", value="encontrarDesayunoPorDescuento")
    private static String sqlEncontrarDesayunoPorDescuento;

    public RepositorioDescuentoSQL(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public boolean existe(BigDecimal porcentaje, LocalDate fechaInicio, LocalDate fechaFin) {
        MapSqlParameterSource parametros = new MapSqlParameterSource();
        parametros.addValue("porcentaje", porcentaje);
        parametros.addValue("fechaInicio", fechaInicio);
        parametros.addValue("fechaFin", fechaFin);

        return Optional.ofNullable(customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .queryForObject(sqlExiste, parametros, Boolean.class)).orElse(false);
    }

    @Override
    public boolean existeIncluyendoId(Long id, BigDecimal porcentaje, LocalDate fechaInicio, LocalDate fechaFin) {
        MapSqlParameterSource parametros = new MapSqlParameterSource();
        parametros.addValue("porcentaje", porcentaje);
        parametros.addValue("fechaInicio", fechaInicio);
        parametros.addValue("fechaFin", fechaFin);
        parametros.addValue("id", id);

        return Optional.ofNullable(customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .queryForObject(sqlExiste, parametros, Boolean.class)).orElse(false);
    }

    @Override
    public Long crear(Descuento descuento) {
        Long descuentoId = customNamedParameterJdbcTemplate.crear(descuento, sqlCrear);
        crearDesayunos(descuentoId, descuento.getDesayunos());

        return descuentoId;
    }

    @Override
    public void actualizar(Descuento descuento) {
        customNamedParameterJdbcTemplate.actualizar(descuento, sqlActualizar);

        eliminarDesayunos(descuento.getId());
        crearDesayunos(descuento.getId(), descuento.getDesayunos());
    }



    private void crearDesayunos(Long descuentoId, List<DesayunoId> desayunos) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put(PARAMETRO_DESCUENTO_ID, descuentoId);

        desayunos.forEach(desayuno -> {
            parametros.put("desayunoId", desayuno.getValor());
            customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                    .update(sqlCrearDescuentoDesayuno, parametros);
        });
    }

    private void eliminarDesayunos(Long descuentoId) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put(PARAMETRO_DESCUENTO_ID, descuentoId);

        customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .update(sqlEliminarDescuentoDesayuno, parametros);
    }
}
