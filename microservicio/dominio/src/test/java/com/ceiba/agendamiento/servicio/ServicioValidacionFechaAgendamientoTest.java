package com.ceiba.agendamiento.servicio;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.Collections;

import com.ceiba.BasePrueba;
import com.ceiba.agendamiento.excepcion.ExcepcionFechaAgendamientoNoValida;
import com.ceiba.agendamiento.modelo.entidad.FranjaHoraria;
import com.ceiba.agendamiento.validacion.ReglaDiaDeLaSemanaNoHabil;
import com.ceiba.agendamiento.validacion.ReglaDiaFeriado;
import com.ceiba.agendamiento.validacion.ReglaFranjaHoraria;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;

import org.junit.Assert;
import org.junit.Test;

public class ServicioValidacionFechaAgendamientoTest {

    @Test
    public void falla_con_fecha_creacion_pasado() {
        ServicioValidadorFechaAgendamiento subject;
        subject = new ServicioValidadorFechaAgendamiento(
                Collections.singletonList(new ReglaFranjaHoraria(FranjaHoraria.con(8, 0, 12, 0))));

        BasePrueba.assertThrows(() -> {
            LocalDateTime fechaHoraAgendamiento = LocalDateTime.now().plusDays(1);
            LocalDateTime fechaHoraCreacion = LocalDateTime.now().minusDays(1);
            subject.validar(fechaHoraCreacion, fechaHoraAgendamiento);
        }, ExcepcionValorInvalido.class, "La fecha de creacion debe ser mayor o igual a la del sistema");
    }

    @Test
    public void falla_con_fecha_agendamiento_igual_creacion() {
        ServicioValidadorFechaAgendamiento subject;
        subject = new ServicioValidadorFechaAgendamiento(
                Collections.singletonList(new ReglaFranjaHoraria(FranjaHoraria.con(8, 0, 12, 0))));

        BasePrueba.assertThrows(() -> {
            LocalDateTime fechaHoraCreacion = LocalDateTime.now();
            LocalDateTime fechaHoraAgendamiento = fechaHoraCreacion;
            subject.validar(fechaHoraCreacion, fechaHoraAgendamiento);
        }, ExcepcionValorInvalido.class, "La fecha de agendamiento debe ser minimo un dia despues a la creacion.");
    }

    @Test
    public void exitoso_con_fecha_agendamiento_dos_dias_despues() {
        ServicioValidadorFechaAgendamiento subject;
        subject = new ServicioValidadorFechaAgendamiento(
                Collections.singletonList(new ReglaFranjaHoraria(FranjaHoraria.con(8, 0, 12, 0))));

        LocalDateTime fechaHoraCreacion = LocalDateTime.now();
        LocalDateTime fechaHoraAgendamiento = fechaHoraCreacion.plusDays(2);
        
        Exception excepcion = null;
        try {
            subject.validar(fechaHoraCreacion, fechaHoraAgendamiento);
        } catch (Exception e) {
            excepcion = e;
        }

        Assert.assertNull(excepcion);
    }

    @Test
    public void exitoso_con_fecha_agendamiento_un_dia_despues_dentro_franja() {
        ServicioValidadorFechaAgendamiento subject;
        subject = new ServicioValidadorFechaAgendamiento(
                Collections.singletonList(new ReglaFranjaHoraria(FranjaHoraria.con(8, 0, 12, 0))));

        LocalDateTime fechaHoraCreacion = LocalDateTime.now().withHour(10);
        LocalDateTime fechaHoraAgendamiento = fechaHoraCreacion.plusDays(1);

        subject.validar(fechaHoraCreacion, fechaHoraAgendamiento);

        Exception excepcion = null;
        try {
            subject.validar(fechaHoraCreacion, fechaHoraAgendamiento);
        } catch (Exception e) {
            excepcion = e;
        }

        Assert.assertNull(excepcion);
    }

