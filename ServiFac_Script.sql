CREATE DATABASE empresa;

USE empresa;

SELECT * FROM usuario;


CREATE TABLE usuario(
cedula_usuario INT(10) PRIMARY KEY,
nombre VARCHAR(30) NOT NULL,
usuario VARCHAR(15) NOT NULL,
contrasena VARCHAR(128) NOT NULL,
rol INT(10) 
);

CREATE TABLE clientes(
nombres VARCHAR(30) NOT NULL,
cedula_cliente VARCHAR(13) PRIMARY KEY,#13 digitos debido a que puede agregarse 001(RUC)
telefono INT(10) NOT NULL,#ANALIZAR cambiar a varchar
deuda DECIMAL(4,2) NOT NULL DEFAULT 0.00,
direccion TEXT NOT NULL,
descuento INT(2) DEFAULT 0
);

DROP TABLE producto;

CREATE TABLE producto(
nombre_Producto VARCHAR(25) NOT NULL,
codigo VARCHAR (13) PRIMARY KEY,
precio DECIMAL(6,2) NOT NULL,
precio_Compra DECIMAL (6,2) NOT NULL,
ganancia DECIMAL(3,2) NOT NULL,
ganancia_Mayor DECIMAL(3,2) NOT NULL,
stock BOOLEAN DEFAULT TRUE,
id_categoria INT (3) NOT NULL,
precio_Mayor DECIMAL(6,2),
id_ubicacion VARCHAR(10),
cantidad INT(7),
cantidad_Minima INT (3),
ruc VARCHAR(15),
imagen_codigo BLOB,
imagen_producto BLOB,
id_bodega INT(3)
);

CREATE TABLE ubicacion(
id_ubicacion INT (3),
nombre_ubicacion VARCHAR(20)
);

CREATE TABLE categoria(
id_categoria INT (3),
nombre_categoria VARCHAR(20)
);

CREATE TABLE proveedores(
empresa VARCHAR(15) NOT NULL,
ruc VARCHAR(13) NOT NULL PRIMARY KEY,
nombre_cuenta VARCHAR(20) NOT NULL,
tipo_cuenta VARCHAR(20) NOT NULL,
numero_cuenta VARCHAR(15),
deuda DECIMAL(4,2) NOT NULL DEFAULT 0.00,
telefono INT(10)
);

CREATE TABLE venta(
id_Venta INT(10) PRIMARY KEY AUTO_INCREMENT,
total_Venta DECIMAL(7,2) NOT NULL,
valor_Cancelado DECIMAL(7,2) NOT NULL,
fecha DATE,
cedula_usuario INT(10) NOT NULL
);

CREATE TABLE detalle_venta(
cedula_cliente INT(10),
cantidad INT(7),
codigo VARCHAR (13),
precio_venta DECIMAL(6,2) NOT NULL,
usuario VARCHAR(15),
id_venta INT(10)
);

CREATE TABLE bodega(
id_bodega INT (3) PRIMARY KEY,
nombre_bodega VARCHAR (20) NOT NULL
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