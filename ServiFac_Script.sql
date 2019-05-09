CREATE DATABASE empresa;
use empresa;
#show tables;
#describe venta;
#use empresa;

SELECT * from usuario;
SELECT Usuario, Contrasena, cedula_usuario FROM usuario WHERE Usuario = '1' && Contrasena = '1' or 1=1;
update clientes set credito = 0 where cedula_cliente = "1111111111112";
#SELECT p.Nombre_Producto, p.Codigo, p.precio_compra, p.precio, p.Precio_Mayor, u.nombre_ubicacion, p.Cantidad, p.iva FROM producto p, ubicacion u 
#WHERE  p.id_ubicacion = u.id_ubicacion AND stock = true AND (codigo LIKE "1346" OR Nombre_Producto LIKE "%1346%" ) ORDER BY Nombre_Producto Asc;

#SELECT table_schema as 'Database',
#table_name as 'Tbles',
#round(((data_length + index_length)/ 1024 / 1024),2) 'Size in Mb'
#FROM information_schema.TABLES  ORDER BY( data_length + index_length );

#USE empresa;
CREATE TABLE configuracion(
id INT (10) primary key not null auto_increment,
empresa VARCHAR(40) not null,
direccion varchar(50) not null,
propietario varchar(60) not null,
iva INT(2) not null,
ruc VARCHAR(13) not null,
telefono VARCHAR(10),
contrasena varchar(100)
);
#alter table configuracion ADD COLUMN id INT(10);
#select * from usuario;
#delete from usuario where cedula_usuario="1105970717";
INSERT INTO configuracion(empresa, direccion, propietario, iva, ruc, telefono, contrasena) VALUES("Empresa", "Dirección", "Propietario" , 12, "1111111111111", "0000000", md5('servifac'));

#CREATE TABLE devolucion(
#id_devolucion INT(10),
#codigo VARCHAR(13) not null,
#precio DOUBLE(7,2) not null,
#fecha VARCHAR(10),
#comentario VARCHAR(500) not null
#);
#describe producto;
#select distinct str_to_date(fecha,'%d/%m/%Y') as fecha FROM venta;



CREATE TABLE usuario(
cedula_usuario VARCHAR(10) PRIMARY KEY,
nombre VARCHAR(50) NOT NULL UNIQUE,
usuario VARCHAR(15) NOT NULL UNIQUE,
contrasena VARCHAR(128) NOT NULL,
rol INT(10) ,
estado boolean default true,
login boolean DEFAULT false,
maquina VARCHAR(30) not null
);

#ALTER TABLE usuario change maquina maquina VARCHAR (20) default NULL;
#ALTER TABLE usuario ADD COLUMN maquina VARCHAR (20) default NULL;
#ALTER TABLE usuario add COLUMN estado boolean default true;
select * from clientes;
update clientes set monto_aprobado=100.00 where cedula_cliente="1111111122222";
CREATE TABLE clientes(
nombres VARCHAR(50) NOT NULL UNIQUE,
cedula_cliente VARCHAR(13) PRIMARY KEY,#13 digitos debido a que puede agregarse 001(RUC)
telefono VARCHAR(10) NOT NULL,
deuda DECIMAL(10,2) NOT NULL DEFAULT 0.00,
direccion TEXT NOT NULL,
estado boolean default true,
credito boolean default false,
autorizado_por varchar(10) default "N/A",
modificado_por varchar(10) default "N/A",
monto_aprobado decimal(10,2) default 0.00
);
use empresa;
ALTER table clientes add column credito boolean default false;

INSERT INTO clientes (nombres, cedula_cliente, telefono, deuda, direccion, estado) VALUES ("CONSUMIDOR FINAL","1111111111111", " ", 0.00, " ", 1);#IMPORTANTE

CREATE TABLE producto(
nombre_Producto VARCHAR(40) NOT NULL UNIQUE,
codigo VARCHAR (13) PRIMARY KEY,
precio_Compra DECIMAL (10,2) NOT NULL,
precio DECIMAL(10,2) NOT NULL,
ganancia DECIMAL(10,2) NOT NULL,
ganancia_Mayor DECIMAL(10,2) NOT NULL,
stock BOOLEAN DEFAULT TRUE,
iva Varchar(3) NOT NULL,
id_categoria INT (3) NOT NULL,
precio_Mayor DECIMAL(10,2),
id_ubicacion INT(3),
cantidad INT(7),
cantidad_Minima INT (3),
ruc VARCHAR(15),
imagen_codigo MEDIUMBLOB default null,
imagen_producto MEDIUMBLOB default null,
id_bodega INT(3)
);
#alter table producto change imagen_codigo imagen_codigo MEDIUMBLOB default null;

