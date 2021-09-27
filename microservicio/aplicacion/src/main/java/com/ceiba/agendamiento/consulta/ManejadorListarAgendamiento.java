package com.ceiba.agendamiento.consulta;

import com.ceiba.agendamiento.modelo.dto.AgendamientoDto;
import com.ceiba.agendamiento.puerto.dao.DaoAgendamiento;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListarAgendamiento {

    private final DaoAgendamiento dao;

    public ManejadorListarAgendamiento(DaoAgendamiento dao) {
        this.dao = dao;
    }

    public List<AgendamientoDto> ejecutar(List<String> estados) {
        return dao.listar(estados);
    }

}
