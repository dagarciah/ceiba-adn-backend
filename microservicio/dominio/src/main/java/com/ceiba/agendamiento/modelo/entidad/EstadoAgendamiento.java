package com.ceiba.agendamiento.modelo.entidad;

public enum EstadoAgendamiento {
    Pendiente {
        public EstadoAgendamiento siguiente() {
            return Alistamiento;
        }

        public boolean esCancelable() {
            return true;
        }
    },
    Alistamiento {
        public EstadoAgendamiento siguiente() {
            return Despachado;
        }

        public boolean esCancelable() {
            return false;
        }
    },
    Despachado {
        public EstadoAgendamiento siguiente() {
            return Entregado;
        }

        public boolean esCancelable() {
            return false;
        }
    },
    Entregado {
        public EstadoAgendamiento siguiente() {
            return this;
        }

        public boolean esCancelable() {
            return false;
        }
    },
    Cancelado {
        public EstadoAgendamiento siguiente() {
            return this;
        }

        public boolean esCancelable() {
            return false;
        }
    };

    public abstract EstadoAgendamiento siguiente(); 
    public abstract boolean esCancelable();
}
