package com.ceiba.agendamiento.adaptador.dao;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.ceiba.agendamiento.excepcion.ExcepcionFormatoValorInvalido;
import com.ceiba.agendamiento.validacion.ReglaAgendamiento;
import com.ceiba.agendamiento.validacion.ReglaDiaDeLaSemanaNoHabil;
import com.ceiba.agendamiento.validacion.ReglaDiaFeriado;
import com.ceiba.agendamiento.validacion.ReglaFranjaHoraria;
import com.ceiba.agendamiento.validacion.ValidacionRegla;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MapeoReglaAgendamientoTest {

    private MapeoReglaAgendamiento subject;

    @Before
    public void preparar() {
        subject = new MapeoReglaAgendamiento();
    }

    @Test
    public void exitoso_cuando_deserializa_una_franja_horaria() throws SQLException {
        ResultSet result = mock(ResultSet.class);
        when(result.getString("tipo")).thenReturn(ReglaFranjaHoraria.class.getSimpleName());
        when(result.getString("hora_inicial")).thenReturn("08:00 AM");
        when(result.getString("hora_final")).thenReturn("12:00 PM");

        ReglaAgendamiento regla = subject.mapRow(result, 1);

        ValidacionRegla validacion = regla.validar(LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 0)));

        Assert.assertNotNull(regla);
        Assert.assertTrue(validacion.isValida());
    }

    @Test
    public void fallido_cuando_deserializa_una_franja_horaria_mal_formateada() throws SQLException {
        ResultSet result = mock(ResultSet.class);
        when(result.getString("tipo")).thenReturn(ReglaFranjaHoraria.class.getSimpleName());
        when(result.getString("hora_inicial")).thenReturn("23:50");
        when(result.getString("hora_final")).thenReturn("12:00 PM");

        Assert.assertThrows("El campo \"dia_semana\" contiene un valor no valido.", ExcepcionFormatoValorInvalido.class,
                () -> subject.mapRow(result, 1));
    }

    @Test
    public void exitoso_cuando_deserializa_un_dia_feriado() throws SQLException {
        ResultSet result = mock(ResultSet.class);
        when(result.getString("tipo")).thenReturn(ReglaDiaFeriado.class.getSimpleName());
        when(result.getString("fecha")).thenReturn("18/10/2021");

        ReglaAgendamiento regla = subject.mapRow(result, 1);

        ValidacionRegla validacion = regla.validar(LocalDateTime.of(2021, 10, 18, 0, 0));

        Assert.assertNotNull(regla);
        Assert.assertFalse(validacion.isValida());
    }

    @Test
    public void fallido_cuando_deserializa_un_dia_feriado_mal_formateado() throws SQLException {
        ResultSet result = mock(ResultSet.class);
        when(result.getString("tipo")).thenReturn(ReglaDiaFeriado.class.getSimpleName());
        when(result.getString("fecha")).thenReturn("2021/10/18");

        Assert.assertThrows("El campo \"fecha\" contiene un valor no valido.", ExcepcionFormatoValorInvalido.class,
                () -> subject.mapRow(result, 1));
    }

    @Test
    public void exitoso_cuando_deserializa_dia_semana_lunes() throws SQLException {
        deserializar_dia_semana_exitosamente("LUNES");
    }

    @Test
    public void exitoso_cuando_deserializa_dia_semana_martes() throws SQLException {
        deserializar_dia_semana_exitosamente("MARTES");
    }

    @Test
    public void exitoso_cuando_deserializa_dia_semana_miercoles() throws SQLException {
        deserializar_dia_semana_exitosamente("MIERCOLES");
    }

    @Test
    public void exitoso_cuando_deserializa_dia_semana_jueves() throws SQLException {
        deserializar_dia_semana_exitosamente("JUEVES");
    }

    @Test
    public void exitoso_cuando_deserializa_dia_semana_viernes() throws SQLException {
        deserializar_dia_semana_exitosamente("VIERNES");
    }

    @Test
    public void exitoso_cuando_deserializa_dia_semana_sabado() throws SQLException {
        deserializar_dia_semana_exitosamente("SABADO");
    }

    @Test
    public void exitoso_cuando_deserializa_dia_semana_domingo() throws SQLException {
        deserializar_dia_semana_exitosamente("DOMINGO");
    }

    private void deserializar_dia_semana_exitosamente(String dia) throws SQLException {
        ResultSet result = mock(ResultSet.class);
        when(result.getString("tipo")).thenReturn(ReglaDiaDeLaSemanaNoHabil.class.getSimpleName());
        when(result.getString("dia_semana")).thenReturn(dia);

        ReglaAgendamiento regla = subject.mapRow(result, 1);

        ValidacionRegla validacion = regla.validar(LocalDateTime.of(2021, 10, 18, 0, 0));

        Assert.assertNotNull(regla);
        Assert.assertFalse(validacion.isValida());
    }

    @Test
    public void fallido_cuando_deserializa_un_dia_semana_mal_formateado() throws SQLException {
        ResultSet result = mock(ResultSet.class);
        when(result.getString("tipo")).thenReturn(ReglaDiaDeLaSemanaNoHabil.class.getSimpleName());
        when(result.getString("dia_semana")).thenReturn("MONDAY");

        Assert.assertThrows("El campo \"hora_inicial\" u \"hora_final\" contiene un valor no valido.", ExcepcionFormatoValorInvalido.class,
                () -> subject.mapRow(result, 1));
    }

}
