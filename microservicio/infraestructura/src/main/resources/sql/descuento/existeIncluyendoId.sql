select count(1) from descuento
 where porcentaje = :porcentaje
   and :fechaInicio between fecha_inicio and fecha_fin
   and :fechaFin between fecha_inicio and fecha_fin