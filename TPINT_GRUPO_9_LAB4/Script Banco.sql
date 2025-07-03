-- Crear la base de datos
CREATE DATABASE IF NOT EXISTS banco;
USE banco;

-- TABLA GENEROS
CREATE TABLE generos (
    id_genero INT PRIMARY KEY AUTO_INCREMENT,
    descripcion VARCHAR(50) NOT NULL UNIQUE,
    activo BOOLEAN DEFAULT TRUE
);

-- TABLA PAISES
CREATE TABLE paises (
    id_pais INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL UNIQUE, 
    activo BOOLEAN DEFAULT TRUE
);

-- TABLA PROVINCIAS 
CREATE TABLE provincias (
    id_provincia INT PRIMARY KEY AUTO_INCREMENT,
    nombre_provincia VARCHAR(100) NOT NULL,
    activo BOOLEAN DEFAULT TRUE
);

-- TABLA LOCALIDADES 
CREATE TABLE localidades (
    id_localidad INT PRIMARY KEY AUTO_INCREMENT,
    nombre_localidad VARCHAR(100) NOT NULL,
    id_provincia INT NOT NULL,
    activo BOOLEAN DEFAULT TRUE,
    
    FOREIGN KEY (id_provincia) REFERENCES provincias(id_provincia)
);

-- TABLA USUARIOS EXTENDIDA
CREATE TABLE usuarios (
    id_usuario INT PRIMARY KEY AUTO_INCREMENT,
    dni VARCHAR(20) NULL UNIQUE,
    nombre_usuario VARCHAR(50) NOT NULL UNIQUE,
    contrasena VARCHAR(255) NOT NULL,
    rol VARCHAR(15) NOT NULL, -- 'admin' o 'cliente'
    correo_electronico VARCHAR(100) NULL UNIQUE,
    telefono VARCHAR(50) NOT NULL,
    id_genero INT NOT NULL,
    id_pais INT NOT NULL,
    fecha_nacimiento DATE,
    direccion VARCHAR(200),
    id_localidad INT,
    id_provincia INT,
    fecha_creacion DATETIME DEFAULT CURRENT_TIMESTAMP,
    ultimo_acceso DATETIME,
    activo BOOLEAN DEFAULT TRUE,

    FOREIGN KEY (id_genero) REFERENCES generos(id_genero),
    FOREIGN KEY (id_pais) REFERENCES paises(id_pais),
    FOREIGN KEY (id_localidad) REFERENCES localidades(id_localidad),
    FOREIGN KEY (id_provincia) REFERENCES provincias(id_provincia),

    CONSTRAINT chk_rol CHECK (rol IN ('admin', 'cliente'))
);

-- TABLA CLIENTES
CREATE TABLE clientes (
    id_cliente INT PRIMARY KEY AUTO_INCREMENT,
    dni VARCHAR(20) NOT NULL UNIQUE,
    cuil VARCHAR(20) NOT NULL UNIQUE,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    id_genero INT NOT NULL,
    id_pais INT NOT NULL, 
    fecha_nacimiento DATE NOT NULL,
    direccion VARCHAR(200) NOT NULL,
    id_localidad INT NOT NULL,
    id_provincia INT NOT NULL,
    correo_electronico VARCHAR(150) NOT NULL UNIQUE,
	contrasena VARCHAR(255) NOT NULL,
    telefono VARCHAR(50) NOT NULL, 
    usuario VARCHAR(50) NOT NULL UNIQUE,
    fecha_alta DATETIME DEFAULT CURRENT_TIMESTAMP,
    activo BOOLEAN DEFAULT TRUE,
    
    FOREIGN KEY (id_localidad) REFERENCES localidades(id_localidad),
    FOREIGN KEY (id_provincia) REFERENCES provincias(id_provincia),
    FOREIGN KEY (id_genero) REFERENCES generos(id_genero),
    FOREIGN KEY (id_pais) REFERENCES paises(id_pais)
);

