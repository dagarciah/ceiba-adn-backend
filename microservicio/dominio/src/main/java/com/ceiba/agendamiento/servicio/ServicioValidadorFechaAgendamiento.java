package com.ceiba.agendamiento.servicio;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import com.ceiba.agendamiento.validacion.ReglaAgendamiento;
import com.ceiba.agendamiento.validacion.ReglaFranjaHoraria;
import com.ceiba.agendamiento.validacion.ValidacionRegla;
import com.ceiba.dominio.ValidadorArgumento;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;

public class ServicioValidadorFechaAgendamiento {
    private final List<ReglaAgendamiento> reglas;

    public ServicioValidadorFechaAgendamiento(List<ReglaAgendamiento> reglas) {
        this.reglas = new ArrayList<>(reglas);
    }

    public void validar(LocalDateTime fechaHoraCreacion, LocalDateTime fechaHoraAgendamiento) {
        validarObligatorio(fechaHoraCreacion, "La fecha de creacion del agendamiento es obligatoria.");
        validarObligatorio(fechaHoraAgendamiento, "La fecha de programacion del agendamiento es obligatoria.");

        LocalDate fechaCreacion = fechaHoraCreacion.toLocalDate();
        validarFechaCreacionNoSeaPasado(fechaCreacion);
        validarFechaAgendamientoMinimoUnDiaDespuesCreacion(fechaHoraCreacion, fechaHoraAgendamiento);

        LocalDate fechaAgendamiento = fechaHoraAgendamiento.toLocalDate();
        Optional<ValidacionRegla> validacion = ejecutarReglasValidacion(reglas.stream(), fechaHoraCreacion);
        if (validacion.isPresent()) {
            int diasHabiles = 0;
            LocalDateTime fecha = fechaHoraCreacion.plusDays(1);

            while (!(fecha.toLocalDate().isAfter(fechaAgendamiento)) && diasHabiles < 2) {
                Stream<ReglaAgendamiento> streamReglas = reglas.stream().filter(r -> !(r instanceof ReglaFranjaHoraria));
                Optional<ValidacionRegla> validacionAgendamiento = ejecutarReglasValidacion(streamReglas, fecha);
                if (!validacionAgendamiento.isPresent()) {
                    diasHabiles++;
                }
                fecha = fecha.plusDays(1);
            }

            if (diasHabiles < 2) {
                validacion.get().lanzarError();
            }
        }
    }

    private void validarFechaAgendamientoMinimoUnDiaDespuesCreacion(LocalDateTime fechaHoraCreacion,
            LocalDateTime fechaHoraAgendamiento) {

        LocalDateTime diaDespuesCreacion = fechaHoraCreacion.plusDays(1L);
        ValidadorArgumento.validarMenor(diaDespuesCreacion, fechaHoraAgendamiento,
                "La fecha de agendamiento debe ser minimo un dia despues a la creacion.");
    }

    private void validarFechaCreacionNoSeaPasado(LocalDate fechaCreacion) {
        if (fechaCreacion.isBefore(LocalDate.now())) {
            throw new ExcepcionValorInvalido("La fecha de creacion debe ser mayor o igual a la del sistema");
        }
    }

    private Optional<ValidacionRegla> ejecutarReglasValidacion(Stream<ReglaAgendamiento> reglas, LocalDateTime fecha) {
        return reglas.map(r -> r.validar(fecha)).filter(v -> !v.isValida()).findAny();
    }
}
