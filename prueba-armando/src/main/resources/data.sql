-- Script de datos iniciales de prueba
-- Datos de ejemplo para desarrollo y pruebas

-- Prospecto 1: Juan Pérez García
INSERT INTO prospectos (id, nombres, apellidos, email, fecha_nacimiento, rfc, telefono) 
VALUES ('550e8400-e29b-41d4-a716-446655440001', 'Juan', 'Pérez García', 'juan.perez@example.com', DATE '1990-05-15', 'PEGJ900515ABC', '5551234567');

INSERT INTO domicilios (id, prospecto_id, calle, numero, colonia, ciudad, estado, pais, codigo_postal)
VALUES ('550e8400-e29b-41d4-a716-446655440011', '550e8400-e29b-41d4-a716-446655440001', 'Av. Reforma', '123', 'Centro', 'Ciudad de México', 'CDMX', 'México', '06000');

INSERT INTO empleos (id, prospecto_id, nombre_empresa, fecha_ingreso, fecha_salida, ingreso_mensual, giro_empresa)
VALUES ('550e8400-e29b-41d4-a716-446655440021', '550e8400-e29b-41d4-a716-446655440001', 'Grupo Salinas', DATE '2020-01-15', DATE '2022-06-30', 15000.00, 'Retail');

INSERT INTO empleos (id, prospecto_id, nombre_empresa, fecha_ingreso, fecha_salida, ingreso_mensual, giro_empresa)
VALUES ('550e8400-e29b-41d4-a716-446655440022', '550e8400-e29b-41d4-a716-446655440001', 'Tech Solutions', DATE '2022-07-01', DATE '2023-12-31', 18000.00, 'Tecnología');

INSERT INTO empleos (id, prospecto_id, nombre_empresa, fecha_ingreso, fecha_salida, ingreso_mensual, giro_empresa)
VALUES ('550e8400-e29b-41d4-a716-446655440023', '550e8400-e29b-41d4-a716-446655440001', 'Digital Innovations', DATE '2024-01-01', NULL, 22000.00, 'Tecnología');

-- Prospecto 2: María González López
INSERT INTO prospectos (id, nombres, apellidos, email, fecha_nacimiento, rfc, telefono)
VALUES ('550e8400-e29b-41d4-a716-446655440002', 'María', 'González López', 'maria.gonzalez@example.com', DATE '1988-03-22', 'GOLM880322DEF', '5552345678');

INSERT INTO domicilios (id, prospecto_id, calle, numero, colonia, ciudad, estado, pais, codigo_postal)
VALUES ('550e8400-e29b-41d4-a716-446655440012', '550e8400-e29b-41d4-a716-446655440002', 'Calle Insurgentes', '456', 'Roma Norte', 'Ciudad de México', 'CDMX', 'México', '06700');

INSERT INTO empleos (id, prospecto_id, nombre_empresa, fecha_ingreso, fecha_salida, ingreso_mensual, giro_empresa)
VALUES ('550e8400-e29b-41d4-a716-446655440024', '550e8400-e29b-41d4-a716-446655440002', 'Grupo Salinas', DATE '2019-03-01', DATE '2021-08-15', 12000.00, 'Retail');

INSERT INTO empleos (id, prospecto_id, nombre_empresa, fecha_ingreso, fecha_salida, ingreso_mensual, giro_empresa)
VALUES ('550e8400-e29b-41d4-a716-446655440025', '550e8400-e29b-41d4-a716-446655440002', 'Banco Popular', DATE '2021-09-01', DATE '2023-05-30', 15000.00, 'Finanzas');

INSERT INTO empleos (id, prospecto_id, nombre_empresa, fecha_ingreso, fecha_salida, ingreso_mensual, giro_empresa)
VALUES ('550e8400-e29b-41d4-a716-446655440026', '550e8400-e29b-41d4-a716-446655440002', 'Consultoría ABC', DATE '2023-06-01', NULL, 20000.00, 'Consultoría');