-- TABLA TIPOS DE CUENTA
CREATE TABLE tipos_cuenta (
    id_tipo_cuenta INT PRIMARY KEY AUTO_INCREMENT,
    descripcion VARCHAR(50) NOT NULL,
    activo BOOLEAN DEFAULT TRUE
);

-- TABLA CUENTAS
CREATE TABLE cuentas (
    id_cuenta INT PRIMARY KEY AUTO_INCREMENT,
    numero_cuenta VARCHAR(20) NOT NULL UNIQUE,
    cbu VARCHAR(22) NOT NULL UNIQUE, -- CBU tiene 22 dígitos
    id_cliente INT NOT NULL,
    id_tipo_cuenta INT NOT NULL,
    saldo DECIMAL(15,2) NOT NULL DEFAULT 10000.00, -- Monto inicial fijo
    fecha_creacion DATETIME DEFAULT CURRENT_TIMESTAMP,
    activo BOOLEAN DEFAULT TRUE,
    
    FOREIGN KEY (id_cliente) REFERENCES clientes(id_cliente),
    FOREIGN KEY (id_tipo_cuenta) REFERENCES tipos_cuenta(id_tipo_cuenta)
);

-- TABLA TIPOS DE MOVIMIENTO 
CREATE TABLE tipos_movimiento (
    id_tipo_movimiento INT PRIMARY KEY AUTO_INCREMENT,
    descripcion VARCHAR(50) NOT NULL,
    activo BOOLEAN DEFAULT TRUE
);

-- TABLA MOVIMIENTOS
CREATE TABLE movimientos (
    id_movimiento INT PRIMARY KEY AUTO_INCREMENT,
    id_cuenta INT NOT NULL,
    id_tipo_movimiento INT NOT NULL,
    importe DECIMAL(15,2) NOT NULL, -- Positivo para ingresos, negativo para egresos
    detalle VARCHAR(255) NOT NULL,
    fecha_movimiento DATETIME DEFAULT CURRENT_TIMESTAMP,
    saldo_anterior DECIMAL(15,2) NOT NULL,
    saldo_posterior DECIMAL(15,2) NOT NULL,
    id_cuenta_destino INT NULL, -- Para transferencias
    numero_referencia VARCHAR(50), -- Para identificar operaciones relacionadas
    activo BOOLEAN DEFAULT TRUE,
    
    FOREIGN KEY (id_cuenta) REFERENCES cuentas(id_cuenta),
    FOREIGN KEY (id_tipo_movimiento) REFERENCES tipos_movimiento(id_tipo_movimiento),
    FOREIGN KEY (id_cuenta_destino) REFERENCES cuentas(id_cuenta)
);

-- TABLA ESTADOS DE PRÉSTAMO 
CREATE TABLE estados_prestamo (
    id_estado_prestamo INT PRIMARY KEY AUTO_INCREMENT,
    descripcion VARCHAR(50) NOT NULL,
    activo BOOLEAN DEFAULT TRUE
);

-- TABLA PRÉSTAMOS
CREATE TABLE prestamos (
    id_prestamo INT PRIMARY KEY AUTO_INCREMENT,
    id_cliente INT NOT NULL,
    id_cuenta_deposito INT NOT NULL,
    importe_solicitado DECIMAL(15,2) NOT NULL,
    importe_total DECIMAL(15,2) NOT NULL,
    plazo_meses INT NOT NULL,
    monto_cuota DECIMAL(15,2) NOT NULL,
    fecha_solicitud DATETIME DEFAULT CURRENT_TIMESTAMP,
    fecha_resolucion DATETIME NULL,
    id_estado_prestamo INT NOT NULL DEFAULT 1, -- Pendiente por defecto
    observaciones TEXT,
    activo BOOLEAN DEFAULT TRUE,
    
    FOREIGN KEY (id_cliente) REFERENCES clientes(id_cliente),
    FOREIGN KEY (id_cuenta_deposito) REFERENCES cuentas(id_cuenta),
    FOREIGN KEY (id_estado_prestamo) REFERENCES estados_prestamo(id_estado_prestamo)
);

