package com.ceiba.agendamiento.excepcion;

public class ExcepcionFormatoValorInvalido extends RuntimeException {

    public ExcepcionFormatoValorInvalido(String campo) {
        super("El campo \"" + campo +"\" contiene un valor no valido.");
    }
    
}
