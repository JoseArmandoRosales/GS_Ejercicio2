#!/bin/bash
# Script para inicializar la base de datos Oracle después de que el contenedor esté listo
# Configurar UTF-8 para todo el script
export LANG=C.UTF-8
export LC_ALL=C.UTF-8
export NLS_LANG=AMERICAN_AMERICA.AL32UTF8

echo "Esperando a que Oracle esté listo..."
sleep 15

echo "Conectándose a Oracle para crear usuario..."

# Crear usuario (necesita ejecutarse como SYSDBA)
docker exec -i -e LANG=C.UTF-8 -e LC_ALL=C.UTF-8 -e NLS_LANG=AMERICAN_AMERICA.AL32UTF8 oracle-db sqlplus sys/oracle123@XE as sysdba <<EOF
ALTER SESSION SET CONTAINER = XEPDB1;
CREATE USER prospectos_user IDENTIFIED BY prospectos_pass;
GRANT CONNECT, RESOURCE, DBA TO prospectos_user;
GRANT UNLIMITED TABLESPACE TO prospectos_user;
EXIT;
EOF

echo "Copiando script de inicialización al contenedor..."

# Copiar el archivo init-db.sql al contenedor
docker cp prueba-armando/src/main/resources/init-db.sql oracle-db:/tmp/init-db.sql

echo "Creando tablas en el esquema prospectos_user desde init-db.sql..."

# Crear un script temporal que solo contenga las tablas (saltándose la creación de usuario)
# Extraer desde la línea 12 (ALTER SESSION SET CURRENT_SCHEMA) hasta el final
docker exec oracle-db bash -c "sed -n '12,\$p' /tmp/init-db.sql > /tmp/init-tables.sql"

# Ejecutar solo la parte de tablas del script
docker exec -i -e LANG=C.UTF-8 -e LC_ALL=C.UTF-8 -e NLS_LANG=AMERICAN_AMERICA.AL32UTF8 oracle-db sqlplus prospectos_user/prospectos_pass@XEPDB1 <<EOF
SET NLS_LANG=AMERICAN_AMERICA.AL32UTF8
@/tmp/init-tables.sql
COMMIT;
EXIT;
EOF

echo "Insertando datos de prueba..."

# Copiar el archivo de datos al contenedor
docker cp prueba-armando/src/main/resources/data.sql oracle-db:/tmp/data.sql

# Ejecutar el script de datos con configuración UTF-8
docker exec -i -e LANG=C.UTF-8 -e LC_ALL=C.UTF-8 -e NLS_LANG=AMERICAN_AMERICA.AL32UTF8 oracle-db sqlplus prospectos_user/prospectos_pass@XEPDB1 <<EOF
SET NLS_LANG=AMERICAN_AMERICA.AL32UTF8
@/tmp/data.sql
COMMIT;
EXIT;
EOF

echo "Base de datos inicializada correctamente con datos de prueba!"

