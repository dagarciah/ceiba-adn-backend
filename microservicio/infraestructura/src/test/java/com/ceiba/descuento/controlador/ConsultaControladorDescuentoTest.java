package com.ceiba.descuento.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.usuario.controlador.ConsultaControladorUsuario;
import org.hamcrest.core.IsNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ApplicationMock.class)
@WebMvcTest(ConsultaControladorDescuento.class)
public class ConsultaControladorDescuentoTest {
    @Autowired
    private MockMvc mocMvc;

    @Test
    public void listar() throws Exception {
        // arrange

        // act - assert
        mocMvc.perform(get("/descuento")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", IsNull.notNullValue()))
                .andExpect(jsonPath("$[0].etiqueta", IsNull.notNullValue()))
                .andExpect(jsonPath("$[0].porcentaje", IsNull.notNullValue()))
                .andExpect(jsonPath("$[0].fechaInicio", IsNull.notNullValue()))
                .andExpect(jsonPath("$[0].fechaFin", IsNull.notNullValue()));
    }

    @Test
    public void encontrar() throws Exception {
        // arrange

        // act - assert
        mocMvc.perform(get("/descuento/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.etiqueta", is("Dia de madres")))
                .andExpect(jsonPath("$.porcentaje", IsNull.notNullValue()))
                .andExpect(jsonPath("$.fechaInicio", IsNull.notNullValue()))
                .andExpect(jsonPath("$.fechaFin", IsNull.notNullValue()));
    }
}