CREATE TABLE ubicacion(
id_ubicacion INT (3) AUTO_INCREMENT PRIMARY KEY,
nombre_ubicacion VARCHAR(20) UNIQUE NOT NULL
);
CREATE TABLE categoria(
id_categoria INT (3) AUTO_INCREMENT PRIMARY KEY,
nombre_categoria VARCHAR(20) UNIQUE NOT NULL
);

CREATE TABLE proveedores(
empresa VARCHAR(50) NOT NULL UNIQUE,
ruc VARCHAR(15) NOT NULL PRIMARY KEY,
nombre_cuenta VARCHAR(30) NOT NULL,
tipo_cuenta VARCHAR(20) NOT NULL,
numero_cuenta VARCHAR(15),
deuda DECIMAL(10,2) NOT NULL DEFAULT 0.00,
telefono VARCHAR(10),
estado boolean default true
);

INSERT INTO proveedores (empresa,ruc,nombre_cuenta,tipo_cuenta,numero_cuenta,telefono) VALUES("(Vacio)","(Vacio)","(Vacio)","(Vacio)","(Vacio)","(Vacio)");


CREATE TABLE venta(
id_Venta INT(10) PRIMARY KEY AUTO_INCREMENT,
total_Venta DECIMAL(10,2) NOT NULL,
valor_Cancelado DECIMAL(10,2) NOT NULL,
fecha VARCHAR(10),
cedula_usuario VARCHAR(10) NOT NULL
);

CREATE TABLE detalle_venta(
cedula_cliente VARCHAR(13),
cantidad INT(7),
codigo VARCHAR (13),
precio_venta DECIMAL(10,2) NOT NULL,
usuario VARCHAR(15),
id_venta INT(10)
);

CREATE TABLE bodega(
id_bodega INT (3) PRIMARY KEY AUTO_INCREMENT,
nombre_bodega VARCHAR (20) NOT NULL UNIQUE
);

INSERT INTO bodega (nombre_bodega) VALUES("BODEGA 1");

CREATE TABLE existenciasBodega(
id_bodega INT (3),
codigo VARCHAR(13),
cantidad INT (4)
);

CREATE TABLE abono_cliente(
id_Abono INT(10) PRIMARY KEY AUTO_INCREMENT,
cedula_cliente VARCHAR(13) NOT NULL,
cedula_usuario VARCHAR(15) NOT NULL,
monto_abono DECIMAL(10,2) NOT NULL,
fecha VARCHAR(10) NOT NULL
);
-- Revisar pago_proveedor

CREATE TABLE pago_proveedor(
id_pago INT(6) PRIMARY KEY AUTO_INCREMENT,
ruc VARCHAR(15),
cedula_usuario VARCHAR(10) NOT NULL,
monto_cancelado DECIMAL(10,2) NOT NULL,
fecha VARCHAR (10),
tipo VARCHAR(7),
descripcion TEXT
);

ALTER TABLE producto
ADD CONSTRAINT produbic_fk
FOREIGN KEY (id_ubicacion) REFERENCES ubicacion(id_ubicacion);

ALTER TABLE detalle_venta
ADD CONSTRAINT cedula_fk 
FOREIGN KEY (cedula_cliente) REFERENCES clientes(cedula_cliente);

ALTER TABLE detalle_venta
ADD CONSTRAINT codigo_fk 
FOREIGN KEY (codigo) REFERENCES producto(codigo);

ALTER TABLE detalle_venta
ADD CONSTRAINT venta_fk 
FOREIGN KEY (id_venta) REFERENCES venta(id_venta);

ALTER TABLE venta
ADD CONSTRAINT usuario_fk
FOREIGN KEY (cedula_usuario) REFERENCES usuario(cedula_usuario);

ALTER TABLE existenciasBodega
ADD constraint idBodega_fk
FOREIGN KEY (id_bodega) REFERENCES bodega(id_bodega);

ALTER TABLE producto
ADD CONSTRAINT bodega_fk
FOREIGN KEY (id_bodega) REFERENCES bodega(id_bodega);

ALTER TABLE existenciasBodega
ADD CONSTRAINT codigoBodega_fk
FOREIGN KEY (codigo) REFERENCES producto(codigo);

ALTER TABLE abono_cliente
ADD CONSTRAINT abono_fk
FOREIGN KEY (cedula_cliente) REFERENCES clientes(cedula_cliente);

ALTER TABLE pago_proveedor
ADD CONSTRAINT ruc_fk
FOREIGN KEY (ruc) REFERENCES proveedores(ruc);

ALTER TABLE pago_proveedor
ADD CONSTRAINT cedulausuario_fk
FOREIGN KEY (cedula_usuario) REFERENCES usuario(cedula_usuario);

ALTER TABLE producto
ADD CONSTRAINT rucProducto_fk
FOREIGN KEY (ruc) REFERENCES proveedores(ruc);

ALTER TABLE producto
ADD CONSTRAINT categoria_fk
FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria);