package com.ceiba.agendamiento.adaptador.dao;

import com.ceiba.agendamiento.excepcion.ExcepcionFechaAgendamientoNoValida;
import com.ceiba.agendamiento.excepcion.ExcepcionFormatoValorInvalido;
import com.ceiba.agendamiento.validacion.*;
import org.junit.Before;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.Objects;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

        assert Objects.nonNull(regla);
        ValidacionRegla validacion = regla.validar(LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 0)));

        assertNotNull(regla);
        assertTrue(validacion.isValida());
    }

    @Test
    public void fallido_cuando_deserializa_una_franja_horaria_mal_formateada() throws SQLException {
        ResultSet result = mock(ResultSet.class);
        when(result.getString("tipo")).thenReturn(ReglaFranjaHoraria.class.getSimpleName());
        when(result.getString("hora_inicial")).thenReturn("23:50");
        when(result.getString("hora_final")).thenReturn("12:00 PM");

        assertThrows("El campo \"dia_semana\" contiene un valor no valido.", ExcepcionFormatoValorInvalido.class,
                () -> subject.mapRow(result, 1));
    }

    @Test
    public void exitoso_cuando_deserializa_un_dia_feriado() throws SQLException {
        ResultSet result = mock(ResultSet.class);
        when(result.getString("tipo")).thenReturn(ReglaDiaFeriado.class.getSimpleName());
        when(result.getString("fecha")).thenReturn("18/10/2021");

        ReglaAgendamiento regla = subject.mapRow(result, 1);

        assert Objects.nonNull(regla);
        ValidacionRegla validacion = regla.validar(LocalDateTime.of(2021, 10, 18, 0, 0));

        assertNotNull(regla);
        assertFalse(validacion.isValida());
        assertThrows(ExcepcionFechaAgendamientoNoValida.class, validacion::lanzarError);
    }

    @Test
    public void fallido_cuando_deserializa_un_dia_feriado_mal_formateado() throws SQLException {
        ResultSet result = mock(ResultSet.class);
        when(result.getString("tipo")).thenReturn(ReglaDiaFeriado.class.getSimpleName());
        when(result.getString("fecha")).thenReturn("2021/10/18");

        assertThrows("El campo \"fecha\" contiene un valor no valido.", ExcepcionFormatoValorInvalido.class,
                () -> subject.mapRow(result, 1));
    }

    @Test
    public void exitoso_cuando_deserializa_dia_semana_lunes() throws SQLException {
        LocalDateTime esperado = LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        deserializar_dia_semana_exitosamente("LUNES", esperado);
    }

    @Test
    public void exitoso_cuando_deserializa_dia_semana_martes() throws SQLException {
        LocalDateTime esperado = LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.TUESDAY));
        deserializar_dia_semana_exitosamente("MARTES", esperado);
    }

    @Test
    public void exitoso_cuando_deserializa_dia_semana_miercoles() throws SQLException {
        LocalDateTime esperado = LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.WEDNESDAY));
        deserializar_dia_semana_exitosamente("MIERCOLES", esperado);
    }

    @Test
    public void exitoso_cuando_deserializa_dia_semana_jueves() throws SQLException {
        LocalDateTime esperado = LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.THURSDAY));
        deserializar_dia_semana_exitosamente("JUEVES", esperado);
    }

    @Test
    public void exitoso_cuando_deserializa_dia_semana_viernes() throws SQLException {
        LocalDateTime esperado = LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        deserializar_dia_semana_exitosamente("VIERNES", esperado);
    }

    @Test
    public void exitoso_cuando_deserializa_dia_semana_sabado() throws SQLException {
        LocalDateTime esperado = LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
        deserializar_dia_semana_exitosamente("SABADO", esperado);
    }

    @Test
    public void exitoso_cuando_deserializa_dia_semana_domingo() throws SQLException {
        LocalDateTime esperado = LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        deserializar_dia_semana_exitosamente("DOMINGO", esperado);
    }

    @Test
    public void fallido_cuando_deserializa_un_dia_semana_mal_formateado() throws SQLException {
        ResultSet result = mock(ResultSet.class);
        when(result.getString("tipo")).thenReturn(ReglaDiaDeLaSemanaNoHabil.class.getSimpleName());
        when(result.getString("dia_semana")).thenReturn("MONDAY");

        assertThrows("El campo \"hora_inicial\" u \"hora_final\" contiene un valor no valido.",
                ExcepcionFormatoValorInvalido.class, () -> subject.mapRow(result, 1));
    }

    private void deserializar_dia_semana_exitosamente(String dia, LocalDateTime diaValidacion) throws SQLException {
        ResultSet result = mock(ResultSet.class);
        when(result.getString("tipo")).thenReturn(ReglaDiaDeLaSemanaNoHabil.class.getSimpleName());
        when(result.getString("dia_semana")).thenReturn(dia);

        ReglaAgendamiento regla = subject.mapRow(result, 1);

        assert Objects.nonNull(regla);
        ValidacionRegla validacion = regla.validar(diaValidacion);

        assertNotNull(regla);
        assertFalse(validacion.isValida());
        assertThrows(ExcepcionFechaAgendamientoNoValida.class, validacion::lanzarError);
    }

}
