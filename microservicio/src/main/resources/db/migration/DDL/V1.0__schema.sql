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

create table desayuno(
    id int (11) not null auto_increment,
    nombre varchar (255) not null,
    descripcion varchar (1024) not null,
    imagen varchar (64) not null,
    primary key(id)
);

create table detalle(
    id int (11) not null auto_increment,
    nombre varchar (255) not null,
    primary key(id)
);

create table detalle_desayuno (
    desayuno_id int (11) not null,
    detalle_id int (11) not null,
    cantidad decimal (4, 2) not null,
    unidad varchar (3) default '',
    primary key (desayuno_id, detalle_id),
    foreign key (desayuno_id) references desayuno(id),
    foreign key (detalle_id) references detalle(id)
);

create table agendamiento(
    id int (11) not null auto_increment,
    codigo varchar (12) not null,
    desayuno_id int (11) not null,
    programacion datetime null,
    primary key(id),
    foreign key (desayuno_id) references desayuno(id)
);

create table estado_agendamiento_historico(
    id int (11) not null auto_increment,
    nombre varchar (20) not null,
    agendamiento_id int (11) not null,
    creacion datetime null,
    primary key(id),
    foreign key (agendamiento_id) references agendamiento(id)
);