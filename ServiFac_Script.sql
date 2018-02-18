CREATE DATABASE empresa;

USE empresa;
select * FROM producto;
#UPDATE producto SET cantidad = cantidad + 8 WHERE codigo = "9843579834759";
#describe usuario;

CREATE TABLE usuario(
cedula_usuario VARCHAR(10) PRIMARY KEY,
nombre VARCHAR(50) NOT NULL UNIQUE,
usuario VARCHAR(15) NOT NULL UNIQUE,
contrasena VARCHAR(128) NOT NULL,
rol INT(10) ,
estado boolean default true
);
ALTER TABLE usuario add COLUMN estado boolean default true;

CREATE TABLE clientes(
nombres VARCHAR(50) NOT NULL UNIQUE,
cedula_cliente VARCHAR(13) PRIMARY KEY,#13 digitos debido a que puede agregarse 001(RUC)
telefono VARCHAR(10) NOT NULL,#ANALIZAR cambiar a varchar
deuda DECIMAL(7,2) NOT NULL DEFAULT 0.00,
direccion TEXT NOT NULL,
estado boolean default true
);

CREATE TABLE producto(
nombre_Producto VARCHAR(40) NOT NULL UNIQUE,#
codigo VARCHAR (13) PRIMARY KEY,#
precio_Compra DECIMAL (6,2) NOT NULL,#
precio DECIMAL(7,2) NOT NULL,#
ganancia DECIMAL(7,2) NOT NULL,#
ganancia_Mayor DECIMAL(7,2) NOT NULL,#
stock BOOLEAN DEFAULT TRUE,
iva Varchar(3) NOT NULL,
id_categoria INT (3) NOT NULL,#
precio_Mayor DECIMAL(7,2),#
id_ubicacion INT(3),#
cantidad INT(7),#
cantidad_Minima INT (3),#
ruc VARCHAR(15),#
imagen_codigo BLOB,#
imagen_producto BLOB,#
id_bodega INT(3)
);

CREATE TABLE ubicacion(
id_ubicacion INT (3) AUTO_INCREMENT PRIMARY KEY,
nombre_ubicacion VARCHAR(20) UNIQUE NOT NULL
);

CREATE TABLE categoria(
id_categoria INT (3) AUTO_INCREMENT PRIMARY KEY,
nombre_categoria VARCHAR(20) UNIQUE NOT NULL
);
drop table proveedores;
CREATE TABLE proveedores(
empresa VARCHAR(50) NOT NULL UNIQUE,
ruc VARCHAR(15) NOT NULL PRIMARY KEY,
nombre_cuenta VARCHAR(30) NOT NULL,
tipo_cuenta VARCHAR(20) NOT NULL,
numero_cuenta VARCHAR(15),
deuda DECIMAL(7,2) NOT NULL DEFAULT 0.00,
telefono VARCHAR(10),
estado boolean default true
);
INSERT INTO proveedores (empresa,ruc,nombre_cuenta,tipo_cuenta,numero_cuenta,telefono) VALUES("(Vacio)","(Vacio)","(Vacio)","(Vacio)","(Vacio)","(Vacio)");

drop table venta;
CREATE TABLE venta(
id_Venta INT(10) PRIMARY KEY AUTO_INCREMENT,
total_Venta DECIMAL(7,2) NOT NULL,
valor_Cancelado DECIMAL(7,2) NOT NULL,
fecha VARCHAR(10),
cedula_usuario VARCHAR(10) NOT NULL
);
DROP TABLE detalle_venta;
CREATE TABLE detalle_venta(
cedula_cliente VARCHAR(13),
cantidad INT(7),
codigo VARCHAR (13),
precio_venta DECIMAL(8,2) NOT NULL,
usuario VARCHAR(15),
id_venta INT(10)
);

CREATE TABLE bodega(
id_bodega INT (3) PRIMARY KEY AUTO_INCREMENT,
nombre_bodega VARCHAR (20) NOT NULL UNIQUE
);
describe existenciasbodega;
CREATE TABLE existenciasBodega(
id_bodega INT (3),
codigo VARCHAR(13),
cantidad INT (4)
);
update existenciasbodega SET cantidad = 10 WHERE id_bodega = 1;
ALTER TABLE existenciasbodega CHANGE cantidad cantidad INT(7);
UPDATE clientes SET deuda = 100 WHERE nombres ="ROBERTH TORRES";
SELECT * FROM existenciasbodega;

DROP TABLE abono_cliente;
CREATE TABLE abono_cliente(
id_Abono INT(10) PRIMARY KEY AUTO_INCREMENT,
cedula VARCHAR(13) NOT NULL,
usuario VARCHAR(15) NOT NULL,
monto_abono DECIMAL(7,2) NOT NULL,
fecha VARCHAR(10) NOT NULL
);
-- Revisar pago_proveedor
#describe pago_proveedor;
CREATE TABLE pago_proveedor(
id_pago INT(6) PRIMARY KEY AUTO_INCREMENT,
ruc VARCHAR(15),
cedula_usuario VARCHAR(10) NOT NULL,
monto_cancelado DECIMAL(7,2) NOT NULL,
fecha DATE,
tipo VARCHAR(7),
descripcion TEXT
);
#ALTER TABLE pago_proveedor change fecha fecha_ varchar(10);
ALTER TABLE pago_proveedor add column descripcion TEXT;

ALTER TABLE producto
ADD CONSTRAINT produbic_fk
FOREIGN KEY (id_ubicacion) REFERENCES ubicacion(id_ubicacion);

ALTER TABLE detalle_venta
ADD CONSTRAINT cedula_fk 
FOREIGN KEY (cedula_cliente) REFERENCES clientes(cedula_cliente);
#ALTER TABLE detalle_venta drop foreign key codigo_fk;
ALTER TABLE detalle_venta
ADD CONSTRAINT codigo_fk 
FOREIGN KEY (codigo) REFERENCES producto(codigo);

ALTER TABLE detalle_venta
ADD CONSTRAINT venta_fk 
FOREIGN KEY (id_venta) REFERENCES venta(id_venta);

ALTER TABLE venta
ADD CONSTRAINT usuario_fk
FOREIGN KEY (cedula_usuario) REFERENCES usuario(cedula_usuario);

ALTER TABLE existenciasbodega
ADD constraint idBodega_fk
FOREIGN KEY (id_bodega) REFERENCES bodega(id_bodega);

ALTER TABLE producto
ADD CONSTRAINT bodega_fk
FOREIGN KEY (id_bodega) REFERENCES bodega(id_bodega);

ALTER TABLE existenciasbodega
ADD CONSTRAINT codigoBodega_fk
FOREIGN KEY (codigo) REFERENCES producto(codigo);