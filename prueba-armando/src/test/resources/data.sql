-- Script de datos iniciales de prueba para tests
-- Datos de ejemplo para pruebas unitarias e integración

-- Prospecto de prueba para tests
INSERT INTO prospectos (id, nombres, apellidos, email, fecha_nacimiento, rfc, telefono) 
VALUES ('test-uuid-0001-0001-0001-0001', 'Test', 'Usuario', 'test@example.com', DATE '1990-01-01', 'TEUS900101ABC', '5550000000');

INSERT INTO domicilios (id, prospecto_id, calle, numero, colonia, ciudad, estado, pais, codigo_postal)
VALUES ('test-uuid-0001-0001-0001-0002', 'test-uuid-0001-0001-0001-0001', 'Calle Test', '123', 'Colonia Test', 'Ciudad Test', 'Estado Test', 'México', '00000');

INSERT INTO empleos (id, prospecto_id, nombre_empresa, fecha_ingreso, fecha_salida, ingreso_mensual, giro_empresa)
VALUES ('test-uuid-0001-0001-0001-0003', 'test-uuid-0001-0001-0001-0001', 'Empresa Test 1', DATE '2020-01-01', DATE '2021-12-31', 15000.00, 'Tecnología');

INSERT INTO empleos (id, prospecto_id, nombre_empresa, fecha_ingreso, fecha_salida, ingreso_mensual, giro_empresa)
VALUES ('test-uuid-0001-0001-0001-0004', 'test-uuid-0001-0001-0001-0001', 'Empresa Test 2', DATE '2022-01-01', DATE '2023-12-31', 18000.00, 'Tecnología');

INSERT INTO empleos (id, prospecto_id, nombre_empresa, fecha_ingreso, fecha_salida, ingreso_mensual, giro_empresa)
VALUES ('test-uuid-0001-0001-0001-0005', 'test-uuid-0001-0001-0001-0001', 'Empresa Test 3', DATE '2024-01-01', NULL, 20000.00, 'Tecnología');