-- Prospecto 3: Carlos Rodríguez Martínez
INSERT INTO prospectos (id, nombres, apellidos, email, fecha_nacimiento, rfc, telefono)
VALUES ('550e8400-e29b-41d4-a716-446655440003', 'Carlos', 'Rodríguez Martínez', 'carlos.rodriguez@example.com', DATE '1992-11-10', 'ROMC921110GHI', '5553456789');

INSERT INTO domicilios (id, prospecto_id, calle, numero, colonia, ciudad, estado, pais, codigo_postal)
VALUES ('550e8400-e29b-41d4-a716-446655440013', '550e8400-e29b-41d4-a716-446655440003', 'Boulevard López Mateos', '789', 'Centro', 'Guadalajara', 'Jalisco', 'México', '44100');

INSERT INTO empleos (id, prospecto_id, nombre_empresa, fecha_ingreso, fecha_salida, ingreso_mensual, giro_empresa)
VALUES ('550e8400-e29b-41d4-a716-446655440027', '550e8400-e29b-41d4-a716-446655440003', 'Software Solutions', DATE '2018-06-01', DATE '2020-11-30', 10000.00, 'Tecnología');

INSERT INTO empleos (id, prospecto_id, nombre_empresa, fecha_ingreso, fecha_salida, ingreso_mensual, giro_empresa)
VALUES ('550e8400-e29b-41d4-a716-446655440028', '550e8400-e29b-41d4-a716-446655440003', 'Innovación Tech', DATE '2020-12-01', DATE '2022-12-31', 15000.00, 'Tecnología');

INSERT INTO empleos (id, prospecto_id, nombre_empresa, fecha_ingreso, fecha_salida, ingreso_mensual, giro_empresa)
VALUES ('550e8400-e29b-41d4-a716-446655440029', '550e8400-e29b-41d4-a716-446655440003', 'StartupXYZ', DATE '2023-01-01', NULL, 25000.00, 'Tecnología');

-- Prospecto 4: Ana Martínez Sánchez
INSERT INTO prospectos (id, nombres, apellidos, email, fecha_nacimiento, rfc, telefono)
VALUES ('550e8400-e29b-41d4-a716-446655440004', 'Ana', 'Martínez Sánchez', 'ana.martinez@example.com', DATE '1991-07-08', 'MASA910708JKL', '5554567890');

INSERT INTO domicilios (id, prospecto_id, calle, numero, colonia, ciudad, estado, pais, codigo_postal)
VALUES ('550e8400-e29b-41d4-a716-446655440014', '550e8400-e29b-41d4-a716-446655440004', 'Av. Paseo de la Reforma', '321', 'Polanco', 'Ciudad de México', 'CDMX', 'México', '11560');

INSERT INTO empleos (id, prospecto_id, nombre_empresa, fecha_ingreso, fecha_salida, ingreso_mensual, giro_empresa)
VALUES ('550e8400-e29b-41d4-a716-446655440030', '550e8400-e29b-41d4-a716-446655440004', 'Grupo Salinas', DATE '2017-02-01', DATE '2019-07-31', 13000.00, 'Retail');

INSERT INTO empleos (id, prospecto_id, nombre_empresa, fecha_ingreso, fecha_salida, ingreso_mensual, giro_empresa)
VALUES ('550e8400-e29b-41d4-a716-446655440031', '550e8400-e29b-41d4-a716-446655440004', 'Corporativo ABC', DATE '2019-08-01', DATE '2021-12-31', 17000.00, 'Corporativo');

INSERT INTO empleos (id, prospecto_id, nombre_empresa, fecha_ingreso, fecha_salida, ingreso_mensual, giro_empresa)
VALUES ('550e8400-e29b-41d4-a716-446655440032', '550e8400-e29b-41d4-a716-446655440004', 'Multinacional XYZ', DATE '2022-01-01', DATE '2023-12-31', 21000.00, 'Corporativo');

INSERT INTO empleos (id, prospecto_id, nombre_empresa, fecha_ingreso, fecha_salida, ingreso_mensual, giro_empresa)
VALUES ('550e8400-e29b-41d4-a716-446655440033', '550e8400-e29b-41d4-a716-446655440004', 'Empresa Actual', DATE '2024-01-15', NULL, 28000.00, 'Consultoría');

