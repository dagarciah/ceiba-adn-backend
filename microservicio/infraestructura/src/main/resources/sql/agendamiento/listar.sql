select a.id, a.codigo, a.desayuno_id, a.programacion, h.nombre as estado, h.creacion as ultimo_cambio
  from agendamiento a
  join (
   select a.id as agendamiento, max(h.id) as estado
     from agendamiento a
       join estado_agendamiento_historico h on a.id = h.agendamiento_id
    group by a.id
  ) as mid on mid.agendamiento = a.id
  join estado_agendamiento_historico h on mid.estado = h.id
  where h.nombre in (:estado)