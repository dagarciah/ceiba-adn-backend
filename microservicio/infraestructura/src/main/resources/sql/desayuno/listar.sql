select id, nombre, descripcion, imagen
  from desayuno d
  limit :offset, :limit;
