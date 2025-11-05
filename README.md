# Desarrollo de Microservicios para la Gestión de Prospectos de Empleo

## Objetivo
Desarrollar una aplicación basada en microservicios usando Java, que implemente un conjunto de servicios REST para gestionar prospectos de empleo en una consultora de capital humano.

## Requerimientos Técnicos

- **Lenguaje:** Java 21
- **Framework:** Spring Boot
- **Base de Datos:** Oracle (deseable)
- **Persistencia:** Uso de `JdbcTemplate`, deseable integración con PL/SQL para procedimientos almacenados y funciones.
- **Contenerización:** Docker

## Funcionalidades

1. **Persistencia:**
   - Crear y almacenar nuevos prospectos junto con el historial de sus empleos anteriores (al menos 3 empleos).

2. **Modificación:**
   - Actualizar los datos de un prospecto, incluyendo datos personales, empleos, y domicilio.

3. **Búsqueda y Listado:**
   - Buscar prospectos por su identificador único (ID).
   - Listar todos los prospectos que hayan trabajado para una empresa específica (ej. "Grupo Salinas").
   - Listar prospectos con ingresos superiores a $10,000 en cualquiera de sus empleos anteriores.
   - Listar prospectos cuyo último empleo esté vigente (sin fecha de salida).

4. **Eliminación:**
   - Eliminar prospectos, incluyendo sus datos personales y el historial de empleos, por su identificador único (ID) o por correo electrónico.

## Estructura de Datos

- **Prospecto:**
  - `id`: Identificador único (UUID)
  - `nombres`
  - `apellidos`
  - `email`
  - `fechaNacimiento`
  - `rfc`: Registro Federal de Contribuyentes
  - `telefono`
  - `domicilio`: Información del domicilio
  - `listaEmpleos`: Historial de empleos

- **Domicilio:**
  - `calle`
  - `numero`
  - `colonia`
  - `ciudad`
  - `estado`
  - `pais`
  - `codigoPostal`

- **Empleo:**
  - `nombreEmpresa`
  - `fechaIngreso`
  - `fechaSalida` (puede ser `null`)
  - `ingresoMensual`
  - `giroEmpresa`

## Extras

- **Interfaz Web:** Desarrollo opcional de una interfaz web para interacción con los servicios REST, o bien una colección de Postman para realizar pruebas.
- **Documentación:** Documentar los endpoints y el proceso de despliegue en contenedores.

## Limitaciones

- Duración de la tarea: 24 horas.
- Uso de recursos en línea y herramientas de IA está permitido para consultas y soporte.

---

### Notas Adicionales

- Utilizar Docker para contenerizar la aplicación y la base de datos.
- El uso de PL/SQL para la interacción con Oracle es deseable para puntos extra.
- Mantener la estructura y claridad del código fuente junto con su documentación.

---

## Documentación de Despliegue

### Requisitos Previos

- Docker Desktop instalado y ejecutándose
- Docker Compose instalado

### Despliegue con Docker Compose

1. **Clonar o descargar el proyecto:**
   ```bash
   cd GS_Ejercicio2
   ```

2. **Iniciar los servicios con Docker Compose:**
   ```bash
   docker-compose up -d
   ```

   Esto iniciará:
   - Oracle Database Express Edition en el puerto 1521
   - La aplicación Spring Boot en el puerto 8080

3. **Verificar el estado de los contenedores:**
   ```bash
   docker-compose ps
   ```

### Inicialización de la Base de Datos

**Nota:** Oracle Express no ejecuta automáticamente scripts de inicialización como otras bases de datos. Debes inicializar manualmente.

**Importante:** El proyecto incluye datos de prueba que se cargan automáticamente cuando se usa H2 o cuando se ejecuta el script de inicialización. Estos datos incluyen 6 prospectos de ejemplo con sus empleos y domicilios, incluyendo algunos que trabajaron en "Grupo Salinas" y otros con ingresos superiores a $10,000.

#### Usar el script de inicialización

1. **Esperar a que Oracle esté completamente iniciado:**
   ```bash
   docker-compose logs -f oracle-db
   ```
   Espera hasta ver el mensaje "DATABASE IS READY TO USE!"

2. **Ejecutar el script de inicialización:**
   ```bash
   chmod +x init-oracle.sh
   ./init-oracle.sh
   ```
   
   Este script creará el esquema y cargará los datos de prueba automáticamente.

### Configuración de la Aplicación

La aplicación se configura mediante variables de entorno en `docker-compose.yml`:

- `SPRING_DATASOURCE_URL`: URL de conexión a Oracle
- `SPRING_DATASOURCE_USERNAME`: Usuario de la base de datos
- `SPRING_DATASOURCE_PASSWORD`: Contraseña de la base de datos

