package com.ceiba.agendamiento.servicio;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Collections;

import com.ceiba.agendamiento.modelo.entidad.Agendamiento;
import com.ceiba.agendamiento.modelo.entidad.EstadoAgendamientoHistorico;
import com.ceiba.agendamiento.puerto.repositorio.RepositorioAgendamiento;

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
        Long id = 1L;
        Agendamiento existente = Agendamiento.builder()
            .id(id)
            .codigo("ABC123DE")
            .desayunoId(1L)
            .programacion(LocalDateTime.now())
            .estados(Collections.singletonList(EstadoAgendamientoHistorico.pendiente(id)))
            .build();

        when(repositorio.encontrar(id)).thenReturn(existente);
    }
}
