select el.nombre, e.cantidad, e.unidad
  from detalle_desayuno e
  join detalle el on el.id = e.detalle_id
 where e.desayuno_id = :id;
