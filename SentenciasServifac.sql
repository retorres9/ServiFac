--DROP TABLE detalle_venta cascade constraints;

CREATE TABLE productos(
codigo VARCHAR2 (13) NOT NULL PRIMARY KEY,
nombre VARCHAR2 (20) NOT NULL,
precio NUMBER(3,2) NOT NULL
);

CREATE TABLE venta(
id_venta NUMBER (4) PRIMARY KEY,
total_venta NUMBER (4,2) NOT NULL,
fecha VARCHAR(8)
);

CREATE TABLE detalle_venta(
codigo VARCHAR2 (13),
id_venta NUMBER (4),
cantidad NUMBER(4),
precio_venta NUMBER (4),
cedula NUMBER (13)
);

CREATE TAble Cliente(
cedula NUMBER (11) NOT NULL PRIMARY KEY,
Nombres VARCHAR2 (10)
);

ALTER TABLE detalle_venta
ADD CONSTRAINT productos_fk FOREIGN KEY (codigo)
REFERENCES productos (codigo);

ALTER TABLE detalle_venta
ADD CONSTRAINT clientes_fk FOREIGN KEY (cedula)
REFERENCES cliente (cedula);

ALTER TABLE detalle_venta
ADD CONSTRAINT venta_fk FOREIGN KEY (id_venta)
REFERENCES venta(id_venta);

ALTER TABLE detalle_venta
ADD CONSTRAINT usuario_fk FOREIGN KEY (Usuario)
REFERENCES usuario(usuario);

INSERT INTO productos (codigo, nombre, precio) VALUES ('126', 'Atun 2', 2);

UPDATE productos SET precio = 2 WHERE nombre = 'Atun 1';

INSERT INTO Cliente (cedula, nombres) VALUES (123456, 'Roberth');

INSERT INTO venta (id_venta, total_venta, fecha) VALUES (1,2,'12-23');

INSERT INTO detalle_venta(codigo, id_venta, cantidad, precio_venta,cedula) VALUES ('126',1,1,2,123456);

SELECT p.codigo , p.nombre,v.precio_venta, v.cantidad, v.CODIGO, ven.fecha
FROM productos p, detalle_venta v, venta ven
WHERE p.codigo = v.codigo AND v.id_venta =ven.id_venta AND v.id_venta = 1;

SELECT dv.id_venta, c.nombres, v.total_venta, v.fecha
FROM detalle_venta dv, venta v, cliente c
WHERE dv.ID_VENTA = v.id_venta AND dv.cedula = c.cedula;

ALTER TABLE `proveedores` ADD UNIQUE(`Empresa`);
