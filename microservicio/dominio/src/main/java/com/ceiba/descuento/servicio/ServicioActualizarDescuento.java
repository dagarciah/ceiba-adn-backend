package com.ceiba.descuento.servicio;

import com.ceiba.descuento.modelo.entidad.Descuento;
import com.ceiba.descuento.puerto.repositorio.RepositorioDescuento;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;

public class ServicioActualizarDescuento {
    public static final String EL_DESCUENTO_YA_EXISTE_EN_EL_SISTEMA = "El descuento ya existe en el sistema";
    private final RepositorioDescuento repositorio;

    public ServicioActualizarDescuento(RepositorioDescuento repositorio) {
        this.repositorio = repositorio;
    }

    public void ejecutar(Descuento descuento) {
        validarExistenciaPrevia(descuento);
        repositorio.actualizar(descuento);
    }

    private void validarExistenciaPrevia(Descuento descuento) {
        boolean existe = repositorio.existeIncluyendoId(descuento.getId(), descuento.getPorcentaje(), descuento.getFechaInicio(), descuento.getFechaFin());
        if (existe) {
            throw new ExcepcionDuplicidad(EL_DESCUENTO_YA_EXISTE_EN_EL_SISTEMA);
        }
    }
}
