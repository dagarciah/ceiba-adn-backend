package com.ceiba.agendamiento.modelo.entidad;

public enum EstadoAgendamiento {
    PENDIENTE {
        public EstadoAgendamiento siguiente() {
            return ALISTAMIENTO;
        }

        public boolean esCancelable() {
            return true;
        }
    },
    ALISTAMIENTO {
        public EstadoAgendamiento siguiente() {
            return DESPACHADO;
        }

        public boolean esCancelable() {
            return false;
        }
    },
    DESPACHADO {
        public EstadoAgendamiento siguiente() {
            return ENTREGADO;
        }

        public boolean esCancelable() {
            return false;
        }
    },
    ENTREGADO {
        public EstadoAgendamiento siguiente() {
            return this;
        }

        public boolean esCancelable() {
            return false;
        }
    },
    CANCELADO {
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
