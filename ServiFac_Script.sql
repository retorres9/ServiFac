CREATE DATABASE empresa;

USE empresa;

SELECT * FROM usuario;


CREATE TABLE usuario(
Cedula_Usuario INT(10) PRIMARY KEY,
Nombre VARCHAR(30) NOT NULL,
Usuario VARCHAR(15) NOT NULL,
Contrasena VARCHAR(128) NOT NULL,
Rol INT(10) 
);

CREATE TABLE clientes(
Nombres VARCHAR(30) NOT NULL,
Cedula_Cliente VARCHAR(13) PRIMARY KEY,#13 digitos debido a que puede agregarse 001(RUC)
Telefono INT(10) NOT NULL,#ANALIZAR cambiar a varchar
Deuda DECIMAL(4,2) NOT NULL DEFAULT 0.00,
Direccion TEXT NOT NULL,
Descuento INT(2) DEFAULT 0
);

CREATE TABLE producto(
Nombre_Producto VARCHAR(25) NOT NULL,
Codigo VARCHAR (13) PRIMARY KEY,
Precio DECIMAL(6,2) NOT NULL,
Precio_Compra DECIMAL (6,2) NOT NULL,
Ganancia DECIMAL(3,2) NOT NULL,
Stock BOOLEAN DEFAULT TRUE,
Categoria INT (3) NOT NULL,
Precio_Mayor DECIMAL(6,2),
id_ubicacion VARCHAR(10),
Cantidad INT(7),
Cantidad_Minima INT (3),
RUC VARCHAR(15),
Imagen_Codigo BLOB
);

CREATE TABLE proveedores(
Empresa VARCHAR(15) NOT NULL,
RUC VARCHAR(13) NOT NULL PRIMARY KEY,
Nombre_Cuenta VARCHAR(20) NOT NULL,
Tipo_Cuenta VARCHAR(20) NOT NULL,
Numero_Cuenta VARCHAR(15),
Deuda DECIMAL(4,2) NOT NULL DEFAULT 0.00,
Telefono INT(10)
);

CREATE TABLE venta(
Id_Venta INT(10) PRIMARY KEY AUTO_INCREMENT,
Total_Venta DECIMAL(7,2) NOT NULL,
Valor_Cancelado DECIMAL(7,2) NOT NULL,
Fecha DATE,
Cedula_Usuario INT(10) NOT NULL
);

CREATE TABLE detalle_venta(
Cedula_Cliente INT(10),
Cantidad INT(7),
Codigo VARCHAR (13),
Precio_Venta DECIMAL(6,2) NOT NULL,
Usuario VARCHAR(15),
Id_Venta INT(10)
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
Id_Abono INT(10) PRIMARY KEY AUTO_INCREMENT,
Cedula INT(10) NOT NULL,
Usuario VARCHAR(15) NOT NULL,
Monto_Abono DECIMAL(7,2) NOT NULL,
Fecha DATE
);
-- Revisar pago_proveedor
CREATE TABLE pago_proveedor(
Id_Pago INT(6) PRIMARY KEY AUTO_INCREMENT,
Empresa VARCHAR(15),
Usuario VARCHAR(15) NOT NULL,
Monto_Cancelado DECIMAL(7,2) NOT NULL,
Fecha DATE,
Tipo VARCHAR(7)
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