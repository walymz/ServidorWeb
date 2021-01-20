drop database if exists dbventas;
create database dbventas;
use dbventas;

drop table if exists clientes;
drop table if exists articulos;
drop table if exists facturas;
drop table if exists detalles;

create table clientes (
    id integer auto_increment primary key,
    nombre varchar(20) not null,
    apellido varchar(20) not null,
    tipoDocumento enum('DNI', 'LC', 'LE', 'PASS') not null,
    numeroDocumento varchar(8)not null,
    telefono varchar(10),
    email varchar(50),
    direccion varchar(50)
)
ENGINE = InnoDB;
alter table clientes
   add constraint I_Clientes_Tipo_Documento
   unique(tipoDocumento, numeroDocumento);

create table facturas(
    id int auto_increment primary key,
    letra enum('A','B','C') not null,
    numero integer not null,
    fecha date,
    monto double,
    idCliente integer not null
) 
ENGINE = InnoDB;
alter table facturas
add constraint FK_facturas_codcli
    foreign key (idCliente)
    references clientes(id);
  --  on delete cascade;

create table articulos(
    id integer auto_increment,
    nombre varchar(50),
    precio double,
    stock integer,
primary key (id)
) ENGINE = InnoDB;

create table detalles(
    --id int auto_increment primary key
    idFactura int not null,
    idArticulo int not null,
    cantidad int unsigned not null,
primary key(idFactura, idArticulo)
)
ENGINE = InnoDB;

alter table detalles
    add constraint FK_facturas_id
    foreign key (idFactura)
    references facturas(id);
-- on delete cascade;

alter table detalles
    add constraint FK_articulos_codigo
    foreign key (idArticulo)
    references articulos(id)
-- on delete cascade;
