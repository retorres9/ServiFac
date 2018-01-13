CREATE DATABASE empresa;

USE empresa;
select * from producto;

CREATE TABLE usuario(
cedula_usuario VARCHAR(10) PRIMARY KEY,
nombre VARCHAR(50) NOT NULL UNIQUE,
usuario VARCHAR(15) NOT NULL UNIQUE,
contrasena VARCHAR(128) NOT NULL,
rol INT(10) 
);
select * from usuario;

CREATE TABLE clientes(
nombres VARCHAR(50) NOT NULL,
cedula_cliente VARCHAR(13) PRIMARY KEY,#13 digitos debido a que puede agregarse 001(RUC)
telefono INT(10) NOT NULL,#ANALIZAR cambiar a varchar
deuda DECIMAL(7,2) NOT NULL DEFAULT 0.00,
direccion TEXT NOT NULL
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

CREATE TABLE proveedores(
empresa VARCHAR(50) NOT NULL UNIQUE,
ruc VARCHAR(13) NOT NULL PRIMARY KEY,
nombre_cuenta VARCHAR(30) NOT NULL,
tipo_cuenta VARCHAR(20) NOT NULL,
numero_cuenta VARCHAR(15),
deuda DECIMAL(7,2) NOT NULL DEFAULT 0.00,
telefono VARCHAR(10)
);

CREATE TABLE venta(
id_Venta INT(10) PRIMARY KEY AUTO_INCREMENT,
total_Venta DECIMAL(7,2) NOT NULL,
valor_Cancelado DECIMAL(7,2) NOT NULL,
fecha DATE,
cedula_usuario INT(150) NOT NULL
);

CREATE TABLE detalle_venta(
cedula_cliente INT(10),
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

CREATE TABLE existenciasBodega(
id_bodega INT (3),
codigo VARCHAR(13),
cantidad INT (4)
);

CREATE TABLE abono_cliente(
id_Abono INT(10) PRIMARY KEY AUTO_INCREMENT,
cedula INT(10) NOT NULL,
usuario VARCHAR(15) NOT NULL,
monto_abono DECIMAL(7,2) NOT NULL,
fecha DATE
);
-- Revisar pago_proveedor
CREATE TABLE pago_proveedor(
id_pago INT(6) PRIMARY KEY AUTO_INCREMENT,
empresa VARCHAR(15),
usuario VARCHAR(15) NOT NULL,
monto_cancelado DECIMAL(7,2) NOT NULL,
fecha DATE,
tipo VARCHAR(7)
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