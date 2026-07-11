# Sistema de Gestión de Personal Municipal

## Proyecto Final
**Curso:** Técnicas de Programación Orientada a Objetos

Este proyecto consiste en el desarrollo de un Sistema de Gestión de Personal Municipal orientado a la administración de trabajadores de una municipalidad. El sistema fue desarrollado utilizando Programación Orientada a Objetos, arquitectura MVC, patrón DAO y persistencia de datos mediante JDBC y MySQL.

---

# Objetivo del proyecto

Desarrollar una aplicación de escritorio que permita administrar la información del personal municipal de manera organizada, facilitando el registro, actualización, consulta y control de trabajadores, contratos y asistencias.

---

# Funcionalidades implementadas

El sistema permite realizar las siguientes operaciones:

## Autenticación

- Inicio de sesión mediante usuario y contraseña.
- Validación de credenciales almacenadas en MySQL.

## Gestión de Trabajadores

- Registrar empleados municipales.
- Registrar obreros municipales.
- Buscar trabajador por DNI.
- Buscar trabajador por nombre.
- Listar trabajadores.
- Actualizar información.
- Activar y desactivar trabajadores.

## Gestión de Áreas

- Registrar áreas.
- Buscar áreas.
- Actualizar áreas.
- Activar y desactivar áreas.

## Gestión de Cargos

- Registrar cargos.
- Buscar cargos.
- Actualizar cargos.
- Activar y desactivar cargos.

## Gestión de Contratos

- Registrar contratos.
- Buscar contratos.
- Consultar historial de contratos.

## Gestión de Asistencias

- Registrar asistencia diaria.
- Registrar hora de ingreso.
- Registrar hora de salida.
- Calcular tardanzas automáticamente.
- Consultar historial de asistencias.

## Reportes

- Reporte general del sistema.
- Reporte por tipo de trabajador.

---

# Tecnologías utilizadas

- Java
- Java Swing
- Programación Orientada a Objetos
- JDBC
- MySQL Server
- MySQL Connector/J
- Apache Maven
- Apache NetBeans 26
- Git
- GitHub

---

# Arquitectura del proyecto

El proyecto sigue la arquitectura **MVC (Modelo - Vista - Controlador)**.

```
View
│
├── Formularios Swing
│
▼
Model
│
├── Clases del dominio
│
▼
DAO
│
├── Acceso a datos mediante JDBC
│
▼
MySQL
```

---

# Patrones utilizados

Durante el desarrollo del proyecto se aplicaron los siguientes patrones y principios:

- Arquitectura MVC
- Patrón DAO (Data Access Object)
- Programación Orientada a Objetos
- JDBC para acceso a datos

---

# Principios de Programación Orientada a Objetos aplicados

El proyecto implementa los cuatro pilares de la Programación Orientada a Objetos:

## Abstracción

La clase **Trabajador** fue implementada como clase abstracta.

## Herencia

Las clases:

- EmpleadoMunicipal
- ObreroMunicipal

heredan de la clase Trabajador.

## Polimorfismo

Las clases hijas implementan el método:

```
calcularSueldo()
```

de forma independiente.

## Encapsulamiento

Los atributos fueron declarados privados y se accede a ellos mediante métodos Getter y Setter.

---

# Base de datos

El sistema utiliza MySQL.

Nombre de la base de datos:

```
sistema_personal_municipal
```

El script de creación se encuentra en:

```
database/
```

---

# Instalación del proyecto

## Requisitos

- Java JDK compatible con el proyecto.
- Apache NetBeans.
- MySQL Server.
- MySQL Connector/J.
- Maven.

---

## Pasos para ejecutar

### 1. Importar la base de datos

Ejecutar el archivo:

```
database/sistema_personal_municipal.sql
```

---

### 2. Configurar la conexión

Verificar los datos de conexión en la clase:

```
dao/ConexionBD.java
```

Modificar únicamente si es necesario:

- servidor
- puerto
- usuario
- contraseña

---

### 3. Abrir el proyecto

Abrir la carpeta del proyecto mediante Apache NetBeans.

---

### 4. Compilar

Seleccionar:

```
Clean and Build
```

---

### 5. Ejecutar

Ejecutar la aplicación desde NetBeans.

---

# Estructura del proyecto

```
SistemaPersonalMunicipal
│
├── src
│   ├── dao
│   ├── model
│   ├── view
│   └── util
│
├── database
│   ├── sistema_personal_municipal.sql
│   └── backup.sql
│
├── doc
│   ├── Informe_Final.docx
│   ├── Manual_Instalacion_Ejecucion.md
│   ├── UML.png
│   ├── DER.png
│   └── Historias_Usuario.xlsx
│
├── pom.xml
└── README.md
```

---

# Usuario de prueba

Si la base de datos se encuentra con los datos iniciales del proyecto:

Usuario:

```
admin
```

Contraseña:

```
1234
```


---

# Autor

**Marco Antonio Silva**

Universidad Privada del Norte

Carrera:

Ingeniería de Sistemas Computacionales

Curso:

Técnicas de Programación Orientada a Objetos

Año:

2026

---

# Licencia

Proyecto desarrollado exclusivamente con fines académicos para el curso **Técnicas de Programación Orientada a Objetos**.