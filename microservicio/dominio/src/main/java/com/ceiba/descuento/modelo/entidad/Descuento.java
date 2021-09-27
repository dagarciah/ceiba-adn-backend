package com.ceiba.descuento.modelo.entidad;

import com.ceiba.desayuno.modelo.entidad.DesayunoId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class Descuento {
    private String etiqueta;
    private BigDecimal porcentaje;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private List<DesayunoId> desayunos;
}
