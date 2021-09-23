package com.ceiba.agendamiento.servicio;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Collections;

import com.ceiba.BasePrueba;
import com.ceiba.agendamiento.excepcion.ExcepcionEstadoAgendamientoNoValido;
import com.ceiba.agendamiento.modelo.dto.EstadoAgendamientoDto;
import com.ceiba.agendamiento.modelo.entidad.Agendamiento;
import com.ceiba.agendamiento.modelo.entidad.EstadoAgendamiento;
import com.ceiba.agendamiento.modelo.entidad.EstadoAgendamientoHistorico;
import com.ceiba.agendamiento.puerto.repositorio.RepositorioAgendamiento;
import com.ceiba.agendamiento.servicio.testdatabuilder.AgendamientoTestDataBuilder;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ServicioActualizarEstadoAgendamientoTest {

    private RepositorioAgendamiento repositorio;
    private ServicioActualizarEstadoAgendamiento subject;

    @Before
    public void preparar() {
        repositorio = mock(RepositorioAgendamiento.class);
        subject = new ServicioActualizarEstadoAgendamiento(repositorio);
    }

    @Test
    public void cancela_agendamiento_en_estado_pendiente() {
        Agendamiento existente = AgendamientoTestDataBuilder.builder().build();
        when(repositorio.encontrar(existente.getId())).thenReturn(existente);

        EstadoAgendamientoDto estado = subject.ejecutar(existente.getId(), EstadoAgendamiento.CANCELADO);

        Assert.assertEquals(estado.getNombre(), EstadoAgendamiento.CANCELADO.name());
    }

    @Test
    public void cambia_alistamiento_un_agendamiento_en_estado_pendiente() {
        Agendamiento existente = AgendamientoTestDataBuilder.builder()
                .conEstados(Collections.singletonList(
                        new EstadoAgendamientoHistorico(1L, 1L, LocalDateTime.now(), EstadoAgendamiento.PENDIENTE)))
                .build();
        when(repositorio.encontrar(existente.getId())).thenReturn(existente);

        // act
        EstadoAgendamientoDto estado = subject.ejecutar(existente.getId(), EstadoAgendamiento.ALISTAMIENTO);

        // Assert
        Assert.assertEquals(estado.getNombre(), EstadoAgendamiento.ALISTAMIENTO.name());
    }

    @Test
    public void falla_cancelando_un_agendamiento_en_estado_alistamiento() {
        Agendamiento existente = AgendamientoTestDataBuilder.builder()
                .conEstados(Collections.singletonList(
                        new EstadoAgendamientoHistorico(1L, 1L, LocalDateTime.now(), EstadoAgendamiento.ALISTAMIENTO)))
                .build();
        when(repositorio.encontrar(existente.getId())).thenReturn(existente);

        // Act - Assert
        BasePrueba.assertThrows(() -> subject.ejecutar(existente.getId(), EstadoAgendamiento.CANCELADO),
                ExcepcionEstadoAgendamientoNoValido.class, "El agendamiento [1] no puede pasar a estado \"CANCELADO\"");
    }

    @Test
    public void falla_despachando_un_agendamiento_en_estado_pendiente() {
        Agendamiento existente = AgendamientoTestDataBuilder.builder().build();
        when(repositorio.encontrar(existente.getId())).thenReturn(existente);

        // Act - Assert
        BasePrueba.assertThrows(() -> subject.ejecutar(existente.getId(), EstadoAgendamiento.DESPACHADO),
                ExcepcionEstadoAgendamientoNoValido.class, "El agendamiento [1] no puede pasar a estado \"DESPACHADO\"");
    }
}
