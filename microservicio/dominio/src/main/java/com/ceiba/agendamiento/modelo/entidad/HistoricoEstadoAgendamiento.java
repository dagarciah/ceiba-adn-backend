package com.ceiba.agendamiento.modelo.entidad;

import java.util.Comparator;
import java.util.TreeSet;

public class HistoricoEstadoAgendamiento {
    private final TreeSet<EstadoAgendamiento> historico = new TreeSet<>(Comparator.comparing(EstadoAgendamiento::getCreacion));   

    public HistoricoEstadoAgendamiento() {
    }

    public HistoricoEstadoAgendamiento(EstadoAgendamiento inicial) {
        agregar(inicial);
    }

    public void agregar(EstadoAgendamiento estado) {
        historico.add(estado);
    }

    public EstadoAgendamiento getActual() {
        return historico.last();
    }

}