-- TABLA CUOTAS
CREATE TABLE cuotas (
    id_cuota INT PRIMARY KEY AUTO_INCREMENT,
    id_prestamo INT NOT NULL,
    numero_cuota INT NOT NULL,
    monto_cuota DECIMAL(15,2) NOT NULL,
    fecha_vencimiento DATE NOT NULL,
    fecha_pago DATETIME NULL, -- NULL = no pagada
    id_cuenta_pago INT NULL,
    activo BOOLEAN DEFAULT TRUE,
    
    FOREIGN KEY (id_prestamo) REFERENCES prestamos(id_prestamo),
    FOREIGN KEY (id_cuenta_pago) REFERENCES cuentas(id_cuenta),
    UNIQUE KEY unique_cuota_prestamo (id_prestamo, numero_cuota)
);

-- ÍNDICES PARA OPTIMIZACIÓN
CREATE INDEX idx_clientes_dni ON clientes(dni);
CREATE INDEX idx_clientes_cuil ON clientes(cuil);
CREATE INDEX idx_clientes_email ON clientes(correo_electronico);
CREATE INDEX idx_clientes_usuario ON clientes(usuario);
CREATE INDEX idx_cuentas_numero ON cuentas(numero_cuenta);
CREATE INDEX idx_cuentas_cbu ON cuentas(cbu);
CREATE INDEX idx_cuentas_cliente ON cuentas(id_cliente);
CREATE INDEX idx_movimientos_cuenta ON movimientos(id_cuenta);
CREATE INDEX idx_movimientos_cuenta_destino ON movimientos(id_cuenta_destino); 
CREATE INDEX idx_movimientos_fecha ON movimientos(fecha_movimiento);
CREATE INDEX idx_prestamos_cliente ON prestamos(id_cliente);
CREATE INDEX idx_prestamos_estado ON prestamos(id_estado_prestamo);
CREATE INDEX idx_cuotas_prestamo ON cuotas(id_prestamo);
CREATE INDEX idx_cuotas_vencimiento ON cuotas(fecha_vencimiento);

-- TRIGGERS PARA VALIDACIONES Y AUTOMATIZACIÓN

-- Trigger para validar máximo 3 cuentas por cliente en INSERT
DELIMITER //
CREATE TRIGGER validar_max_cuentas_cliente
    BEFORE INSERT ON cuentas
    FOR EACH ROW
BEGIN
    DECLARE cuenta_count INT;
    
    SELECT COUNT(*) INTO cuenta_count 
    FROM cuentas 
    WHERE id_cliente = NEW.id_cliente AND activo = TRUE;
    
    IF cuenta_count >= 3 THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Un cliente no puede tener más de 3 cuentas activas';
    END IF;
END//
DELIMITER ;

-- Trigger para validar máximo 3 cuentas por cliente en UPDATE
DELIMITER //
CREATE TRIGGER validar_max_cuentas_update
    BEFORE UPDATE ON cuentas
    FOR EACH ROW
BEGIN
    DECLARE cuenta_count INT;
    IF NEW.activo = TRUE THEN
        SELECT COUNT(*) INTO cuenta_count 
        FROM cuentas 
        WHERE id_cliente = NEW.id_cliente AND activo = TRUE AND id_cuenta != NEW.id_cuenta;
        IF cuenta_count >= 3 THEN
            SIGNAL SQLSTATE '45000' 
            SET MESSAGE_TEXT = 'Un cliente no puede tener más de 3 cuentas activas';
        END IF;
    END IF;
END//
DELIMITER ;

-- Trigger para generar número de cuenta único
DELIMITER //
CREATE TRIGGER generar_numero_cuenta
    BEFORE INSERT ON cuentas
    FOR EACH ROW
BEGIN
    IF NEW.numero_cuenta IS NULL OR NEW.numero_cuenta = '' THEN
        SET NEW.numero_cuenta = CONCAT('CTA', LPAD(NEW.id_cliente, 6, '0'), LPAD((SELECT COALESCE(MAX(id_cuenta), 0) + 1 FROM cuentas), 6, '0'));
    END IF;
