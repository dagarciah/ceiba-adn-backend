insert into usuario(nombre,clave,fecha_creacion) values('test','1234',now());
insert into configuracion_laboral(id, tipo, valor) values ('INICIO_FRANJA', 'FRANJA_HORARIA', '08:00 AM');
insert into configuracion_laboral(id, tipo, valor) values ('FIN_FRANJA', 'FRANJA_HORARIA', '12:00 PM');
insert into configuracion_laboral(id, tipo, valor) values ('DOMINGO', 'DIA_NO_HABIL', 'Domingo');
insert into configuracion_laboral(id, tipo, valor) values ('DIA_DE_LA_RAZA', 'DIA_FERIADO', '18/10/2021');