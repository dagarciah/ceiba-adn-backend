package com.ceiba.desayuno.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
public class DesayunoDto {
    private final Long id;
    private final String nombre;
    private final String descripcion;
    private final String imagen;
    private final BigDecimal precio;
    private final List<DetalleDesayunoDto> detalle;
}
