package com.ceiba.agendamiento.servicio;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.ceiba.agendamiento.modelo.entidad.Agendamiento;
import com.ceiba.agendamiento.puerto.repositorio.RepositorioAgendamiento;
import com.ceiba.agendamiento.servicio.testdatabuilder.AgendamientoTestDataBuilder;

import org.junit.Before;
import org.junit.Test;

public class ServicioActualizarEstadoAgendamientoTest {

    private RepositorioAgendamiento repositorio;

    @Before
    public void preparar() {
        repositorio = mock(RepositorioAgendamiento.class);
    }

    @Test
    public void cancela_agendamiento_en_estado_pendiente() {
        Agendamiento existente = AgendamientoTestDataBuilder.builder().build();
        when(repositorio.encontrar(existente.getId())).thenReturn(existente);
    }
}