END//
DELIMITER ;

-- Trigger para generar CBU único
DELIMITER //
CREATE TRIGGER generar_cbu
    BEFORE INSERT ON cuentas
    FOR EACH ROW
BEGIN
    IF NEW.cbu IS NULL OR NEW.cbu = '' THEN
        SET NEW.cbu = CONCAT('000', LPAD(NEW.id_cliente, 8, '0'), LPAD((SELECT COALESCE(MAX(id_cuenta), 0) + 1 FROM cuentas), 11, '0'));
    END IF;
END//
DELIMITER ;

-- Trigger para actualizar cuentas.saldo y validar saldo no negativo
DELIMITER //
CREATE TRIGGER actualizar_saldo_cuenta
    AFTER INSERT ON movimientos
    FOR EACH ROW
BEGIN
    DECLARE nuevo_saldo DECIMAL(15,2);
    
    -- Calcular nuevo saldo
    SET nuevo_saldo = (SELECT saldo FROM cuentas WHERE id_cuenta = NEW.id_cuenta) + NEW.importe;
    
    -- Validar saldo no negativo
    IF nuevo_saldo < 0 THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'El saldo de la cuenta no puede ser negativo';
    END IF;
    
    -- Actualizar saldo en cuentas
    UPDATE cuentas 
    SET saldo = nuevo_saldo 
    WHERE id_cuenta = NEW.id_cuenta;
END//
DELIMITER ;





USE banco;


INSERT INTO generos (descripcion) VALUES 
('Masculino'),
('Femenino'),
('Reservado');

INSERT INTO paises (nombre) VALUES
('Chile'),
('Paraguay'),
('Bolivia'),
('Perú'),
('Venezuela'),
('Colombia'),
('Ecuador'),
('México'),
('Estados Unidos'),
('Canadá'),
('España'),
('Italia'),
('Argentina'),
('Brasil'),
('Uruguay');

INSERT INTO provincias (nombre_provincia) VALUES
('Tucumán'),
('Salta'),
('Misiones'),
('Entre Ríos'),
('Chaco'),
('Formosa'),
('Santa Cruz'),
('Chubut'),
('Río Negro'),
('Neuquén'),
('La Pampa'),
('Buenos Aires'),
('Córdoba'),
('Santa Fe'),
('Mendoza');

INSERT INTO localidades (nombre_localidad, id_provincia) VALUES
('San Miguel de Tucumán', 5),
('Salta Capital', 6),
('Posadas', 7),
('Paraná', 8),
('Resistencia', 9),
('Formosa Capital', 10),
('Río Gallegos', 11),
('Comodoro Rivadavia', 12),
('Viedma', 13),
('Neuquén Capital', 14),
('CABA', 1),
('La Plata', 1),
('Córdoba Capital', 2),
('Rosario', 3),
('Santa Rosa', 15);

