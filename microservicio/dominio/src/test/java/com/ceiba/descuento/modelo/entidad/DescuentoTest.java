package com.ceiba.descuento.modelo.entidad;

import com.ceiba.desayuno.modelo.entidad.DesayunoId;
import com.ceiba.descuento.servicio.testdatabuilder.DescuentoTestDataBuilder;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class DescuentoTest {

    private Descuento descuento;

    @Before
    public void arrange() {
        descuento = new DescuentoTestDataBuilder().build();
    }

    @Test
    public void agrega_desayunos_ausentes() {
        List<DesayunoId> ausentes = descuento.agregarSiAusente(Collections.singletonList(DesayunoId.de(2L)));
        assertEquals(1, ausentes.size());
        assertEquals(2, descuento.getDesayunos().size());
    }

    @Test
    public void no_agrega_desayunos_presentes() {
        List<DesayunoId> ausentes = descuento.agregarSiAusente(Collections.singletonList(DesayunoId.de(1L)));
        assertEquals(0, ausentes.size());
        assertEquals(1, descuento.getDesayunos().size());
    }

    @Test
    public void mantiene_desayunos_existentes() {
        List<DesayunoId> removidos = descuento.mantener(Arrays.asList(DesayunoId.de(1L), DesayunoId.de(2L)));
        assertEquals(0, removidos.size());
        assertEquals(1, descuento.getDesayunos().size());
    }

    @Test
    public void no_mantiene_desayuno_ausente_nueva_lista() {
        List<DesayunoId> removidos = descuento.mantener(Collections.singletonList(DesayunoId.de(2L)));
        assertEquals(1, removidos.size());
        assertEquals(0, descuento.getDesayunos().size());
    }

    @Test
    public void limpia_desayunos_con_lista_vacia() {
        List<DesayunoId> removidos = descuento.mantener(Collections.emptyList());
        assertEquals(1, removidos.size());
        assertEquals(0, descuento.getDesayunos().size());
    }
}