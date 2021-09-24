package com.ceiba.agendamiento.servicio.testdatabuilder;

import java.time.LocalDateTime;

import com.ceiba.agendamiento.modelo.entidad.Agendamiento;
import com.ceiba.agendamiento.modelo.entidad.EstadoAgendamiento;
import com.ceiba.agendamiento.modelo.entidad.FlujoEstadoAgendamiento;
import com.ceiba.agendamiento.modelo.entidad.HistoricoEstadoAgendamiento;

public class AgendamientoTestDataBuilder {

    private Long id;
    private String codigo;
    private Long desayunoId;
    private LocalDateTime programacion;
    private HistoricoEstadoAgendamiento historico = new HistoricoEstadoAgendamiento();

    private AgendamientoTestDataBuilder() {
        this.id = 1L;
        this.codigo = "ABC123DE";
        this.desayunoId = 1L;
        this.programacion = LocalDateTime.now().plusDays(2);
        
        EstadoAgendamiento pendiente = new EstadoAgendamiento(1L, this.id, LocalDateTime.now(), FlujoEstadoAgendamiento.PENDIENTE);
        this.historico.agregar(pendiente);
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

    public AgendamientoTestDataBuilder conEstado(EstadoAgendamiento estado) {
        this.historico.agregar(estado);
        return this;
    }

    public AgendamientoTestDataBuilder conHistorico(HistoricoEstadoAgendamiento historico) {
        this.historico = historico;
        return this;
    }

    public Agendamiento build() {
        return Agendamiento.builder()
                .id(id)
                .codigo(codigo)
                .desayunoId(desayunoId)
                .programacion(programacion)
                .historico(historico)
                .build();
    }
}