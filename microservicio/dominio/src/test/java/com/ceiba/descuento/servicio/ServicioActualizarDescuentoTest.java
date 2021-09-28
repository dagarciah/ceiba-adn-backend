package com.ceiba.descuento.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.descuento.modelo.entidad.Descuento;
import com.ceiba.descuento.puerto.repositorio.RepositorioDescuento;
import com.ceiba.descuento.servicio.testdatabuilder.DescuentoTestDataBuilder;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class ServicioActualizarDescuentoTest {
    private ServicioActualizarDescuento subject;
    private RepositorioDescuento repositorio;

    @Test
    public void actualiza_descuento_exitosamente() {
        // arrange
        Long descuentoId = 1L;
        Descuento descuento = new DescuentoTestDataBuilder().conId(descuentoId).build();
        repositorio = mock(RepositorioDescuento.class);
        subject = new ServicioActualizarDescuento(repositorio);

        when(repositorio.existeIncluyendoId(descuentoId, descuento.getPorcentaje(), descuento.getFechaInicio(), descuento.getFechaFin()))
                .thenReturn(false);

        // act
        subject.ejecutar(descuento);

        // assert
        verify(repositorio, times(1))
                .existeIncluyendoId(descuentoId, descuento.getPorcentaje(), descuento.getFechaInicio(), descuento.getFechaFin());
        verify(repositorio, times(1)).actualizar(descuento);
    }

    @Test
    public void falla_cuando_el_descuento_ya_existe() {
        // arrange
        long descuentoId = 1L;
        Descuento descuento = new DescuentoTestDataBuilder().conId(descuentoId).build();
        repositorio = mock(RepositorioDescuento.class);
        subject = new ServicioActualizarDescuento(repositorio);

        when(repositorio.existeIncluyendoId(descuentoId, descuento.getPorcentaje(), descuento.getFechaInicio(), descuento.getFechaFin()))
                .thenReturn(true);

        // act - assert
        BasePrueba.assertThrows(() -> subject.ejecutar(descuento), ExcepcionDuplicidad.class, ServicioActualizarDescuento.EL_DESCUENTO_YA_EXISTE_EN_EL_SISTEMA);

        // assert
        verify(repositorio, times(1))
                .existeIncluyendoId(descuentoId, descuento.getPorcentaje(), descuento.getFechaInicio(), descuento.getFechaFin());
        verify(repositorio, times(0)).actualizar(descuento);
    }
}