package com.ceiba.agendamiento.modelo.entidad;

public enum FlujoEstadoAgendamiento {
    PENDIENTE {
        public FlujoEstadoAgendamiento siguiente() {
            return ALISTAMIENTO;
        }

        public boolean esCancelable() {
            return true;
        }
    },
    ALISTAMIENTO {
        public FlujoEstadoAgendamiento siguiente() {
            return DESPACHADO;
        }

        public boolean esCancelable() {
            return false;
        }
    },
    DESPACHADO {
        public FlujoEstadoAgendamiento siguiente() {
            return ENTREGADO;
        }

        public boolean esCancelable() {
            return false;
        }
    },
    ENTREGADO {
        public FlujoEstadoAgendamiento siguiente() {
            return this;
        }

        public boolean esCancelable() {
            return false;
        }
    },
    CANCELADO {
        public FlujoEstadoAgendamiento siguiente() {
            return this;
        }

        public boolean esCancelable() {
            return false;
        }
    };

    public abstract FlujoEstadoAgendamiento siguiente(); 
    public abstract boolean esCancelable();
}
