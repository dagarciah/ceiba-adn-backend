package com.ceiba.agendamiento.servicio.testdatabuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.ceiba.agendamiento.modelo.entidad.Agendamiento;
import com.ceiba.agendamiento.modelo.entidad.EstadoAgendamiento;
import com.ceiba.agendamiento.modelo.entidad.EstadoAgendamientoHistorico;

public class AgendamientoTestDataBuilder {

    private Long id;
    private String codigo;
    private Long desayunoId;
    private LocalDateTime programacion;
    private List<EstadoAgendamientoHistorico> estados = new ArrayList<>();

    private AgendamientoTestDataBuilder() {
        this.id = 1L;
        this.codigo = "ABC123DE";
        this.desayunoId = 1L;
        this.programacion = LocalDateTime.now().plusDays(2);
        this.estados.add(new EstadoAgendamientoHistorico(1L, this.id, LocalDateTime.now(), EstadoAgendamiento.Pendiente));
    }

    public static AgendamientoTestDataBuilder builder() {
        return new AgendamientoTestDataBuilder();
    }

    public AgendamientoTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }
    
    public AgendamientoTestDataBuilder conCodigo(String codigo) {
        this.codigo = codigo;
        return this;
    }

    public AgendamientoTestDataBuilder conDesayunoId(Long desayunoId) {
        this.desayunoId = desayunoId;
        return this;
    }

    public AgendamientoTestDataBuilder conProgramacion(LocalDateTime programacion) {
        this.programacion = programacion;
        return this;
    }

    public AgendamientoTestDataBuilder conEstado(EstadoAgendamientoHistorico estado) {
        this.estados.add(estado);
        return this;
    }

    public Agendamiento build() {
        return Agendamiento.builder()
                .id(id)
                .codigo(codigo)
                .desayunoId(desayunoId)
                .programacion(programacion)
                .estados(estados)
                .build();
    }
}