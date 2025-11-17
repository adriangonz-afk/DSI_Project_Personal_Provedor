# Sistema de Gestión de Personal y Proveedores

## Descripción General

Este proyecto es un sistema integral para la gestión de personal y proveedores, diseñado para organizaciones que requieren administrar eficientemente sus recursos humanos y relaciones con proveedores. El sistema permite registrar, consultar y gestionar información relevante sobre personal, proveedores, proyectos, contratos, pagos, evaluaciones y más.

---

## Características Principales

- **Gestión de Personal:** Registro, actualización y consulta de datos personales, experiencia laboral, grados académicos, especialidades, asignación a proyectos y cargos.
- **Gestión de Proveedores:** Administración de proveedores, contratos, documentos, servicios ofrecidos, actividades y pagos.
- **Gestión de Proyectos:** Creación y seguimiento de proyectos, asignación de personal y proveedores, tareas y notificaciones.
- **Auditoría y Seguridad:** Registro de auditorías y manejo de excepciones para garantizar la integridad y trazabilidad de las operaciones.
- **Notificaciones:** Envío y gestión de notificaciones internas relacionadas con tareas, proyectos y eventos relevantes.
- **Evaluaciones:** Registro y seguimiento de evaluaciones de desempeño del personal.

---

## Arquitectura del Sistema

El sistema está desarrollado en **Java** utilizando el framework **Spring Boot** y sigue una arquitectura multicapa:

- **Controller:** Maneja las solicitudes HTTP y coordina la lógica de negocio.
- **DTO (Data Transfer Object):** Facilita la transferencia de datos entre capas.
- **Entity:** Representa las tablas de la base de datos.
- **Repository:** Acceso y manipulación de datos en la base de datos.
- **Service:** Implementa la lógica de negocio.
- **Mapper:** Conversión entre entidades y DTOs.
- **Exception:** Manejo centralizado de errores y excepciones.

La base de datos se define mediante scripts SQL en la carpeta `database/`.

---

## Estructura de Carpetas

```
src/
  main/
    java/com/system/
      controller/   # Controladores REST
      dto/          # Objetos de transferencia de datos
      entity/       # Entidades JPA
      exception/    # Manejo de excepciones
      mapper/       # Mapeadores entre entidades y DTOs
      repository/   # Repositorios JPA
      service/      # Servicios de negocio
    resources/
      application.properties
      static/
      templates/
database/
  datos.sql
  esquemas.sql
  funciones.sql
test/
  java/com/system/sistema_personal_proveedor/
```

---

## Casos de Uso Destacados

1. **Registrar nuevo personal:**  
   El usuario puede ingresar datos personales, experiencia, grados académicos y asignar cargos y proyectos.

2. **Registrar proveedor y contrato:**  
   Permite agregar proveedores, asociar contratos, documentos y servicios ofrecidos.

3. **Asignar personal a proyecto:**  
   El administrador puede asignar personal a proyectos específicos y definir tareas.

4. **Registrar pago a proveedor:**  
   Gestión de pagos realizados a proveedores por servicios prestados.

5. **Evaluar desempeño del personal:**  
   Registro y consulta de evaluaciones periódicas del personal.

---

## Requisitos Técnicos

- **Java 17+**
- **Spring Boot 2.7+**
- **Maven**
- **Base de datos relacional** (ejemplo: PostgreSQL, MySQL)
- **IDE recomendado:** Visual Studio Code, IntelliJ IDEA

---

## Instalación y Ejecución

1. Clona el repositorio:
   ```bash
   git clone https://github.com/angelariasus/personal-supplier-backend
   ```
2. Configura la base de datos usando los scripts en `database/`.
3. Modifica `src/main/resources/application.properties` con tus credenciales de base de datos.
4. Compila y ejecuta el proyecto:
   ```bash
   mvn spring-boot:run
   ```
5. Accede a la API REST en `http://localhost:8080/`.

---

## Licencia

Este proyecto está bajo la licencia MIT. Consulta el archivo [LICENSE](./LICENSE) para más detalles.
