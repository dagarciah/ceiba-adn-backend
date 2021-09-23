insert into usuario(nombre,clave,fecha_creacion) values('test','1234',now());

insert into configuracion_laboral(id, tipo, valor) values ('INICIO_FRANJA', 'FRANJA_HORARIA', '08:00 AM');
insert into configuracion_laboral(id, tipo, valor) values ('FIN_FRANJA', 'FRANJA_HORARIA', '12:00 PM');
insert into configuracion_laboral(id, tipo, valor) values ('DOMINGO', 'DIA_NO_HABIL', 'DOMINGO');
insert into configuracion_laboral(id, tipo, valor) values ('DIA_DE_LA_RAZA', 'DIA_FERIADO', '18/10/2021');

insert into agendamiento (codigo, desayuno_id, programacion) values ('ABC123DE', 1, '2021-09-28 13:24:03');

insert into estado_agendamiento_historico (creacion, nombre, agendamiento_id) values ('2021-09-27 07:24:03', 'PENDIENTE', 1);
insert into estado_agendamiento_historico (creacion, nombre, agendamiento_id) values ('2021-09-28 13:24:03', 'ALISTAMIENTO', 1);