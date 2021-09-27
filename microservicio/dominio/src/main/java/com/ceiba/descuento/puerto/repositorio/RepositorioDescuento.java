package com.ceiba.descuento.puerto.repositorio;

import com.ceiba.descuento.modelo.entidad.Descuento;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface RepositorioDescuento {
    boolean existe(BigDecimal porcentaje, LocalDate fechaInicio, LocalDate fechaFin);

    Long crear(Descuento descuento);
}
