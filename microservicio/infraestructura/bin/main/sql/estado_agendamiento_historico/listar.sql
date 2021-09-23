select id, creacion as ultimo_cambio, nombre as estado
  from estado_agendamiento_historico 
 where agendamiento_id = :agendamientoId 