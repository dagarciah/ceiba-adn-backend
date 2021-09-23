package com.ceiba.agendamiento.modelo.entidad;

import java.time.LocalDateTime;
import java.util.Objects;

import com.ceiba.dominio.ValidadorArgumento;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
public class EstadoAgendamientoHistorico {
    private Long id;
    /**
     * Codigo unico de agendamiento
     */
    private Long agendamientoId;
    private LocalDateTime creacion;
    private String nombre;
    @Getter(value = AccessLevel.NONE)
    private EstadoAgendamiento estado;

    @Builder
    EstadoAgendamientoHistorico(Long id, Long agendamientoId, LocalDateTime creacion, EstadoAgendamiento estado) {
        ValidadorArgumento.validarObligatorio(agendamientoId, "El identificador de agendamiento es obligatorio.");
        ValidadorArgumento.validarObligatorio(creacion, "La fecha de creacion del estado es obligatoria.");
        ValidadorArgumento.validarObligatorio(estado, "El tipo de estado es obligatorio.");
        
        this.agendamientoId = agendamientoId;
        this.creacion = creacion;
        this.estado = estado;
        this.nombre = estado.name();
    }

    public static EstadoAgendamientoHistorico pendiente(Long agendamientoId) {
        return new EstadoAgendamientoHistorico(null, agendamientoId, LocalDateTime.now(), EstadoAgendamiento.Pendiente); 
    }

    public boolean esCancelable() {
        return estado.esCancelable();
    } 
    
    public EstadoAgendamiento siguiente() {
        return estado.siguiente();
    }

    public boolean esIgual(EstadoAgendamiento comparable) {
        return Objects.equals(estado, comparable);
    }
   
}
