create table usuario (
 id int(11) not null auto_increment,
 nombre varchar(100) not null,
 clave varchar(45) not null,
 fecha_creacion datetime null,
 primary key (id)
);

create table configuracion_laboral(
    id varchar (20) not null,
    tipo varchar (20) not null,
    valor varchar (20) not null,
    primary key(id) 
);

create table agendamiento(
    id int (11) not null auto_increment,
    codigo varchar (12) not null,
    desayuno_id int (11) not null,
    programacion datetime null,
    primary key(id) 
);

create table estado_agendamiento_historico(
    id int (11) not null auto_increment,
    nombre varchar (20) not null,
    agendamiento_id int (11) not null,
    creacion datetime null,
    primary key(id) 
);