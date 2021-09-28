package com.ceiba.descuento.adaptador.dao;

import com.ceiba.desayuno.modelo.entidad.DesayunoId;
import com.ceiba.descuento.adaptador.repositorio.MapeoDescuento;
import com.ceiba.descuento.adaptador.repositorio.MapeoDescuentoDesayuno;
import com.ceiba.descuento.modelo.entidad.Descuento;
import com.ceiba.descuento.puerto.dao.DaoDescuento;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
public class DaoDescuentoSql implements DaoDescuento {
    private static final String PARAMETRO_DESCUENTO_ID = "descuentoId";

    @SqlStatement(namespace="descuento", value="listar")
    private static String sqlListar;

    @SqlStatement(namespace="descuento", value="encontrar")
    private static String sqlEncontrar;

    @SqlStatement(namespace="descuento", value="encontrarDesayunoPorDescuento")
    private static String sqlEncontrarDesayunoPorDescuento;

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    public DaoDescuentoSql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public List<Descuento> listar() {
        return customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListar, new MapeoDescuento());
    }

    @Override
    public Descuento encontrar(Long descuentoId) {
        MapSqlParameterSource parametros = new MapSqlParameterSource(PARAMETRO_DESCUENTO_ID, descuentoId);
        Descuento descuento = customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlEncontrar, parametros, new MapeoDescuento());

        assert Objects.nonNull(descuento);
        descuento.agregarSiAusente(encontrarDesayunoPorDescuento(descuentoId));

        return descuento;
    }

    private List<DesayunoId> encontrarDesayunoPorDescuento(Long descuentoId) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put(PARAMETRO_DESCUENTO_ID, descuentoId);

        return customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .query(sqlEncontrarDesayunoPorDescuento, parametros, new MapeoDescuentoDesayuno());
    }
}
