package com.ceiba.agendamiento.adaptador.repositorio;

import java.util.Collections;

import com.ceiba.agendamiento.modelo.entidad.Agendamiento;
import com.ceiba.agendamiento.modelo.entidad.EstadoAgendamientoHistorico;
import com.ceiba.agendamiento.puerto.repositorio.RepositorioAgendamiento;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;

import org.springframework.stereotype.Component;

@Component
public class RepositorioAgendamientoSql implements RepositorioAgendamiento {
    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    public RepositorioAgendamientoSql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @SqlStatement(namespace = "agendamiento", value = "crear")
    private static String sqlCrear;

    @SqlStatement(namespace = "estado_agendamiento_historico", value = "crear")
    private static String sqlCrearEstado;

    @SqlStatement(namespace = "agendamiento", value = "encontrar")
    private static String sqlEncontrar;

    @Override
    public Agendamiento crear(Agendamiento agendamiento) {
        Long id = this.customNamedParameterJdbcTemplate.crear(agendamiento, sqlCrear);

        EstadoAgendamientoHistorico estado = EstadoAgendamientoHistorico.pendiente(id);
        this.customNamedParameterJdbcTemplate.crear(estado, sqlCrearEstado);

        return new Agendamiento(id, agendamiento.getCodigo(), agendamiento.getDesayunoId(),
                agendamiento.getProgramacion(), Collections.singletonList(estado));
    }

    @Override
    public Agendamiento encontrar(Long id) {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .queryForObject(sqlCrearEstado, Collections.singletonMap("id", id), new MapeoAgendamiento());
    }

    @Override
    public void actualizar(Agendamiento agendamiento) {
        // TODO Auto-generated method stub
        
    }
}
