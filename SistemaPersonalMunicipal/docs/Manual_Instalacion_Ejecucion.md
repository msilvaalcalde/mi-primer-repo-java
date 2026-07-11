# Manual de Instalación y Ejecución

## Sistema de Gestión de Personal Municipal

Proyecto Final del curso Técnicas de Programación Orientada a Objetos.

---

## 1. Descripción

El Sistema de Gestión de Personal Municipal es una aplicación de escritorio desarrollada en Java.

El sistema permite realizar las siguientes operaciones:

- Iniciar sesión mediante credenciales almacenadas en MySQL.
- Registrar empleados municipales.
- Registrar obreros municipales.
- Buscar trabajadores por documento.
- Buscar trabajadores por nombre.
- Listar trabajadores activos.
- Actualizar información de trabajadores.
- Desactivar y reactivar trabajadores.
- Gestionar áreas municipales.
- Registrar y actualizar contratos.
- Registrar asistencias.
- Calcular tardanzas automáticamente.
- Consultar historial de asistencias.
- Generar reportes generales y por tipo de trabajador.

---

## 2. Tecnologías utilizadas

El proyecto utiliza:

- Java.
- Java Swing.
- JDBC.
- MySQL Server.
- MySQL Connector/J.
- Maven.
- Apache NetBeans.
- Git.
- GitHub.

---

## 3. Requisitos previos

Antes de ejecutar el sistema se debe contar con:

### Software requerido

- Java JDK compatible con el proyecto.
- Apache NetBeans.
- Maven.
- MySQL Server.
- MySQL Workbench, opcional para administración visual de la base de datos.

### Base de datos

El proyecto utiliza la base de datos:

```text
sistema_personal_municipal
```

La carpeta:

```text
database/
```

contiene:

```text
sistema_personal_municipal.sql
sistema_personal_municipal_backup.sql
```

El primer archivo permite crear una instalación limpia del sistema.

El segundo archivo contiene una copia de seguridad de la base utilizada durante el desarrollo y las pruebas.

---

## 4. Instalación de la base de datos

### Opción A: instalación limpia

1. Iniciar MySQL Server.
2. Abrir MySQL Workbench.
3. Crear una nueva pestaña SQL.
4. Abrir el archivo:

```text
database/sistema_personal_municipal.sql
```

5. Ejecutar el script completo.
6. Verificar que se haya creado el esquema:

```text
sistema_personal_municipal
```

7. Verificar la existencia de las tablas:

```text
usuario
area
cargo
trabajador
contrato
asistencia
```

### Opción B: restaurar el backup

1. Abrir MySQL Workbench.
2. Ingresar a:

```text
Server
→ Data Import
```

3. Seleccionar:

```text
Import from Self-Contained File
```

4. Elegir:

```text
database/sistema_personal_municipal_backup.sql
```

5. Iniciar la importación.
6. Verificar que las tablas y los datos hayan sido restaurados correctamente.

---

## 5. Configuración de la conexión

La conexión con MySQL se encuentra centralizada en:

```text
ConexionBD.java
```

Ubicación:

```text
com.mycompany.sistemapersonalmunicipal.dao
```

Debe verificarse:

```text
URL
usuario
contraseña
```

Ejemplo de URL de conexión:

```text
jdbc:mysql://localhost:3306/sistema_personal_municipal
```

Antes de ejecutar el sistema se debe verificar que:

- MySQL Server se encuentre iniciado.
- El puerto configurado sea correcto.
- La base de datos exista.
- El usuario configurado tenga acceso a la base de datos.
- La contraseña configurada sea correcta.

---

## 6. Apertura del proyecto

1. Abrir Apache NetBeans.
2. Seleccionar:

```text
File
→ Open Project
```

3. Seleccionar la carpeta:

```text
SistemaPersonalMunicipal
```

4. Esperar a que Maven cargue las dependencias.
5. Verificar que el proyecto no muestre errores.

---

## 7. Compilación

En NetBeans:

1. Hacer clic derecho sobre el proyecto.
2. Seleccionar:

```text
Clean and Build
```

La compilación correcta debe finalizar con:

```text
BUILD SUCCESS
```

---

## 8. Ejecución

1. Hacer clic derecho sobre el proyecto.
2. Seleccionar:

```text
Run
```

El sistema iniciará mostrando la ventana de inicio de sesión.

El flujo principal es:

```text
SistemaPersonalMunicipal
↓
FrmLogin
↓
FrmMenuPrincipal
```

---

## 9. Credenciales de prueba

Para la demostración académica se utiliza:

```text
Usuario: admin
Contraseña: 1234
```

Estas credenciales son utilizadas únicamente con fines académicos y de demostración.

---

## 10. Uso del sistema

### 10.1 Inicio de sesión

1. Ingresar el usuario.
2. Ingresar la contraseña.
3. Pulsar el botón Ingresar.
4. El sistema validará las credenciales mediante una consulta a la tabla `usuario`.

Si las credenciales son correctas, se abrirá el menú principal.

Si las credenciales son incorrectas, el sistema mostrará un mensaje de error.

---

### 10.2 Registro de trabajador

