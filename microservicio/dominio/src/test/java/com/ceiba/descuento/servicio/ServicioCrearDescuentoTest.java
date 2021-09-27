package com.ceiba.descuento.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.descuento.modelo.entidad.Descuento;
import com.ceiba.descuento.puerto.repositorio.RepositorioDescuento;
import com.ceiba.descuento.servicio.testdatabuilder.DescuentoTestDataBuilder;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ServicioCrearDescuentoTest {

    private ServicioCrearDescuento subject;
    private RepositorioDescuento repositorio;

    @Test
    public void crea_descuento_exitosamente() {
        // arrange
        Long descuentoIdEsperado = 1L;
        Descuento descuento = new DescuentoTestDataBuilder().build();
        repositorio = mock(RepositorioDescuento.class);
        subject = new ServicioCrearDescuento(repositorio);

        when(repositorio.existe(descuento.getPorcentaje(), descuento.getFechaInicio(), descuento.getFechaFin()))
                .thenReturn(false);
        when(repositorio.crear(descuento)).thenReturn(descuentoIdEsperado);

        // act
        Long descuentoId = subject.ejecutar(descuento);

        // assert
        assertEquals(descuentoIdEsperado, descuentoId);
        verify(repositorio, times(1)).existe(descuento.getPorcentaje(), descuento.getFechaInicio(), descuento.getFechaFin());
        verify(repositorio, times(1)).crear(descuento);
    }

    @Test
    public void falla_cuando_el_descuento_ya_existe() {
        // arrange
        Descuento descuento = new DescuentoTestDataBuilder().build();
        repositorio = mock(RepositorioDescuento.class);
        subject = new ServicioCrearDescuento(repositorio);

        when(repositorio.existe(descuento.getPorcentaje(), descuento.getFechaInicio(), descuento.getFechaFin()))
                .thenReturn(true);

        // act - assert
        BasePrueba.assertThrows(() -> subject.ejecutar(descuento), ExcepcionDuplicidad.class, ServicioCrearDescuento.EL_DESCUENTO_YA_EXISTE_EN_EL_SISTEMA);

        // assert
        verify(repositorio, times(1)).existe(descuento.getPorcentaje(), descuento.getFechaInicio(), descuento.getFechaFin());
        verify(repositorio, times(0)).crear(descuento);
    }
}