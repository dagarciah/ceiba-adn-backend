package com.ceiba.descuento.modelo.entidad;

import com.ceiba.desayuno.modelo.entidad.DesayunoId;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;

@Getter
public class Descuento {
    private Long id;
    private String etiqueta;
    private BigDecimal porcentaje;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private List<DesayunoId> desayunos;

    @Builder
    public Descuento(Long id, String etiqueta, BigDecimal porcentaje, LocalDate fechaInicio, LocalDate fechaFin, List<DesayunoId> desayunos) {
        validarObligatorio(etiqueta, "La etiqueta del descuento es obligatoria");
        validarObligatorio(porcentaje, "El porcentaje del descuento es obligatorio");
        validarObligatorio(fechaInicio, "La fecha inicial del descuento es obligatoria");
        validarObligatorio(fechaFin, "La fecha final del descuento es obligatoria");

        this.id = id;
        this.etiqueta = etiqueta;
        this.porcentaje = porcentaje;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.desayunos = Optional.ofNullable(desayunos)
                .map(ArrayList::new)
                .orElseGet(ArrayList::new);
    }
    public List<DesayunoId> agregarSiAusente(List<DesayunoId> comparables) {
        List<DesayunoId> ausentes = comparables.stream()
                .filter(c -> desayunos.stream().noneMatch(d -> Objects.equals(d.getValor(), c.getValor())))
                .collect(Collectors.toList());

        desayunos.addAll(ausentes);
        return ausentes;
    }

}
