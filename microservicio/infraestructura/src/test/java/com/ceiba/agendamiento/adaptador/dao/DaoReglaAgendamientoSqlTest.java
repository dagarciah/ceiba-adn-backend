package com.ceiba.agendamiento.adaptador.dao;

import java.util.List;

import com.ceiba.ConfiguracionDaoTest;
import com.ceiba.agendamiento.validacion.ReglaAgendamiento;
import com.ceiba.agendamiento.validacion.ReglaDiaDeLaSemanaNoHabil;
import com.ceiba.agendamiento.validacion.ReglaDiaFeriado;
import com.ceiba.agendamiento.validacion.ReglaFranjaHoraria;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { DaoReglaAgendamientoSql.class, ConfiguracionDaoTest.class })
public class DaoReglaAgendamientoSqlTest {

    @Autowired
    DaoReglaAgendamientoSql dao;

    @Test
    public void testListar() {
        List<ReglaAgendamiento> reglas = dao.listar();

        Assert.assertTrue(reglas.stream().anyMatch(r -> r instanceof ReglaFranjaHoraria));
        Assert.assertTrue(reglas.stream().anyMatch(r -> r instanceof ReglaDiaDeLaSemanaNoHabil));
        Assert.assertTrue(reglas.stream().anyMatch(r -> r instanceof ReglaDiaFeriado));
    }
}
