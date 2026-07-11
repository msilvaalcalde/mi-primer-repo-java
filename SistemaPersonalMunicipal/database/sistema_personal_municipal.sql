CREATE DATABASE IF NOT EXISTS sistema_personal_municipal;

USE sistema_personal_municipal;


-- =====================================================
-- TABLA USUARIO
-- =====================================================

CREATE TABLE IF NOT EXISTS usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre_usuario VARCHAR(50) NOT NULL UNIQUE,
    contrasena VARCHAR(100) NOT NULL,
    rol VARCHAR(30) NOT NULL,
    estado BOOLEAN NOT NULL DEFAULT TRUE
);


-- =====================================================
-- TABLA ÁREA
-- =====================================================

CREATE TABLE IF NOT EXISTS area (
    id_area INT AUTO_INCREMENT PRIMARY KEY,
    codigo_area VARCHAR(20) NOT NULL UNIQUE,
    nombre_area VARCHAR(100) NOT NULL,
    estado BOOLEAN NOT NULL DEFAULT TRUE
);


-- =====================================================
-- TABLA CARGO
-- =====================================================

CREATE TABLE IF NOT EXISTS cargo (
    id_cargo INT AUTO_INCREMENT PRIMARY KEY,
    codigo_cargo VARCHAR(20) NOT NULL UNIQUE,
    nombre_cargo VARCHAR(100) NOT NULL,
    descripcion VARCHAR(200),
    estado BOOLEAN NOT NULL DEFAULT TRUE
);


-- =====================================================
-- TABLA TRABAJADOR
-- =====================================================

CREATE TABLE IF NOT EXISTS trabajador (
    id_trabajador INT AUTO_INCREMENT PRIMARY KEY,

    tipo_documento VARCHAR(10) NOT NULL,
    numero_documento VARCHAR(20) NOT NULL UNIQUE,

    nombres VARCHAR(100) NOT NULL,
    apellido_paterno VARCHAR(100) NOT NULL,
    apellido_materno VARCHAR(100) NOT NULL,

    celular VARCHAR(9) NOT NULL,
    correo VARCHAR(150) NOT NULL,

    codigo_trabajador VARCHAR(20) NOT NULL UNIQUE,
    fecha_ingreso DATE NOT NULL,
    regimen_laboral VARCHAR(20) NOT NULL,

    tipo_trabajador VARCHAR(30) NOT NULL,

    sueldo_mensual DECIMAL(10,2),
    bonificacion DECIMAL(10,2),

    jornal_diario DECIMAL(10,2),
    dias_trabajados INT,

    estado BOOLEAN NOT NULL DEFAULT TRUE,

    id_area INT NOT NULL,
    id_cargo INT NOT NULL,

    CONSTRAINT fk_trabajador_area
        FOREIGN KEY (id_area)
        REFERENCES area(id_area),

    CONSTRAINT fk_trabajador_cargo
        FOREIGN KEY (id_cargo)
        REFERENCES cargo(id_cargo)
);


-- =====================================================
-- TABLA CONTRATO
-- =====================================================

CREATE TABLE IF NOT EXISTS contrato (
    id_contrato INT AUTO_INCREMENT PRIMARY KEY,

    codigo_contrato VARCHAR(20) NOT NULL UNIQUE,

    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NOT NULL,

    estado_contrato BOOLEAN NOT NULL DEFAULT TRUE,

    id_trabajador INT NOT NULL UNIQUE,

    CONSTRAINT fk_contrato_trabajador
        FOREIGN KEY (id_trabajador)
        REFERENCES trabajador(id_trabajador)
        ON DELETE CASCADE
);


-- =====================================================
-- TABLA ASISTENCIA
-- =====================================================

CREATE TABLE IF NOT EXISTS asistencia (
    id_asistencia INT AUTO_INCREMENT PRIMARY KEY,

    fecha DATE NOT NULL,

    hora_ingreso TIME NOT NULL,
    hora_salida TIME,

    tardanza BOOLEAN NOT NULL DEFAULT FALSE,

    id_trabajador INT NOT NULL,

    CONSTRAINT fk_asistencia_trabajador
        FOREIGN KEY (id_trabajador)
        REFERENCES trabajador(id_trabajador)
        ON DELETE CASCADE
);


-- =====================================================
-- DATOS INICIALES
-- =====================================================

INSERT IGNORE INTO usuario (
    nombre_usuario,
    contrasena,
    rol,
    estado
)
VALUES (
    'admin',
    '1234',
    'ADMINISTRADOR',
    TRUE
);


INSERT IGNORE INTO area (
    codigo_area,
    nombre_area,
    estado
)
VALUES
('A001', 'Recursos Humanos', TRUE),
('A002', 'Contabilidad', TRUE),
('A003', 'Logística', TRUE);


INSERT IGNORE INTO cargo (
    codigo_cargo,
    nombre_cargo,
    descripcion,
    estado
)
VALUES
(
    'C001',
    'Asistente administrativo',
    'Apoyo en actividades administrativas',
    TRUE
),
(
    'C002',
    'Analista',
    'Análisis y gestión de información',
    TRUE
),
(
    'C003',
    'Obrero municipal',
    'Apoyo en actividades operativas municipales',
    TRUE
);