### Acceso a la Aplicación

- **API REST:** http://localhost:8080/api/prospectos
- **Swagger UI:** http://localhost:8080/swagger-ui.html
- **API Docs (JSON):** http://localhost:8080/api-docs

### Crear Colección de Postman desde OpenAPI

La aplicación expone una especificación OpenAPI en el endpoint `/api-docs` que puede ser importada directamente en Postman para crear una colección completa con todos los endpoints documentados.

#### Opción 1: Importar desde URL (Recomendado)

1. **Abrir Postman:**
   - Abre la aplicación Postman (Desktop o Web)

2. **Importar desde URL:**
   - Click en el botón **"Import"** en la esquina superior izquierda
   - Selecciona la pestaña **"Link"**
   - Ingresa la siguiente URL:
     ```
     http://localhost:8080/api-docs
     ```
   - Click en **"Continue"**
   - Click en **"Import"**

3. **Colección creada:**
   - Postman creará automáticamente una colección llamada "Prospectos" (o similar)
   - Todos los endpoints estarán disponibles con:
     - Métodos HTTP configurados
     - Parámetros documentados
     - Ejemplos de request/response
     - Esquemas de validación

#### Opción 2: Descargar y Compartir el Archivo OpenAPI

1. **Descargar el archivo OpenAPI:**
   - Abre en tu navegador: http://localhost:8080/api-docs
   - Guarda el contenido JSON como archivo (ej: `prospectos-api.json`)
   - O desde la terminal:
     ```bash
     curl http://localhost:8080/api-docs > prospectos-api.json
     ```

2. **Compartir el archivo:**
   - Comparte el archivo `prospectos-api.json` con tu equipo
   - El archivo es texto plano (JSON) y puede compartirse por cualquier medio

3. **Importar en Postman:**
   - En Postman, click en **"Import"**
   - Selecciona la pestaña **"Upload Files"**
   - Selecciona el archivo `prospectos-api.json`
   - Click en **"Import"**

### Detener los Servicios

```bash
docker-compose down
```

Para eliminar también los volúmenes (incluyendo los datos de Oracle):
```bash
docker-compose down -v
```

---

## Estructura del Proyecto

```
GS_Ejercicio2/
├── prueba-armando/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/gruposalinas/prueba_armando/
│   │   │   │       ├── config/          # Configuración (OpenAPI)
│   │   │   │       ├── controller/      # Controladores REST
│   │   │   │       ├── domain/          # Modelos de dominio
│   │   │   │       ├── dto/             # DTOs para request/response
│   │   │   │       ├── exception/       # Manejo de excepciones
│   │   │   │       ├── mapper/          # Mappers entre dominio y DTOs
│   │   │   │       ├── repository/      # Capa de persistencia
│   │   │   │       └── service/         # Capa de servicios
│   │   │   └── resources/
│   │   │       ├── application.properties
│   │   │       ├── schema.sql
│   │   │       └── init-db.sql
│   │   └── test/
│   ├── pom.xml
│   └── Dockerfile
├── docker-compose.yml
├── Dockerfile
└── README.md
```

---

## Tecnologías Utilizadas

- **Java 21**: Lenguaje de programación
- **Spring Boot 3.5.7**: Framework de aplicación
- **Spring JDBC / JdbcTemplate**: Persistencia de datos
- **Oracle Database**: Base de datos
- **Docker & Docker Compose**: Contenerización
- **Swagger/OpenAPI**: Documentación de API
- **Bean Validation**: Validación de datos

---

## Notas Importantes

1. **Oracle Database**: El contenedor de Oracle puede tardar varios minutos en iniciarse completamente. La aplicación esperará hasta que Oracle esté listo.

2. **Puertos**: Asegúrate de que los puertos 8080 y 1521 no estén en uso.

3. **Datos Persistentes**: Los datos de Oracle se almacenan en un volumen de Docker. Para iniciar desde cero, elimina el volumen con `docker-compose down -v`.

4. **Datos de Prueba**: El proyecto incluye datos de prueba que se cargan automáticamente:
   - 6 prospectos de ejemplo
   - Cada prospecto tiene al menos 3 empleos
   - Varios prospectos trabajaron en "Grupo Salinas"
   - Varios prospectos tienen ingresos superiores a $10,000
   - Algunos tienen empleo vigente (sin fecha de salida)
   
   Puedes probar los endpoints de búsqueda con estos datos:
   - Buscar por empresa: `GET /api/prospectos/empresa/Grupo Salinas`
   - Buscar con ingresos mínimos: `GET /api/prospectos/ingresos-minimos/10000`
   - Buscar con empleo vigente: `GET /api/prospectos/empleo-vigente`