-- Prospecto 5: Luis Fernández Torres
INSERT INTO prospectos (id, nombres, apellidos, email, fecha_nacimiento, rfc, telefono)
VALUES ('550e8400-e29b-41d4-a716-446655440005', 'Luis', 'Fernández Torres', 'luis.fernandez@example.com', DATE '1989-09-25', 'FETL890925MNO', '5555678901');

INSERT INTO domicilios (id, prospecto_id, calle, numero, colonia, ciudad, estado, pais, codigo_postal)
VALUES ('550e8400-e29b-41d4-a716-446655440015', '550e8400-e29b-41d4-a716-446655440005', 'Calle Principal', '654', 'Centro', 'Monterrey', 'Nuevo León', 'México', '64000');

INSERT INTO empleos (id, prospecto_id, nombre_empresa, fecha_ingreso, fecha_salida, ingreso_mensual, giro_empresa)
VALUES ('550e8400-e29b-41d4-a716-446655440034', '550e8400-e29b-41d4-a716-446655440005', 'Industrias del Norte', DATE '2016-04-01', DATE '2018-09-30', 8000.00, 'Manufactura');

INSERT INTO empleos (id, prospecto_id, nombre_empresa, fecha_ingreso, fecha_salida, ingreso_mensual, giro_empresa)
VALUES ('550e8400-e29b-41d4-a716-446655440035', '550e8400-e29b-41d4-a716-446655440005', 'Grupo Salinas', DATE '2018-10-01', DATE '2020-12-31', 14000.00, 'Retail');

INSERT INTO empleos (id, prospecto_id, nombre_empresa, fecha_ingreso, fecha_salida, ingreso_mensual, giro_empresa)
VALUES ('550e8400-e29b-41d4-a716-446655440036', '550e8400-e29b-41d4-a716-446655440005', 'TechCorp', DATE '2021-01-01', NULL, 19000.00, 'Tecnología');

-- Prospecto 6: Laura Sánchez Hernández
INSERT INTO prospectos (id, nombres, apellidos, email, fecha_nacimiento, rfc, telefono)
VALUES ('550e8400-e29b-41d4-a716-446655440006', 'Laura', 'Sánchez Hernández', 'laura.sanchez@example.com', DATE '1993-04-18', 'SAHL930418PQR', '5556789012');

INSERT INTO domicilios (id, prospecto_id, calle, numero, colonia, ciudad, estado, pais, codigo_postal)
VALUES ('550e8400-e29b-41d4-a716-446655440016', '550e8400-e29b-41d4-a716-446655440006', 'Av. Universidad', '987', 'Centro', 'Puebla', 'Puebla', 'México', '72000');

INSERT INTO empleos (id, prospecto_id, nombre_empresa, fecha_ingreso, fecha_salida, ingreso_mensual, giro_empresa)
VALUES ('550e8400-e29b-41d4-a716-446655440037', '550e8400-e29b-41d4-a716-446655440006', 'Empresa Local SA', DATE '2019-05-01', DATE '2021-03-31', 11000.00, 'Servicios');

INSERT INTO empleos (id, prospecto_id, nombre_empresa, fecha_ingreso, fecha_salida, ingreso_mensual, giro_empresa)
VALUES ('550e8400-e29b-41d4-a716-446655440038', '550e8400-e29b-41d4-a716-446655440006', 'Desarrollo Software', DATE '2021-04-01', DATE '2023-06-30', 16000.00, 'Tecnología');

INSERT INTO empleos (id, prospecto_id, nombre_empresa, fecha_ingreso, fecha_salida, ingreso_mensual, giro_empresa)
VALUES ('550e8400-e29b-41d4-a716-446655440039', '550e8400-e29b-41d4-a716-446655440006', 'Cloud Solutions', DATE '2023-07-01', NULL, 23000.00, 'Tecnología');

COMMIT;