INSERT INTO usuarios (
    dni, nombre_usuario, contrasena, rol, correo_electronico, telefono, 
    id_genero, id_pais, fecha_nacimiento, direccion,
    id_localidad, id_provincia
) VALUES
('30111111', 'Abraham', '$2a$12$hF1zkgTjHhY4VeRxhtlvj.s347nI7kFIAMo4/muzYYzwMlBxijw8q', 'admin', 'abraham@banco.com', '1111111111', 1, 1, '1980-01-01', 'Av. Siempre Viva 742', 1, 1),
('30111112', 'Oscar',   '$2a$12$2rz0RveHvfy1AD81xJYxPeC030bg9jdx1QdpVc9OTUskgJdrCYQr6', 'admin', 'oscar@banco.com',   '2222222222', 2, 1, '1985-05-05', 'Calle del Software 404', 1, 1),
('30123456', 'user1',    '$2a$12$M6dO9bO42co2Rr0JwoR.DujbtZDKxA/Q.HxRFJ6j2jvjXvU/gPuBm', 'cliente', 'user1@ejemplo.com', '3333333333', 1, 1, '1990-01-01', 'Calle Falsa 123', 1, 1),
('30987654', 'user2',    '$2a$12$3k/Ly9q2VAnI4/oaufrlzOvm.eftJuFEo3KvpxFH8QMMl1fMkWepK', 'cliente', 'user2@ejemplo.com', '3444444444', 2, 1, '1991-02-02', 'Calle Falsa 124', 1, 1),
('30111222', 'user3',    '$2a$12$M6dO9bO42co2Rr0JwoR.DujbtZDKxA/Q.HxRFJ6j2jvjXvU/gPuBm', 'cliente', 'user3@ejemplo.com', '3555555555', 3, 1, '1992-03-03', 'Calle Falsa 125', 1, 1),
('30222333', 'user4',    'dummyhash', 'admin', 'user4@ejemplo.com', '3666666666', 1, 1, '1993-04-04', 'Calle Falsa 126', 1, 1),
('30123460', 'user5',    'dummyhash', 'admin', 'user5@ejemplo.com', '3777777777', 2, 1, '1994-05-05', 'Calle Falsa 127', 1, 1),
('30123461', 'user6',    'dummyhash', 'admin', 'user6@ejemplo.com', '3888888888', 3, 1, '1995-06-06', 'Calle Falsa 128', 1, 1),
('30123462', 'user7',    'dummyhash', 'admin', 'user7@ejemplo.com', '3999999999', 1, 1, '1996-07-07', 'Calle Falsa 129', 1, 1),
('30123463', 'user8',    'dummyhash', 'admin', 'user8@ejemplo.com', '4000000000', 2, 1, '1997-08-08', 'Calle Falsa 130', 1, 1),
('30123464', 'user9',    'dummyhash', 'admin', 'user9@ejemplo.com', '4111111111', 3, 1, '1998-09-09', 'Calle Falsa 131', 1, 1),
('30123465', 'user10',   'dummyhash', 'admin', 'user10@ejemplo.com', '4222222222', 1, 1, '1999-10-10', 'Calle Falsa 132', 1, 1),
('30123466', 'user11',   'dummyhash', 'admin', 'user11@ejemplo.com', '4333333333', 2, 1, '2000-11-11', 'Calle Falsa 133', 1, 1),
('30123467', 'user12',   'dummyhash', 'admin', 'user12@ejemplo.com', '4444444444', 3, 1, '2001-12-12', 'Calle Falsa 134', 1, 1),
('30123468', 'user13',   'dummyhash', 'admin', 'user13@ejemplo.com', '4555555555', 1, 1, '1990-01-01', 'Calle Falsa 135', 1, 1),
('30123469', 'user14',   'dummyhash', 'admin', 'user14@ejemplo.com', '4666666666', 2, 1, '1991-02-02', 'Calle Falsa 136', 1, 1);


INSERT INTO tipos_cuenta (descripcion) VALUES
('Cuenta Sueldo'),
('Cuenta VIP'),
('Cuenta Corriente PYME'),
('Cuenta Corriente Personal'),
('Caja de Ahorro Premium'),
('Cuenta Universitaria'),
('Cuenta Joven'),
('Cuenta Empresas'),
('Cuenta No Residencial'),
('Cuenta Monotributista'),
('Cuenta Extranjera'),
('Cuenta Para Jubilados'),
('Cuenta Solidaria');

INSERT INTO tipos_movimiento (descripcion) VALUES
('Depósito'),
('Extracción'),
('Pago de Servicios'),
('Cobro de Cheque'),
('Intereses'),
('Comisión'),
('Ajuste'),
('Transferencia Recibida'),
('Transferencia Enviada'),
('Apertura de Caja'),
('Cierre de Caja'),
('Anulación'),
('Reversión');

