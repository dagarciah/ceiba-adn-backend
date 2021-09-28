package com.ceiba.descuento.comando;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComandoDescuento {
    @Setter
    private Long id;
    private String etiqueta;
    private BigDecimal porcentaje;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private List<Long> desayunos;
}
