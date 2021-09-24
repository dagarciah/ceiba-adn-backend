package com.ceiba.agendamiento.modelo.entidad;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResultadoAgendamiento {
    private final String codigo;
    private final String estado;

    @Builder
    public ResultadoAgendamiento(String codigo, String estado) {
        validarObligatorio(codigo, "El codio de agendamiento es obligatorio.");
        validarObligatorio(estado, "El estado del agendamiento es obligatorio");

        this.codigo = codigo;
        this.estado = estado;
    }
        
}