INSERT INTO estados_prestamo (descripcion) VALUES
('En Proceso'),
('En Evaluación'),
('Cancelado'),
('Pagado'),
('Refinanciado'),
('En Mora'),
('Suspendido'),
('Archivado'),
('Reprogramado'),
('En Revisión'),
('Aceptado'),
('Denegado');


INSERT INTO clientes (
    dni, cuil, nombre, apellido, id_genero, id_pais, fecha_nacimiento, direccion,
    id_localidad, id_provincia, correo_electronico, contrasena, telefono, usuario, fecha_alta, activo
) VALUES
('30123456', '20-30123456-3', 'Juan', 'Pérez', 1, 1, '1985-01-15', 'Calle Falsa 123', 1, 1, 'juan.perez@mail.com', 'pass1234', '1112345678', 'juanperez', NOW(), TRUE),
('30987654', '27-30987654-8', 'María', 'Gómez', 2, 2, '1990-05-20', 'Av. Siempre Viva 742', 2, 1, 'maria.gomez@mail.com', 'pass1235', '1187654321', 'mariagomez', NOW(), TRUE),
('30111222', '20-30111222-4', 'Carlos', 'Ramírez', 3, 3, '1978-09-10', 'Calle Libertad 456', 3, 2, 'carlos.ramirez@mail.com', 'pass1236', '1165432198', 'carlosramirez', NOW(), TRUE),
('30222333', '20-30222333-5', 'Ana', 'Torres', 2, 4, '1982-03-25', 'Av. Belgrano 987', 4, 3, 'ana.torres@mail.com', 'pass1237', '1143219876', 'anatorres', NOW(), TRUE),
('30333444', '20-30333444-6', 'Luis', 'Martínez', 1, 5, '1995-07-18', 'Calle Corrientes 789', 5, 4, 'luis.martinez@mail.com', 'pass1238', '1198765432', 'luismartinez', NOW(), TRUE),
('30444555', '20-30444555-7', 'Laura', 'Fernández', 2, 6, '1988-11-05', 'Av. Rivadavia 234', 6, 5, 'laura.fernandez@mail.com', 'pass1239', '1176543219', 'laurafernandez', NOW(), TRUE),
('30555666', '20-30555666-8', 'Diego', 'Sánchez', 1, 7, '1976-06-30', 'Calle San Martín 321', 7, 6, 'diego.sanchez@mail.com', 'pass1240', '1154321987', 'diegosanchez', NOW(), TRUE),
('30666777', '20-30666777-9', 'Sofía', 'González', 2, 8, '1983-12-12', 'Av. 9 de Julio 654', 8, 7, 'sofia.gonzalez@mail.com', 'pass1241', '1132198765', 'sofiagonzalez', NOW(), TRUE),
('30777888', '20-30777888-0', 'Martín', 'López', 1, 9, '1992-08-08', 'Calle Moreno 1234', 9, 8, 'martin.lopez@mail.com', 'pass1242', '1121987654', 'martinlopez', NOW(), TRUE),
('30888999', '20-30888999-1', 'Julieta', 'Ramón', 2, 10, '1987-04-21', 'Av. Santa Fe 432', 10, 9, 'julieta.ramon@mail.com', 'pass1243', '1119876543', 'julietaramon', NOW(), TRUE),
('30999000', '20-30999000-2', 'Federico', 'Castro', 1, 11, '1991-10-30', 'Calle Defensa 98', 11, 10, 'federico.castro@mail.com', 'pass1244', '1198765123', 'federicocastro', NOW(), TRUE),
('31000111', '20-31000111-3', 'Camila', 'Vega', 2, 12, '1986-02-15', 'Av. Belgrano 77', 12, 11, 'camila.vega@mail.com', 'pass1245', '1187654322', 'camilavega', NOW(), TRUE),
('31111222', '20-31111222-4', 'Pablo', 'Ríos', 1, 13, '1979-07-07', 'Calle Florida 45', 13, 12, 'pablo.rios@mail.com', 'pass1246', '1176543200', 'pablorios', NOW(), TRUE),
('31222333', '20-31222333-5', 'Valentina', 'Morales', 2, 14, '1984-09-29', 'Av. Corrientes 890', 14, 13, 'valentina.morales@mail.com', 'pass1247', '1165432100', 'valentinamorales', NOW(), TRUE),
('31333444', '20-31333444-6', 'Matías', 'Salinas', 3, 15, '1993-12-01', 'Calle San Telmo 12', 15, 14, 'matias.salinas@mail.com', 'pass1248', '1154321000', 'matiassalinas', NOW(), TRUE);


