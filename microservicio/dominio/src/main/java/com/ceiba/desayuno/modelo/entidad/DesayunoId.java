package com.ceiba.desayuno.modelo.entidad;

import com.ceiba.dominio.ValidadorArgumento;

import lombok.Getter;

@Getter
public final class DesayunoId {
    private final Long valor;

    private DesayunoId(Long valor) {
        this.valor = valor;
    }

    public static DesayunoId de(Long valor) {
        ValidadorArgumento.validarObligatorio(valor, "Se esperaba un identificador numerico.");
        return new DesayunoId(valor);
    }
}
