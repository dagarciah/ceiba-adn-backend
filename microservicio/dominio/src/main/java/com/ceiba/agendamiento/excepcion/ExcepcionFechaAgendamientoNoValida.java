package com.ceiba.agendamiento.excepcion;

import com.ceiba.agendamiento.modelo.entidad.FranjaHoraria;

public class ExcepcionFechaAgendamientoNoValida extends RuntimeException{

    /**
     *
     */
    public static final String MENSAJE_AGENDAMIENTO_FUERA_DE_FRANJA = "Las solicitudes agendadas por fuera de la franja %s, no pueden ser agendadas para el siguiente dia habil.";
    public static final String MENSAJE_AGENDAMIENTO_DIA_NO_HABIL = "Las solicitudes agendadas en dias no habiles, no pueden ser agendadas para el siguiente dia habil.";

    public ExcepcionFechaAgendamientoNoValida(String mensaje) {
        super(mensaje);
    }

    public static ExcepcionFechaAgendamientoNoValida fueraFranja(FranjaHoraria franja) {
        return new ExcepcionFechaAgendamientoNoValida(String.format(MENSAJE_AGENDAMIENTO_FUERA_DE_FRANJA, franja.toString())); 
    }
    
    public static ExcepcionFechaAgendamientoNoValida diaNoHabil() {
        return new ExcepcionFechaAgendamientoNoValida(MENSAJE_AGENDAMIENTO_DIA_NO_HABIL); 
    }
}