INSERT INTO cuentas (numero_cuenta, cbu, id_cliente, id_tipo_cuenta, saldo, fecha_creacion, activo) VALUES
('CTA000001000001', '000000001000000000001', 1, 1, 10000.00, NOW(), TRUE),
('CTA000002000002', '000000002000000000002', 2, 2, 15000.00, NOW(), TRUE),
('CTA000003000003', '000000003000000000003', 3, 1, 20000.00, NOW(), TRUE),
('CTA000004000004', '000000004000000000004', 4, 2, 5000.00, NOW(), TRUE),
('CTA000005000005', '000000005000000000005', 5, 1, 12000.00, NOW(), TRUE),
('CTA000006000006', '000000006000000000006', 6, 2, 18000.00, NOW(), TRUE),
('CTA000007000007', '000000007000000000007', 7, 1, 22000.00, NOW(), TRUE),
('CTA000008000008', '000000008000000000008', 8, 2, 3000.00, NOW(), TRUE),
('CTA000009000009', '000000009000000000009', 9, 1, 8000.00, NOW(), TRUE),
('CTA000010000010', '000000010000000000010', 10, 2, 17000.00, NOW(), TRUE),
('CTA000011000011', '000000011000000000011', 11, 1, 11000.00, NOW(), TRUE),
('CTA000012000012', '000000012000000000012', 12, 2, 9000.00, NOW(), TRUE),
('CTA000013000013', '000000013000000000013', 13, 1, 6000.00, NOW(), TRUE),
('CTA000014000014', '000000014000000000014', 14, 2, 13000.00, NOW(), TRUE),
('CTA000015000015', '000000015000000000015', 15, 1, 14000.00, NOW(), TRUE);

INSERT INTO tipos_cuenta (descripcion) VALUES 
('Caja de Ahorro'),
('Cuenta Corriente');

INSERT INTO movimientos (id_cuenta, id_tipo_movimiento, importe, detalle, saldo_anterior, saldo_posterior, activo) VALUES
(1, 1, 10000.00, 'Alta de Cuenta', 0.00, 10000.00, TRUE),
(2, 1, 15000.00, 'Alta de Cuenta', 0.00, 15000.00, TRUE),
(3, 1, 20000.00, 'Alta de Cuenta', 0.00, 20000.00, TRUE),
(4, 1, 5000.00, 'Alta de Cuenta', 0.00, 5000.00, TRUE),
(5, 1, 12000.00, 'Alta de Cuenta', 0.00, 12000.00, TRUE),
(6, 1, 18000.00, 'Alta de Cuenta', 0.00, 18000.00, TRUE),
(7, 1, 22000.00, 'Alta de Cuenta', 0.00, 22000.00, TRUE),
(8, 1, 3000.00, 'Alta de Cuenta', 0.00, 3000.00, TRUE),
(9, 1, 8000.00, 'Alta de Cuenta', 0.00, 8000.00, TRUE),
(10, 1, 17000.00, 'Alta de Cuenta', 0.00, 17000.00, TRUE),
(11, 1, 11000.00, 'Alta de Cuenta', 0.00, 11000.00, TRUE),
(12, 1, 9000.00, 'Alta de Cuenta', 0.00, 9000.00, TRUE),
(13, 1, 6000.00, 'Alta de Cuenta', 0.00, 6000.00, TRUE),
(14, 1, 13000.00, 'Alta de Cuenta', 0.00, 13000.00, TRUE),
(15, 1, 14000.00, 'Alta de Cuenta', 0.00, 14000.00, TRUE);

