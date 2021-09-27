package com.ceiba.desayuno.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
public class DetalleDesayunoDto {
    private final String nombre;
    private final BigDecimal cantidad;
    private final String unidad;
}
