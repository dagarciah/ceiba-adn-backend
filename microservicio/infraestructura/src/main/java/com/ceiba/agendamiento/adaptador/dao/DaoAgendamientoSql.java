package com.ceiba.agendamiento.adaptador.dao;

import com.ceiba.agendamiento.modelo.dto.AgendamientoDto;
import com.ceiba.agendamiento.modelo.dto.EstadoAgendamientoDto;
import com.ceiba.agendamiento.modelo.entidad.FlujoEstadoAgendamiento;
import com.ceiba.agendamiento.puerto.dao.DaoAgendamiento;
import com.ceiba.agendamiento.puerto.excepcion.AgendamientoNoEncontrado;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class DaoAgendamientoSql implements DaoAgendamiento {
    public static final String EXPRESION_ESTADOS_SOPORTADOS = "^(Pendiente|Alistamiento|Despachado|Entregado|Cancelado)$";
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
    public List<AgendamientoDto> listar(List<String> estados) {
        List<FlujoEstadoAgendamiento> enums = estados.stream()
                .filter(e -> e.matches(EXPRESION_ESTADOS_SOPORTADOS))
                .map(FlujoEstadoAgendamiento::valueOf)
                .collect(Collectors.toList());

        if (estados.size() != enums.size()) {
            throw new IllegalArgumentException("Ha filtrado por un estado de agendamiento no valido.");
        }

        if (enums.isEmpty()) {
            enums = Arrays.asList(FlujoEstadoAgendamiento.values());
        }

        Map<String, List<String>> parametros = comoParametros(enums);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .query(sqlListar, parametros, new MapeoAgendamientoYEstadoDto());
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

    private Map<String, List<String>> comoParametros(List<FlujoEstadoAgendamiento> enums) {
        return Collections.singletonMap("estado",
                enums.stream().map(FlujoEstadoAgendamiento::name).collect(Collectors.toList()));
    }
}
