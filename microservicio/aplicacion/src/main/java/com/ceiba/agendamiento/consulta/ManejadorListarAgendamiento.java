package com.ceiba.agendamiento.consulta;

import static com.ceiba.dominio.ValidadorArgumento.validarRegex;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.ceiba.agendamiento.modelo.dto.AgendamientoDto;
import com.ceiba.agendamiento.modelo.entidad.EstadoAgendamiento;
import com.ceiba.agendamiento.puerto.dao.DaoAgendamiento;

import org.springframework.stereotype.Component;

@Component
public class ManejadorListarAgendamiento {

    private final DaoAgendamiento dao;

    public ManejadorListarAgendamiento(DaoAgendamiento dao) {
        this.dao = dao;
    }

    public List<AgendamientoDto> ejecutar(List<String> estados) {
        List<EstadoAgendamiento> estadosEnum = estados.stream()
                .peek(e -> validarRegex(e, "^(Pendiente|Alistamiento|Despachado|Entregado|Cancelado)$",
                        "Ha filtrado por un estado de agendamiento no valido. " + e))
                .map(EstadoAgendamiento::valueOf).collect(Collectors.toList());

        if (estadosEnum.isEmpty()) {
            estadosEnum = Arrays.asList(EstadoAgendamiento.values());
        }
                
        return dao.listar(estadosEnum);
    }

}
