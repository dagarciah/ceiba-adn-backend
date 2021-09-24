package com.ceiba.agendamiento.modelo.entidad;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;

import java.time.LocalDateTime;
import java.util.Objects;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
public class EstadoAgendamiento {
    private Long id;
    /**
     * Codigo unico de agendamiento
     */
    private Long agendamientoId;
    private LocalDateTime creacion;
    private String nombre;
    @Getter(value = AccessLevel.NONE)
    private FlujoEstadoAgendamiento estado;

    @Builder
    public EstadoAgendamiento(Long id, Long agendamientoId, LocalDateTime creacion, FlujoEstadoAgendamiento estado) {
        validarObligatorio(agendamientoId, "El identificador de agendamiento es obligatorio.");
        validarObligatorio(creacion, "La fecha de creacion del estado es obligatoria.");
        validarObligatorio(estado, "El tipo de estado es obligatorio.");
        
        this.agendamientoId = agendamientoId;
        this.creacion = creacion;
        this.estado = estado;
        this.nombre = estado.name();
    }

    public static EstadoAgendamiento pendiente(Long agendamientoId) {
        return new EstadoAgendamiento(null, agendamientoId, LocalDateTime.now(), FlujoEstadoAgendamiento.PENDIENTE); 
    }

    public boolean esCancelable() {
        return estado.esCancelable();
    } 
    
    public FlujoEstadoAgendamiento siguiente() {
        return estado.siguiente();
    }

    public boolean esIgual(FlujoEstadoAgendamiento comparable) {
        return Objects.equals(estado, comparable);
    }
   
}
