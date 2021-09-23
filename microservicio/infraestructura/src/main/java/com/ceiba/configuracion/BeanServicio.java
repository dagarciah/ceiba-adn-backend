package com.ceiba.configuracion;

import java.util.Collections;

import com.ceiba.agendamiento.puerto.dao.DaoReglaAgendamiento;
import com.ceiba.agendamiento.puerto.repositorio.RepositorioAgendamiento;
import com.ceiba.agendamiento.servicio.ServicioActualizarEstadoAgendamiento;
import com.ceiba.agendamiento.servicio.ServicioCrearAgendamiento;
import com.ceiba.agendamiento.servicio.ServicioValidadorFechaAgendamiento;
import com.ceiba.usuario.puerto.repositorio.RepositorioUsuario;
import com.ceiba.usuario.servicio.ServicioActualizarUsuario;
import com.ceiba.usuario.servicio.ServicioCrearUsuario;
import com.ceiba.usuario.servicio.ServicioEliminarUsuario;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicio {

    @Bean
    public ServicioCrearUsuario servicioCrearUsuario(RepositorioUsuario repositorioUsuario) {
        return new ServicioCrearUsuario(repositorioUsuario);
    }

    @Bean
    public ServicioEliminarUsuario servicioEliminarUsuario(RepositorioUsuario repositorioUsuario) {
        return new ServicioEliminarUsuario(repositorioUsuario);
    }

    @Bean
    public ServicioActualizarUsuario servicioActualizarUsuario(RepositorioUsuario repositorioUsuario) {
        return new ServicioActualizarUsuario(repositorioUsuario);
    }
	
    @Bean
    public ServicioValidadorFechaAgendamiento servicioValidadorFechaAgendamiento(DaoReglaAgendamiento daoReglaAgendamiento) {
        return new ServicioValidadorFechaAgendamiento(Collections.emptyList());
    }

    @Bean
    public ServicioCrearAgendamiento servicioProgramarDesayuno(RepositorioAgendamiento repositorio) {
        return new ServicioCrearAgendamiento(repositorio);
    }

    @Bean
    public ServicioActualizarEstadoAgendamiento servicioActualizarEstadoAgendamiento(RepositorioAgendamiento repositorio) {
        return new ServicioActualizarEstadoAgendamiento(repositorio);
    }

}
