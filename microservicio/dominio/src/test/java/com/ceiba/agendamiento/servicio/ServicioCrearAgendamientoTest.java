package com.ceiba.agendamiento.servicio;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Collections;

import com.ceiba.agendamiento.modelo.dto.AgendamientoDto;
import com.ceiba.agendamiento.modelo.entidad.Agendamiento;
import com.ceiba.agendamiento.modelo.entidad.AgendamientoSequencia;
import com.ceiba.agendamiento.modelo.entidad.EstadoAgendamiento;
import com.ceiba.agendamiento.modelo.entidad.EstadoAgendamientoHistorico;
import com.ceiba.agendamiento.modelo.entidad.SolicitudAgendamiento;
import com.ceiba.agendamiento.puerto.repositorio.RepositorioAgendamiento;
import com.ceiba.agendamiento.servicio.testdatabuilder.SolicitudAgendamientoTestDataBuilder;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ServicioCrearAgendamientoTest {

    private ServicioCrearAgendamiento subject;
    private RepositorioAgendamiento repositorio;

    @Before
    public void preparar() {
        repositorio = mock(RepositorioAgendamiento.class);
        subject = new ServicioCrearAgendamiento(repositorio);
    }

    @Test
    public void agenda_exitosamente() {
        // arrange
        LocalDateTime haceDosDias = LocalDateTime.now().minusDays(2);
        SolicitudAgendamiento solicitud = SolicitudAgendamientoTestDataBuilder.defecto().conFecha(haceDosDias).build();
        
        Agendamiento agendamiento = new Agendamiento(1L, AgendamientoSequencia.siguiente(), solicitud.getDesayunoId().getValor(), solicitud.getFecha(), Collections.singletonList(EstadoAgendamientoHistorico.pendiente(1L)));
        when(repositorio.crear(any(Agendamiento.class))).thenReturn(agendamiento);

        // act
        AgendamientoDto resultado = subject.ejecutar(solicitud);

        // assert 
        Assert.assertNotNull(resultado);
        Assert.assertFalse(resultado.getCodigo().isEmpty());
        Assert.assertEquals(resultado.getEstados().get(0).getNombre(), EstadoAgendamiento.PENDIENTE.name());
    }
  
}
