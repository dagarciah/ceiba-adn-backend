package com.ceiba.agendamiento.adaptador.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import com.ceiba.agendamiento.modelo.dto.AgendamientoDto;
import com.ceiba.agendamiento.modelo.dto.EstadoAgendamientoDto;
import com.ceiba.agendamiento.modelo.entidad.FlujoEstadoAgendamiento;
import com.ceiba.agendamiento.puerto.dao.DaoAgendamiento;
import com.ceiba.agendamiento.puerto.excepcion.AgendamientoNoEncontrado;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

@Component
public class DaoAgendamientoSql implements DaoAgendamiento {
    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    public DaoAgendamientoSql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @SqlStatement(namespace = "agendamiento", value = "listar")
    private static String sqlListar;

    @SqlStatement(namespace = "agendamiento", value = "encontrarUsandoCodigo")
    private static String sqlEncontrarUsandoCodigo;

    @SqlStatement(namespace = "estado_agendamiento_historico", value = "listar")
    private static String sqlListarEstadoHistorico;

    @Override
    public List<AgendamientoDto> listar(List<FlujoEstadoAgendamiento> estados) {
        Map<String, List<String>> parametros = Collections.singletonMap("estado",
                estados.stream().map(FlujoEstadoAgendamiento::name).collect(Collectors.toList()));

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListar, parametros,
                new MapeoAgendamientoYEstadoDto());
    }

    @Override
    public AgendamientoDto detalle(String codigo) {
        AgendamientoDto agendamiento;

        try {
            agendamiento = this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(
                    sqlEncontrarUsandoCodigo, Collections.singletonMap("codigo", codigo), new MapeoAgendamientoDto());

            assert Objects.nonNull(agendamiento);
        } catch (EmptyResultDataAccessException excepcion) {
            throw new AgendamientoNoEncontrado(excepcion, codigo);
        }

        List<EstadoAgendamientoDto> estados = this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .query(sqlListarEstadoHistorico, Collections.singletonMap("agendamientoId", agendamiento.getId()),
                        new MapeoEstadoAgendamientoDto());

        return agendamiento.toBuilder().estados(estados).build();
    }
}
