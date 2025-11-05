-- Script de creación de esquema de base de datos para tests (H2)
-- Tabla de prospectos
CREATE TABLE IF NOT EXISTS prospectos (
    id VARCHAR(36) PRIMARY KEY,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    rfc VARCHAR(13) NOT NULL,
    telefono VARCHAR(20)
);

-- Tabla de domicilios
CREATE TABLE IF NOT EXISTS domicilios (
    id VARCHAR(36) PRIMARY KEY,
    prospecto_id VARCHAR(36) NOT NULL,
    calle VARCHAR(200) NOT NULL,
    numero VARCHAR(20),
    colonia VARCHAR(100),
    ciudad VARCHAR(100) NOT NULL,
    estado VARCHAR(100) NOT NULL,
    pais VARCHAR(100) NOT NULL,
    codigo_postal VARCHAR(10),
    FOREIGN KEY (prospecto_id) REFERENCES prospectos(id) ON DELETE CASCADE
);

-- Tabla de empleos
CREATE TABLE IF NOT EXISTS empleos (
    id VARCHAR(36) PRIMARY KEY,
    prospecto_id VARCHAR(36) NOT NULL,
    nombre_empresa VARCHAR(200) NOT NULL,
    fecha_ingreso DATE NOT NULL,
    fecha_salida DATE,
    ingreso_mensual DECIMAL(10,2) NOT NULL,
    giro_empresa VARCHAR(200),
    FOREIGN KEY (prospecto_id) REFERENCES prospectos(id) ON DELETE CASCADE
);

-- Índices para mejorar rendimiento de consultas
CREATE INDEX IF NOT EXISTS idx_prospectos_email ON prospectos(email);
CREATE INDEX IF NOT EXISTS idx_empleos_prospecto ON empleos(prospecto_id);
CREATE INDEX IF NOT EXISTS idx_empleos_empresa ON empleos(nombre_empresa);
CREATE INDEX IF NOT EXISTS idx_empleos_ingreso ON empleos(ingreso_mensual);
CREATE INDEX IF NOT EXISTS idx_empleos_fecha_salida ON empleos(fecha_salida);

