
# Hospital Management System

## Descripción
Hospital Management System es una API RESTful desarrollada en Java con Spring Boot que permite la gestión de consultorios, médicos, pacientes y citas médicas. La aplicación sigue una arquitectura modular y está diseñada para ser escalable, segura y mantenible.

## Características
- Gestión de Consultorios: Creación, actualización, eliminación y consulta de consultorios.
- Gestión de Médicos: Registro de médicos, actualización de información, eliminación y consulta.
- Gestión de Pacientes: Registro de pacientes, actualización de información, eliminación y consulta.
- Gestión de Citas Médicas: Creación, modificación y cancelación de citas.
- API RESTful segura y estructurada.
- Uso de Lombok para simplificar el código.

## Tecnologías Utilizadas
- Java 17
- Spring Boot
- Maven
- Lombok
- MySQL

## Instalación
1. Clonar el repositorio:
```bash
git clone https://github.com/MarioHMis/hospital-management.git
```
2. Configurar el archivo `.env` con las variables de entorno necesarias (DB, JWT, etc.).
3. Ejecutar el proyecto con Maven:
```bash
mvn spring-boot:run
```

## Endpoints Principales
- `/api/consultorios` - CRUD de consultorios.
- `/api/medicos` - CRUD de médicos.
- `/api/pacientes` - CRUD de pacientes.
- `/api/citas` - CRUD de citas médicas.

## Contribuciones
Las contribuciones son bienvenidas. Por favor, sigue el flujo de trabajo establecido y asegúrate de que el código siga las buenas prácticas de desarrollo.

## Licencia
Este proyecto está bajo la licencia MIT.
