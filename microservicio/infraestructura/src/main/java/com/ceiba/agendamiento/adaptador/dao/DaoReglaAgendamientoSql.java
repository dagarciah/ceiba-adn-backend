package com.ceiba.agendamiento.adaptador.dao;

import com.ceiba.agendamiento.puerto.dao.DaoReglaAgendamiento;
import com.ceiba.agendamiento.validacion.ReglaAgendamiento;

import java.util.List;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;

import org.springframework.stereotype.Component;

@Component
public class DaoReglaAgendamientoSql implements DaoReglaAgendamiento {
    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace="regla_agendamiento", value="listar")
    private static String sqlListar;

    public DaoReglaAgendamientoSql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }
   
    @Override
    public List<ReglaAgendamiento> listar() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListar, new MapeoReglaAgendamiento());
    }
}
