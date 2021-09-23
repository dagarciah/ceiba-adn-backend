package com.ceiba.agendamiento.validacion;

import java.util.function.Supplier;

import lombok.Getter;

public final class ValidacionRegla {
    @Getter
    private final boolean valida;
    private final Supplier<RuntimeException> error;

    private ValidacionRegla(boolean valida, Supplier<RuntimeException> error) {
        this.valida = valida;
        this.error = error;
    }

    public static ValidacionRegla invalida(Supplier<RuntimeException> error) {
        return new ValidacionRegla(false, error);
    }

    public static ValidacionRegla valida() {
        return new ValidacionRegla(true, () -> new RuntimeException("No se esperaba un error."));
    }

    public void lanzarError() {
        if (!valida) {
            throw error.get();
        }
    }
}