package com.ceiba.agendamiento.controlador;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.ceiba.ApplicationMock;
import com.ceiba.agendamiento.comando.ComandoSolicitudAgendamiento;
import com.ceiba.agendamiento.servicio.testdatabuilder.ComandoSolicitudAgendamientoTestDataBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;

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

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= ApplicationMock.class)
@WebMvcTest(ConsultaControladorAgendamiento.class)
public class ComandoControladorAgendamientoTest {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mocMvc;

    @Test
    public void crear() throws Exception{
        // arrange
        ComandoSolicitudAgendamiento solicitud = new ComandoSolicitudAgendamientoTestDataBuilder().build();

        // act - assert
        mocMvc.perform(post("/agendamiento")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(solicitud)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valor.estados[0].nombre", is("Pendiente")))
                .andExpect(jsonPath("$.valor.codigo", IsNull.notNullValue()));
    }
}