1. Abrir la opción Registrar trabajador.
2. Elegir el tipo de trabajador:

```text
Empleado Municipal
```

o:

```text
Obrero Municipal
```

3. Completar los datos personales.
4. Completar los datos laborales.
5. Seleccionar el área.
6. Seleccionar el cargo.
7. Completar los datos de pago correspondientes.
8. Pulsar Guardar.

El código del trabajador se genera automáticamente.

Para empleados municipales se generan códigos con el formato:

```text
T001
T002
T003
...
```

Para obreros municipales se generan códigos con el formato:

```text
O001
O002
O003
...
```

#### Empleado Municipal

Al seleccionar Empleado Municipal se habilitan:

```text
Sueldo mensual
Bonificación
```

y se deshabilitan:

```text
Jornal diario
Días trabajados
```

Los cargos disponibles corresponden a puestos administrativos.

#### Obrero Municipal

Al seleccionar Obrero Municipal se habilitan:

```text
Jornal diario
Días trabajados
```

y se deshabilitan:

```text
Sueldo mensual
Bonificación
```

El cargo disponible corresponde al tipo Obrero municipal.

---

### 10.3 Validaciones del registro

El formulario valida los datos siguiendo el orden del formulario.

Se valida:

1. Número de documento.
2. Nombres.
3. Apellido paterno.
4. Apellido materno.
5. Celular.
6. Correo electrónico.
7. Código del trabajador.
8. Fecha de ingreso.
9. Datos económicos según el tipo de trabajador.

Para DNI se requieren exactamente 8 dígitos numéricos.

Ejemplo:

```text
74045282
```

Para celular se requieren exactamente 9 dígitos numéricos.

Ejemplo:

```text
999888777
```

También se valida el formato básico del correo electrónico.

Ejemplo:

```text
usuario@correo.com
```

---

### 10.4 Buscar trabajadores

La ventana de búsqueda permite:

- Listar todos los trabajadores activos.
- Buscar un trabajador por documento.
- Buscar trabajadores por nombre o apellido.

Los resultados son obtenidos directamente desde MySQL.

Los trabajadores desactivados no aparecen en los listados operativos.

---

### 10.5 Modificar trabajador

1. Abrir Modificar trabajador.
2. Ingresar el documento.
3. Pulsar Buscar.
4. El sistema cargará los datos del trabajador.
5. Modificar los datos permitidos.
6. Pulsar Actualizar.

Se pueden actualizar:

- celular;
- correo;
- régimen laboral;
- área;
- cargo.

También se puede:

```text
Desactivar trabajador
Reactivar trabajador
```

La desactivación utiliza eliminación lógica mediante el campo:

```text
estado
```

Al desactivar:

```text
estado = FALSE
```

Al reactivar:

```text
estado = TRUE
```

---

### 10.6 Gestión de áreas

El módulo permite:

- Registrar áreas.
- Buscar áreas por código.
- Listar áreas activas.
- Actualizar el nombre del área.
- Desactivar áreas.
- Reactivar áreas.
- Limpiar los campos.
- Cerrar la ventana.

Las áreas activas pueden seleccionarse posteriormente durante el registro de trabajadores.

---

### 10.7 Gestión de contratos

1. Ingresar el documento del trabajador.
2. Pulsar Buscar trabajador.
3. El sistema carga:
   - nombre completo;
   - tipo de trabajador;
   - fecha de ingreso.
4. El sistema genera automáticamente el código de contrato.
5. Ingresar la fecha de finalización.
6. Pulsar Registrar.

Los códigos de contrato siguen el formato:

```text
CT001
CT002
CT003
...
```

La fecha de inicio del contrato se toma de la fecha de ingreso del trabajador.

También se puede:

```text
Cargar contrato
Actualizar fecha fin
```

El sistema permite consultar un contrato existente por documento del trabajador.

---

### 10.8 Registro de asistencias

1. Ingresar el documento del trabajador.
2. Pulsar Buscar trabajador.
3. El sistema carga:
   - nombre;
   - código;
   - tipo de trabajador.
4. La fecha se carga automáticamente.
5. Ingresar:
   - hora de ingreso;
   - hora de salida.
6. Pulsar Registrar asistencia.

Formato de hora:

```text
HH:MM
```

Ejemplos:

```text
07:55
08:15
17:00
```

El sistema determina automáticamente si existe tardanza.

Criterio utilizado:

```text
Hasta las 08:00 → No hay tardanza
Después de las 08:00 → Hay tardanza
```

Ejemplos:

```text
07:55 → NO
08:00 → NO
08:01 → SÍ
08:15 → SÍ
```

También es posible consultar el historial mediante:

```text
Ver asistencias
```

El historial muestra:

- fecha;
- hora de ingreso;
- hora de salida;
- condición de tardanza.

---

### 10.9 Reportes

El sistema incluye dos reportes principales.

#### Reporte general

Muestra:

- código del trabajador;
- documento;
- nombre completo;
- tipo de trabajador;
- régimen laboral;
- fecha de ingreso;
- área;
- cargo;
- remuneración calculada.

El reporte muestra únicamente trabajadores activos.

#### Reporte por tipo

