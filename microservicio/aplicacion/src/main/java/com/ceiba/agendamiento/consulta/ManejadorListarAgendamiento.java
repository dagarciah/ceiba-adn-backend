package com.ceiba.agendamiento.consulta;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.ceiba.agendamiento.modelo.dto.AgendamientoDto;
import com.ceiba.agendamiento.modelo.entidad.FlujoEstadoAgendamiento;
import com.ceiba.agendamiento.puerto.dao.DaoAgendamiento;

import org.springframework.stereotype.Component;

@Component
public class ManejadorListarAgendamiento {

    private final DaoAgendamiento dao;

    public ManejadorListarAgendamiento(DaoAgendamiento dao) {
        this.dao = dao;
    }

    public List<AgendamientoDto> ejecutar(List<String> estados) {
        List<FlujoEstadoAgendamiento> estadosEnum = estados.stream().peek(e -> {
            if (!e.matches("^(Pendiente|Alistamiento|Despachado|Entregado|Cancelado)$")) {
                throw new IllegalArgumentException("Ha filtrado por un estado de agendamiento no valido.");
            }
        }).map(FlujoEstadoAgendamiento::valueOf).collect(Collectors.toList());

        if (estadosEnum.isEmpty()) {
            estadosEnum = Arrays.asList(FlujoEstadoAgendamiento.values());
        }

        return dao.listar(estadosEnum);
    }

}
