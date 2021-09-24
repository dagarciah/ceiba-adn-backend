package com.ceiba.agendamiento.validacion;

import java.util.function.Supplier;

import com.ceiba.dominio.ValidadorArgumento;

import lombok.Getter;

public final class ValidacionRegla {
    @Getter
    private final boolean valida;
    private final Supplier<RuntimeException> proveedorError;

    private ValidacionRegla(boolean valida, Supplier<RuntimeException> proveedorError) {
        ValidadorArgumento.validarObligatorio(proveedorError, "El proveedor de error es obligatorio");
        this.valida = valida;
        this.proveedorError = proveedorError;
    }

    public static ValidacionRegla invalida(Supplier<RuntimeException> error) {
        return new ValidacionRegla(false, error);
    }

    public static ValidacionRegla valida() {
        return new ValidacionRegla(true, () -> new RuntimeException("No se esperaba un error."));
    }

    @SuppressWarnings("common-java:InsufficientBranchCoverage")
    public void lanzarError() {
        if (!valida) {
            throw proveedorError.get();
        }
    }
}