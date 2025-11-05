-- Script de creación de esquema de base de datos
-- Tabla de prospectos
CREATE TABLE prospectos (
    id VARCHAR2(36) PRIMARY KEY,
    nombres VARCHAR2(100) NOT NULL,
    apellidos VARCHAR2(100) NOT NULL,
    email VARCHAR2(100) UNIQUE NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    rfc VARCHAR2(13) NOT NULL,
    telefono VARCHAR2(20)
);

-- Tabla de domicilios
CREATE TABLE domicilios (
    id VARCHAR2(36) PRIMARY KEY,
    prospecto_id VARCHAR2(36) NOT NULL,
    calle VARCHAR2(200) NOT NULL,
    numero VARCHAR2(20),
    colonia VARCHAR2(100),
    ciudad VARCHAR2(100) NOT NULL,
    estado VARCHAR2(100) NOT NULL,
    pais VARCHAR2(100) NOT NULL,
    codigo_postal VARCHAR2(10),
    CONSTRAINT fk_domicilio_prospecto FOREIGN KEY (prospecto_id) REFERENCES prospectos(id) ON DELETE CASCADE
);

-- Tabla de empleos
CREATE TABLE empleos (
    id VARCHAR2(36) PRIMARY KEY,
    prospecto_id VARCHAR2(36) NOT NULL,
    nombre_empresa VARCHAR2(200) NOT NULL,
    fecha_ingreso DATE NOT NULL,
    fecha_salida DATE,
    ingreso_mensual NUMBER(10,2) NOT NULL,
    giro_empresa VARCHAR2(200),
    CONSTRAINT fk_empleo_prospecto FOREIGN KEY (prospecto_id) REFERENCES prospectos(id) ON DELETE CASCADE
);

-- Índices para mejorar rendimiento de consultas
CREATE INDEX idx_prospectos_email ON prospectos(email);
CREATE INDEX idx_empleos_prospecto ON empleos(prospecto_id);
CREATE INDEX idx_empleos_empresa ON empleos(nombre_empresa);
CREATE INDEX idx_empleos_ingreso ON empleos(ingreso_mensual);
CREATE INDEX idx_empleos_fecha_salida ON empleos(fecha_salida);