    @Test
    public void fallido_con_fecha_agendamiento_un_dia_despues_fuera_franja() {
        ServicioValidadorFechaAgendamiento subject;
        FranjaHoraria franja = FranjaHoraria.con(8, 0, 12, 0);
        subject = new ServicioValidadorFechaAgendamiento(
                Collections.singletonList(new ReglaFranjaHoraria(FranjaHoraria.con(8, 0, 12, 0))));

        BasePrueba.assertThrows(() -> {
            LocalDateTime fechaHoraCreacion = LocalDateTime.now().withHour(13);
            LocalDateTime fechaHoraAgendamiento = fechaHoraCreacion.plusDays(1).withHour(10);

            subject.validar(fechaHoraCreacion, fechaHoraAgendamiento);
        }, ExcepcionFechaAgendamientoNoValida.class, String
                .format(ExcepcionFechaAgendamientoNoValida.MENSAJE_AGENDAMIENTO_FUERA_DE_FRANJA, franja.toString()));
    }

    @Test
    public void fallido_con_fecha_agendamiento_un_dia_despues_de_dia_semana_no_habil() {
        ServicioValidadorFechaAgendamiento subject;
        subject = new ServicioValidadorFechaAgendamiento(Arrays.asList(new ReglaDiaDeLaSemanaNoHabil(DayOfWeek.SUNDAY),
                new ReglaFranjaHoraria(FranjaHoraria.con(8, 0, 12, 0))));

        BasePrueba.assertThrows(() -> {
            LocalDateTime fechaHoraCreacion = LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.SUNDAY))
                    .withHour(10);
            LocalDateTime fechaHoraAgendamiento = fechaHoraCreacion.plusDays(1);

            subject.validar(fechaHoraCreacion, fechaHoraAgendamiento);
        }, ExcepcionFechaAgendamientoNoValida.class,
                ExcepcionFechaAgendamientoNoValida.MENSAJE_AGENDAMIENTO_DIA_NO_HABIL);
    }

    @Test
    public void fallido_con_fecha_agendamiento_un_dia_despues_de_dia_feriado() {
        LocalDateTime fechaHoraCreacion = LocalDateTime.now().withHour(10);

        ServicioValidadorFechaAgendamiento subject;
        subject = new ServicioValidadorFechaAgendamiento(
                Arrays.asList(new ReglaFranjaHoraria(FranjaHoraria.con(8, 0, 12, 0)),
                        new ReglaDiaFeriado(fechaHoraCreacion.toLocalDate())));

        BasePrueba.assertThrows(() -> {
            LocalDateTime fechaHoraAgendamiento = fechaHoraCreacion.plusDays(1);

            subject.validar(fechaHoraCreacion, fechaHoraAgendamiento);
        }, ExcepcionFechaAgendamientoNoValida.class,
                ExcepcionFechaAgendamientoNoValida.MENSAJE_AGENDAMIENTO_DIA_NO_HABIL);
    }

    @Test
    public void fallido_con_fecha_fuera_franja_fin_de_semana_largo() {
        LocalDateTime fechaHoraCreacion = LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.SATURDAY))
                .withHour(13);

        FranjaHoraria franja = FranjaHoraria.con(8, 0, 12, 0);
        LocalDateTime diaFeraido = fechaHoraCreacion.plusDays(2);

        ServicioValidadorFechaAgendamiento subject;
        subject = new ServicioValidadorFechaAgendamiento(Arrays.asList(new ReglaFranjaHoraria(franja),
                new ReglaDiaDeLaSemanaNoHabil(DayOfWeek.SUNDAY), new ReglaDiaFeriado(diaFeraido.toLocalDate())));

        BasePrueba.assertThrows(() -> {
            LocalDateTime fechaHoraAgendamiento = diaFeraido;

            subject.validar(fechaHoraCreacion, fechaHoraAgendamiento);
        }, ExcepcionFechaAgendamientoNoValida.class, String
                .format(ExcepcionFechaAgendamientoNoValida.MENSAJE_AGENDAMIENTO_FUERA_DE_FRANJA, franja.toString()));
    }
}
