package com.ceiba.agendamiento.adaptador.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

import com.ceiba.agendamiento.excepcion.ExcepcionFormatoValorInvalido;
import com.ceiba.agendamiento.modelo.entidad.FranjaHoraria;
import com.ceiba.agendamiento.validacion.ReglaAgendamiento;
import com.ceiba.agendamiento.validacion.ReglaDiaDeLaSemanaNoHabil;
import com.ceiba.agendamiento.validacion.ReglaDiaFeriado;
import com.ceiba.agendamiento.validacion.ReglaFranjaHoraria;
import com.ceiba.infraestructura.jdbc.MapperResult;

import org.springframework.jdbc.core.RowMapper;

public class MapeoReglaAgendamiento implements RowMapper<ReglaAgendamiento> {

    private static final String MENSAJE_VALOR_PARA_COLUMNA_TIPO_NO_ESTA_PRESENTE = "Un valor para la columna \"tipo\" no esta presente para el registro.";
    private static final Map<String, DayOfWeek> DIAS_DE_LA_SEMANA = new HashMap<>();
    static {        
        DIAS_DE_LA_SEMANA.put("LUNES", DayOfWeek.MONDAY);
        DIAS_DE_LA_SEMANA.put("MARTES", DayOfWeek.TUESDAY);
        DIAS_DE_LA_SEMANA.put("MIERCOLES", DayOfWeek.WEDNESDAY);
        DIAS_DE_LA_SEMANA.put("JUEVES", DayOfWeek.THURSDAY);
        DIAS_DE_LA_SEMANA.put("VIERNES", DayOfWeek.FRIDAY);
        DIAS_DE_LA_SEMANA.put("SABADO", DayOfWeek.SATURDAY);
        DIAS_DE_LA_SEMANA.put("DOMINGO", DayOfWeek.SUNDAY);
    }

    private final ReglaDiaSemanaNoHabilMapper reglaDiaSemanaNoHabil = new ReglaDiaSemanaNoHabilMapper();
    private final ReglaDiaFeriadoMapper reglaDiaFeriado = new ReglaDiaFeriadoMapper();
    private final ReglaFranjaHorariaMapper reglaFranjaHoraria = new ReglaFranjaHorariaMapper();

    @Override
    public ReglaAgendamiento mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        if (resultSet.wasNull()) {
            return null;
        }

        ReglaAgendamiento regla;
        switch (resultSet.getString("tipo")) {
            case "ReglaFranjaHoraria":
                regla = reglaFranjaHoraria.mapRow(resultSet, rowNum);
                break;
            case "ReglaDiaFeriado":
                regla = reglaDiaFeriado.mapRow(resultSet, rowNum);
                break;
            case "ReglaDiaDeLaSemanaNoHabil":
                regla = reglaDiaSemanaNoHabil.mapRow(resultSet, rowNum);
                break;
            default:
                throw new IllegalArgumentException(MENSAJE_VALOR_PARA_COLUMNA_TIPO_NO_ESTA_PRESENTE);
        }

        return regla;
    }

    private class ReglaFranjaHorariaMapper implements RowMapper<ReglaAgendamiento>, MapperResult {
        @Override
        public ReglaAgendamiento mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            try {
                DateTimeFormatter formatoFranjaHoraria = DateTimeFormatter.ofPattern("hh:mm a");

                String horaInicialTexto = resultSet.getString("hora_inicial");
                LocalTime horaInicial = LocalTime.parse(horaInicialTexto, formatoFranjaHoraria);

                String horaFinalTexto = resultSet.getString("hora_final");
                LocalTime horaFinal = LocalTime.parse(horaFinalTexto, formatoFranjaHoraria);

                return new ReglaFranjaHoraria(FranjaHoraria.con(horaInicial, horaFinal));
            } catch (DateTimeParseException exception) {
                throw new ExcepcionFormatoValorInvalido("hora_inicial\" u \"hora_final");
            }
        }
    }

    private class ReglaDiaFeriadoMapper implements RowMapper<ReglaAgendamiento>, MapperResult {

        @Override
        public ReglaAgendamiento mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            try {
                DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                String fechaTexto = resultSet.getString("fecha");
                LocalDate fecha = LocalDate.parse(fechaTexto, formatoFecha);

                return new ReglaDiaFeriado(fecha);
            } catch (DateTimeParseException exception) {
                throw new ExcepcionFormatoValorInvalido("fecha");
            }
        }

    }

    private class ReglaDiaSemanaNoHabilMapper implements RowMapper<ReglaAgendamiento>, MapperResult {

        @Override
        public ReglaAgendamiento mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            String diaTexto = resultSet.getString("dia_semana");

            if (!DIAS_DE_LA_SEMANA.containsKey(diaTexto)) {
                throw new ExcepcionFormatoValorInvalido("dia_semana");
            }

            return new ReglaDiaDeLaSemanaNoHabil(DIAS_DE_LA_SEMANA.get(diaTexto));
        }

    }

}
