package com.ceiba.desayuno.controlador;

import com.ceiba.ApplicationMock;
import org.hamcrest.core.IsNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ApplicationMock.class)
@WebMvcTest(ConsultaControladorDesayuno.class)
public class ConsultaControladorDesayunoTest {

    @Autowired
    private MockMvc mocMvc;

    @Test
    public void listar() throws Exception {
        // arrange

        // act - assert
        mocMvc.perform(get("/desayuno")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].nombre", is("Ancheta de Amor")))
                .andExpect(jsonPath("$[0].imagen", is("QW5jaGV0YSBkZSBBbW9y.jpg")));
    }

    @Test
    public void detalle() throws Exception {
        // arrange

        // act - assert
        mocMvc.perform(get("/desayuno/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.nombre", is("Ancheta de Amor")))
                .andExpect(jsonPath("$.imagen", is("QW5jaGV0YSBkZSBBbW9y.jpg")))
                .andExpect(jsonPath("$.detalle", IsNull.notNullValue()));
    }
}