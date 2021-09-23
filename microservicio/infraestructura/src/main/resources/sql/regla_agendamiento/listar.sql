select 'ReglaFranjaHoraria' as tipo, l1.valor as hora_inicial, l2.valor as hora_final, '' as dia_semana, '' as fecha
  from configuracion_laboral l1 
  join configuracion_laboral l2 on l1.tipo = l2.tipo
 where l1.id <> l2.id 
 and l1.tipo = 'FRANJA_HORARIA'
 and l1.id like '%INICIO%'

union 

select 'ReglaDiaDeLaSemanaNoHabil' as tipo, '' as hora_inicial, '' as hora_final, valor as dia_semana, '' as fecha
  from configuracion_laboral 
 where tipo = 'DIA_NO_HABIL'

union

select 'ReglaDiaFeriado' as tipo, '' as hora_inicial, '' as hora_final, '' as dia_semana, valor as fecha
  from configuracion_laboral 
 where tipo = 'DIA_FERIADO'