INSERT INTO tipos_movimiento (descripcion) VALUES 
('Alta de Cuenta'),
('Alta de Préstamo'),
('Pago de Préstamo'),
('Transferencia');


INSERT INTO prestamos (
    id_cliente, id_cuenta_deposito, importe_solicitado, importe_total, plazo_meses,
    monto_cuota, fecha_solicitud, id_estado_prestamo, activo
) VALUES
(1, 1, 100000.00, 110000.00, 12, 9166.67, NOW(), 1, TRUE),
(2, 2, 50000.00, 55000.00, 6, 9166.67, NOW(), 2, TRUE),
(3, 3, 200000.00, 220000.00, 24, 9166.67, NOW(), 3, TRUE),
(4, 4, 75000.00, 82500.00, 12, 6875.00, NOW(), 1, TRUE),
(5, 5, 125000.00, 137500.00, 18, 7638.89, NOW(), 2, TRUE),
(6, 6, 60000.00, 66000.00, 12, 5500.00, NOW(), 3, TRUE),
(7, 7, 90000.00, 99000.00, 15, 6600.00, NOW(), 1, TRUE),
(8, 8, 30000.00, 33000.00, 6, 5500.00, NOW(), 2, TRUE),
(9, 9, 150000.00, 165000.00, 24, 6875.00, NOW(), 3, TRUE),
(10, 10, 40000.00, 44000.00, 8, 5500.00, NOW(), 1, TRUE),
(11, 11, 80000.00, 88000.00, 12, 7333.33, NOW(), 2, TRUE),
(12, 12, 35000.00, 38500.00, 7, 5500.00, NOW(), 3, TRUE),
(13, 13, 70000.00, 77000.00, 12, 6416.67, NOW(), 1, TRUE),
(14, 14, 95000.00, 104500.00, 15, 6966.67, NOW(), 2, TRUE),
(15, 15, 120000.00, 132000.00, 18, 7333.33, NOW(), 3, TRUE);


INSERT INTO estados_prestamo (descripcion) VALUES 
('Pendiente'),
('Aprobado'),
('Rechazado');

INSERT INTO cuotas (id_prestamo, numero_cuota, monto_cuota, fecha_vencimiento, activo) VALUES
(1, 1, 9166.67, DATE_ADD(CURDATE(), INTERVAL 1 MONTH), TRUE),
(2, 1, 9166.67, DATE_ADD(CURDATE(), INTERVAL 1 MONTH), TRUE),
(3, 1, 9166.67, DATE_ADD(CURDATE(), INTERVAL 1 MONTH), TRUE),
(4, 1, 6875.00, DATE_ADD(CURDATE(), INTERVAL 1 MONTH), TRUE),
(5, 1, 7638.89, DATE_ADD(CURDATE(), INTERVAL 1 MONTH), TRUE),
(6, 1, 5500.00, DATE_ADD(CURDATE(), INTERVAL 1 MONTH), TRUE),
(7, 1, 6600.00, DATE_ADD(CURDATE(), INTERVAL 1 MONTH), TRUE),
(8, 1, 5500.00, DATE_ADD(CURDATE(), INTERVAL 1 MONTH), TRUE),
(9, 1, 6875.00, DATE_ADD(CURDATE(), INTERVAL 1 MONTH), TRUE),
(10, 1, 9166.67, DATE_ADD(CURDATE(), INTERVAL 1 MONTH), TRUE),
(11, 1, 6123.45, DATE_ADD(CURDATE(), INTERVAL 1 MONTH), TRUE),
(12, 1, 7210.00, DATE_ADD(CURDATE(), INTERVAL 1 MONTH), TRUE),
(13, 1, 8499.99, DATE_ADD(CURDATE(), INTERVAL 1 MONTH), TRUE),
(14, 1, 9980.00, DATE_ADD(CURDATE(), INTERVAL 1 MONTH), TRUE),
(15, 1, 7750.00, DATE_ADD(CURDATE(), INTERVAL 1 MONTH), TRUE);