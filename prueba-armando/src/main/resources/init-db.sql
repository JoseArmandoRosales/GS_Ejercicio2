-- Script de inicialización de base de datos
-- Este script debe ejecutarse después de que Oracle esté listo

-- Crear usuario y otorgar permisos
ALTER SESSION SET CONTAINER = XEPDB1;

CREATE USER prospectos_user IDENTIFIED BY prospectos_pass;
GRANT CONNECT, RESOURCE, DBA TO prospectos_user;
GRANT UNLIMITED TABLESPACE TO prospectos_user;

-- Conectar como usuario creado
ALTER SESSION SET CURRENT_SCHEMA = prospectos_user;

-- Crear tablas
CREATE TABLE prospectos (
    id VARCHAR2(36) PRIMARY KEY,
    nombres VARCHAR2(100) NOT NULL,
    apellidos VARCHAR2(100) NOT NULL,
    email VARCHAR2(100) UNIQUE NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    rfc VARCHAR2(13) NOT NULL,
    telefono VARCHAR2(20)
);

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

-- Crear índices
CREATE INDEX idx_prospectos_email ON prospectos(email);
CREATE INDEX idx_empleos_prospecto ON empleos(prospecto_id);
CREATE INDEX idx_empleos_empresa ON empleos(nombre_empresa);
CREATE INDEX idx_empleos_ingreso ON empleos(ingreso_mensual);
CREATE INDEX idx_empleos_fecha_salida ON empleos(fecha_salida);

COMMIT;