Agrupa a los trabajadores en:

```text
EMPLEADO MUNICIPAL
OBRERO MUNICIPAL
```

Muestra:

- cantidad de trabajadores por tipo;
- total de remuneraciones calculadas;
- total general de trabajadores.

Las consultas utilizan operaciones SQL como:

```text
JOIN
WHERE
CASE
COUNT
SUM
GROUP BY
```

---

## 11. Organización del proyecto

El proyecto está organizado en paquetes para separar responsabilidades.

### Paquete model

Contiene las clases del modelo orientado a objetos:

```text
Trabajador
EmpleadoMunicipal
ObreroMunicipal
Area
Cargo
Contrato
Asistencia
Usuario
```

La clase `Trabajador` es abstracta.

La jerarquía principal es:

```text
Trabajador
├── EmpleadoMunicipal
└── ObreroMunicipal
```

El sistema utiliza herencia y polimorfismo para calcular la remuneración de cada tipo de trabajador.

---

### Paquete dao

Contiene las clases de acceso a datos mediante JDBC:

```text
ConexionBD
UsuarioDAO
TrabajadorDAO
AreaDAO
CargoDAO
ContratoDAO
AsistenciaDAO
ReporteDAO
```

Las clases DAO realizan operaciones como:

```text
INSERT
SELECT
UPDATE
```

y utilizan:

```text
Connection
PreparedStatement
ResultSet
SQLException
```

---

### Paquete view

Contiene las interfaces gráficas desarrolladas con Java Swing:

```text
FrmLogin
FrmMenuPrincipal
FrmRegistrarTrabajador
FrmListarTrabajadores
FrmModificarTrabajador
FrmGestionAreas
FrmGestionContratos
FrmAsistencias
FrmReportes
```

---

## 12. Conceptos de Programación Orientada a Objetos aplicados

El proyecto utiliza:

- Encapsulamiento.
- Herencia.
- Abstracción.
- Polimorfismo.
- Upcasting.
- Downcasting.
- Asociaciones entre clases.
- Composición.
- Clases abstractas.
- Manejo de excepciones.
- Separación de responsabilidades.
- Arquitectura por paquetes.

### Abstracción

La clase:

```text
Trabajador
```

es abstracta y define el comportamiento común de empleados y obreros.

### Herencia

```text
EmpleadoMunicipal extends Trabajador
ObreroMunicipal extends Trabajador
```

### Polimorfismo

El método de cálculo de sueldo tiene comportamientos distintos según el objeto real.

Para empleado:

```text
sueldo mensual + bonificación
```

Para obrero:

```text
jornal diario × días trabajados
```

### Composición

La relación entre Trabajador y Asistencia se representa mediante una colección de asistencias dentro de la clase Trabajador.

---

## 13. Persistencia de datos

El sistema utiliza MySQL como fuente principal de información.

El flujo de persistencia es:

```text
Interfaz Swing
↓
DAO
↓
JDBC
↓
MySQL
```

La aplicación no depende de listas temporales en memoria para almacenar los trabajadores.

Los datos permanecen disponibles después de cerrar y volver a ejecutar el programa.

---

## 14. Solución de problemas

### El sistema no conecta con MySQL

Verificar:

- que MySQL Server esté iniciado;
- URL de conexión;
- puerto 3306;
- usuario;
- contraseña;
- existencia del esquema.

### Error por documento duplicado

El número de documento es único.

Debe utilizarse un número no registrado anteriormente.

### Error por código duplicado

Los códigos de trabajador y contrato son únicos.

El sistema los genera automáticamente.

### El trabajador no aparece en el listado

Verificar si el trabajador fue desactivado.

Los listados operativos muestran trabajadores con:

```text
estado = TRUE
```

### El área no aparece en el registro

Verificar que el área se encuentre activa.

### No se puede registrar un segundo contrato

En el alcance actual del sistema se gestiona un contrato asociado a cada trabajador.

Antes de registrar, verificar si el trabajador ya tiene un contrato existente.

### El historial de asistencia aparece vacío

Verificar:

1. que el trabajador haya sido buscado correctamente;
2. que existan asistencias registradas para ese trabajador.

---

## 15. Archivos de base de datos

La carpeta:

```text
database/
```

contiene:

```text
sistema_personal_municipal.sql
sistema_personal_municipal_backup.sql
```

### sistema_personal_municipal.sql

Contiene:

- creación del esquema;
- creación de tablas;
- relaciones;
- usuario inicial;
- áreas iniciales;
- cargos iniciales.

### sistema_personal_municipal_backup.sql

Contiene:

- estructura de la base de datos;
- datos de prueba;
- trabajadores;
- contratos;
- asistencias;
- áreas;
- cargos;
- usuarios.

---

## 16. Flujo general del sistema

```text
Login
↓
Menú principal
├── Registrar trabajador
├── Buscar trabajadores
├── Modificar trabajador
├── Gestión de áreas
├── Gestión de contratos
├── Registro de asistencias
├── Reportes
└── Salir
```

---

## 17. Autor

Marco Antonio Silva Alcalde

Proyecto Final de Técnicas de Programación Orientada a Objetos.
