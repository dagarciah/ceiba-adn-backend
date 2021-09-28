package com.ceiba.descuento.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.descuento.comando.ComandoDescuento;
import com.ceiba.descuento.servicio.testdatabuilder.ComandoDescuentoTestDataBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ApplicationMock.class)
@WebMvcTest(ComandoControladorDescuento.class)
public class ComandoControladorDescuentoTest {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mocMvc;

    @Test
    public void crear() throws Exception {
        // arrange
        ComandoDescuento usuario = new ComandoDescuentoTestDataBuilder().build();

        // act - assert
        mocMvc.perform(post("/descuento")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isOk())
                .andExpect(content().json("{'valor': 3}"));
    }

    @Test
    public void descuento_ya_existe() throws Exception {
        // arrange
        LocalDate fechaInicio = LocalDate.of(2021, 5, 10);
        ComandoDescuento usuario = new ComandoDescuentoTestDataBuilder()
                .conPorcentaje(BigDecimal.valueOf(15))
                .conFechaInicio(fechaInicio)
                .conFechaFin(fechaInicio.plusDays(5))
                .build();

        // act - assert
        mocMvc.perform(post("/descuento")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.nombreExcepcion", Is.is("ExcepcionDuplicidad")))
                .andExpect(jsonPath("$.mensaje", Is.is("El descuento ya existe en el sistema")));
    }

    @Test
    public void actualizar() throws Exception {
        // arrange
        LocalDate fechaInicio = LocalDate.of(2021, 6, 5);
        ComandoDescuento usuario = new ComandoDescuentoTestDataBuilder()
                .conPorcentaje(BigDecimal.valueOf(15))
                .conFechaInicio(fechaInicio)
                .conFechaFin(fechaInicio.plusDays(5))
                .build();

        // act - assert
        mocMvc.perform(put("/descuento/{id}", 2)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isOk());
    }

    @Test
    public void actualiza_descuento_ya_existe() throws Exception {
        // arrange
        LocalDate fechaInicio = LocalDate.of(2021, 5, 10);
        ComandoDescuento usuario = new ComandoDescuentoTestDataBuilder()
                .conPorcentaje(BigDecimal.valueOf(15))
                .conFechaInicio(fechaInicio)
                .conFechaFin(fechaInicio.plusDays(5))
                .build();

        // act - assert
        mocMvc.perform(put("/descuento/{id}", 2)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.nombreExcepcion", Is.is("ExcepcionDuplicidad")))
                .andExpect(jsonPath("$.mensaje", Is.is("El descuento ya existe en el sistema")));
    }
}