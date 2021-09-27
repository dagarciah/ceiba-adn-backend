insert into desayuno(nombre, precio, descripcion, imagen) values('Ancheta de Amor', 150000.00, 'La sorpresa es que le llegue a tu ser querido algo que no se espera y menos que seas es el primero en sorprenderle con uno de nuestros desayunos sorpresa', 'QW5jaGV0YSBkZSBBbW9y.jpg');

insert into detalle(nombre) values ('Bandeja de patas en madera');
insert into detalle(nombre) values ('Hermoso individual rojo de papel con diseño');
insert into detalle(nombre) values ('Precioso Peluche y de excelente calidad -león, tigre, oso motivo según disponibilidad');
insert into detalle(nombre) values ('Canasta de pan con su respectivo individual rojo de papel con diseño. Contiene: - Croissant, mojicón y pan blandito');
insert into detalle(nombre) values ('Cereales en cajita (Choco Crispis, Zucaritas ó Froot loops, según disponibilidad) ');
insert into detalle(nombre) values ('Leche entera o deslactosada Alpina');

insert into detalle_desayuno(desayuno_id, detalle_id, cantidad) values (1, 1, 1);
insert into detalle_desayuno(desayuno_id, detalle_id, cantidad) values (1, 2, 1);
insert into detalle_desayuno(desayuno_id, detalle_id, cantidad, unidad) values (1, 3, 27, 'cm');
insert into detalle_desayuno(desayuno_id, detalle_id, cantidad) values (1, 4, 1);
insert into detalle_desayuno(desayuno_id, detalle_id, cantidad, unidad) values (1, 5, 25, 'gr');
insert into detalle_desayuno(desayuno_id, detalle_id, cantidad) values (1, 6, 1);