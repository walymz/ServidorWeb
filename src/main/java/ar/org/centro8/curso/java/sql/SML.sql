-- //////////////// TABLA CLIENTES ///////////////////

insert into clientes (nombre,apellido,tipoDocumento, numeroDocumento, telefono, email, direccion) values ('juan','perez','DNI','11111111','1111111111', 'juancito@gmail.com','peru 323');
insert into clientes (nombre,apellido,tipoDocumento, numeroDocumento, telefono, email, direccion) values ('juana','Roldan','DNI','22222222','1111111111', 'juancito@gmail.com','peru 323');
insert into clientes (nombre,apellido,tipoDocumento, numeroDocumento, telefono, email, direccion) values ('Maria','Rodriguez','LC','33333333','2222222222', 'maria@gmail.com','Lavale 838');
insert into clientes (nombre,apellido,tipoDocumento, numeroDocumento, telefono, email, direccion) values ('Jose','León','PASS','44444444','4444444444', 'joseito@gmail.com','Rivera 5447');
insert into clientes (nombre,apellido,tipoDocumento, numeroDocumento, telefono, email, direccion) values ('Ana','Suarez','PASS','55555555','5555555555', 'anita@gmail.com','Peron 452');


-- //////////////// TABLA ARTÍCULOS ///////////////////

insert into articulos (nombre,precio,stock) values ('Remera algodón',250,50);
insert into articulos (nombre,precio,stock) values ('Chompa',750,28);
insert into articulos (nombre,precio,stock) values ('Sandalias',2150,13);
insert into articulos (nombre,precio,stock) values ('Zapatillas',3000,100);
insert into articulos (nombre,precio,stock) values ('Collar',150,50);

-- //////////////// TABLA FACTURAS ///////////////////

insert into facturas (letra,numero,fecha,monto, idCliente) values ('A',1,'2020/10/01',500, 3);
insert into facturas (letra,numero,fecha,monto, idCliente) values ('A',2,'2020/10/18',2500, 3);
insert into facturas (letra,numero,fecha,monto, idCliente) values ('B',3,'2020/10/18',320, 2);
insert into facturas (letra,numero,fecha,monto, idCliente) values ('B',4,'2021/01/01',120, 2);
insert into facturas (letra,numero,fecha,monto, idCliente) values ('B',5,'2021/01/10',560, 2);

-- inserto un registro con la fecha actual

insert into facturas (letra,numero,fecha,monto, idCliente) values ('C',6,curdate(),300, 3);
insert into facturas (letra,numero,fecha,monto, idCliente) values ('B',6,curdate(),300, 3);
insert into facturas (letra,numero,fecha,monto, idCliente) values ('C',7,curdate(),400, 4);
insert into facturas (letra,numero,fecha,monto, idCliente) values ('A',8,'2012/10/25',350,4);
insert into facturas (letra,numero,fecha,monto, idCliente) values ('A',9,curdate(),540,4);

-- //////// TABLA DETALLES /////////////

insert into detalles (idFactura, idArticulo, cantidad) values(1,3,10);
insert into detalles (idFactura, idArticulo, cantidad) values(1,4,3);
insert into detalles (idFactura, idArticulo, cantidad) values(1,5,3);
insert into detalles (idFactura, idArticulo, cantidad) values(2,3,10);
insert into detalles (idFactura, idArticulo, cantidad) values(3,3,10);
