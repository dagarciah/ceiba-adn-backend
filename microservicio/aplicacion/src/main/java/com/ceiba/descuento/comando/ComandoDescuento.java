package com.ceiba.descuento.comando;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComandoCrearDescuento {
    private String etiqueta;
    private BigDecimal porcentaje;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private List<Long> desayunos;
}
