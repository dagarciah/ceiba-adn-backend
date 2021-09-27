select id, nombre, descripcion, imagen, precio
  from desayuno d
  limit :offset, :limit;
