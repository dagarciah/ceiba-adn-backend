package com.ceiba.agendamiento.modelo.entidad;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;

import java.time.LocalDateTime;

import com.ceiba.desayuno.modelo.entidad.DesayunoId;

import lombok.Getter;

@Getter
public class SolicitudAgendamiento {
     
    private static final String FECHA_AGENAMIENTO_INVALIDA = "Se esperaba una fecha para el agendamiento.";
    private static final String DESAYUNO_ID_INVALIDO = "Se esperaba un identificador de desayuno.";
   
    private final DesayunoId desayunoId;
    private final LocalDateTime fecha;
    
    public SolicitudAgendamiento(DesayunoId desayunoId, LocalDateTime fecha) {
        validarObligatorio(desayunoId, DESAYUNO_ID_INVALIDO);
        validarObligatorio(fecha, FECHA_AGENAMIENTO_INVALIDA);
        
        this.desayunoId = desayunoId;
        this.fecha = fecha;
    }

}
