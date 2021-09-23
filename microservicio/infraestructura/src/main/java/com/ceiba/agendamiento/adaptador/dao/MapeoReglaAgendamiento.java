package com.ceiba.agendamiento.adaptador.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.ceiba.agendamiento.excepcion.ExcepcionFormatoValorInvalido;
import com.ceiba.agendamiento.modelo.entidad.FranjaHoraria;
import com.ceiba.agendamiento.validacion.ReglaAgendamiento;
import com.ceiba.agendamiento.validacion.ReglaDiaDeLaSemanaNoHabil;
import com.ceiba.agendamiento.validacion.ReglaDiaFeriado;
import com.ceiba.agendamiento.validacion.ReglaFranjaHoraria;
import com.ceiba.infraestructura.jdbc.MapperResult;

import org.springframework.jdbc.core.RowMapper;

public class MapeoReglaAgendamiento implements RowMapper<ReglaAgendamiento> {

    private final ReglaDiaSemanaNoHabilMapper reglaDiaSemanaNoHabil = new ReglaDiaSemanaNoHabilMapper();
    private final ReglaDiaFeriadoMapper reglaDiaFeriado = new ReglaDiaFeriadoMapper();
    private final ReglaFranjaHorariaMapper reglaFranjaHoraria = new ReglaFranjaHorariaMapper();

    @Override
    public ReglaAgendamiento mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        if (resultSet.wasNull()) {
            return null;
        }

        ReglaAgendamiento regla;
        switch(resultSet.getString("tipo")) {
            case "ReglaFranjaHoraria": regla = reglaFranjaHoraria.mapRow(resultSet, rowNum); break;
            case "ReglaDiaFeriado": regla = reglaDiaFeriado.mapRow(resultSet, rowNum); break;
            case "ReglaDiaDeLaSemanaNoHabil": regla = reglaDiaSemanaNoHabil.mapRow(resultSet, rowNum); break;
            default: throw new IllegalArgumentException("Un valor para la columna \"tipo\" no esta presente para el registro.");
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

            DayOfWeek dia;
            switch (diaTexto) {
                case "LUNES":
                    dia = DayOfWeek.MONDAY;
                    break;
                case "MARTES":
                    dia = DayOfWeek.MONDAY;
                    break;
                case "MIERCOLES":
                    dia = DayOfWeek.MONDAY;
                    break;
                case "JUEVES":
                    dia = DayOfWeek.MONDAY;
                    break;
                case "VIERNES":
                    dia = DayOfWeek.MONDAY;
                    break;
                case "SABADO":
                    dia = DayOfWeek.MONDAY;
                    break;
                case "DOMINGO":
                    dia = DayOfWeek.MONDAY;
                    break;
                default:
                    throw new ExcepcionFormatoValorInvalido("dia_semana");
            }

            return new ReglaDiaDeLaSemanaNoHabil(dia);
        }

    }

}
