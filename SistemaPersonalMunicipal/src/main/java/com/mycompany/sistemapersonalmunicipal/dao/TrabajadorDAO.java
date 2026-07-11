package com.mycompany.sistemapersonalmunicipal.dao;

import com.mycompany.sistemapersonalmunicipal.dao.ConexionBD;
import com.mycompany.sistemapersonalmunicipal.model.ObreroMunicipal;
import com.mycompany.sistemapersonalmunicipal.model.EmpleadoMunicipal;
import com.mycompany.sistemapersonalmunicipal.model.Trabajador;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TrabajadorDAO {

    public boolean insertar(Trabajador trabajador) {

        String sql = """
                INSERT INTO trabajador (
                    tipo_documento,
                    numero_documento,
                    nombres,
                    apellido_paterno,
                    apellido_materno,
                    celular,
                    correo,
                    codigo_trabajador,
                    fecha_ingreso,
                    regimen_laboral,
                    tipo_trabajador,
                    sueldo_mensual,
                    bonificacion,
                    jornal_diario,
                    dias_trabajados,
                    estado,
                    id_area,
                    id_cargo
                )
                VALUES (
                    ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,
                    (SELECT id_area
                     FROM area
                     WHERE codigo_area = ?),

                    (SELECT id_cargo
                     FROM cargo
                     WHERE codigo_cargo = ?)
                )
                """;

        try (
                Connection conexion = ConexionBD.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql)
        ) {

            // ================= DATOS GENERALES =================

            ps.setString(1, trabajador.getTipoDocumento());
            ps.setString(2, trabajador.getNumeroDocumento());
            ps.setString(3, trabajador.getNombres());
            ps.setString(4, trabajador.getApellidoPaterno());
            ps.setString(5, trabajador.getApellidoMaterno());
            ps.setString(6, trabajador.getCelular());
            ps.setString(7, trabajador.getCorreo());

            // ================= DATOS LABORALES =================

            ps.setString(8, trabajador.getCodigoTrabajador());

            ps.setDate(
                    9,
                    convertirFecha(trabajador.getFechaIngreso())
            );

            ps.setString(
                    10,
                    trabajador.getRegimenLaboral()
            );

            // ================= TIPO DE TRABAJADOR =================

            if (trabajador instanceof EmpleadoMunicipal) {

                EmpleadoMunicipal empleado =
                        (EmpleadoMunicipal) trabajador;

                ps.setString(
                        11,
                        "EMPLEADO MUNICIPAL"
                );

                ps.setDouble(
                        12,
                        empleado.getSueldoMensual()
                );

                ps.setDouble(
                        13,
                        empleado.getBonificacion()
                );

                ps.setNull(
                        14,
                        Types.DECIMAL
                );

                ps.setNull(
                        15,
                        Types.INTEGER
                );

            } else if (trabajador instanceof ObreroMunicipal) {

                ObreroMunicipal obrero =
                        (ObreroMunicipal) trabajador;

                ps.setString(
                        11,
                        "OBRERO MUNICIPAL"
                );

                ps.setNull(
                        12,
                        Types.DECIMAL
                );

                ps.setNull(
                        13,
                        Types.DECIMAL
                );

                ps.setDouble(
                        14,
                        obrero.getJornalDiario()
                );

                ps.setInt(
                        15,
                        obrero.getDiasTrabajados()
                );

            } else {

                throw new IllegalArgumentException(
                        "Tipo de trabajador no reconocido."
                );
            }

            // ================= ESTADO =================

            ps.setBoolean(
                    16,
                    trabajador.isEstado()
            );

            // ================= ÁREA Y CARGO =================

            ps.setString(
                    17,
                    trabajador.getArea().getCodigoArea()
            );

            ps.setString(
                    18,
                    trabajador.getCargo().getCodigoCargo()
            );

            int filas = ps.executeUpdate();

            return filas > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Error al insertar trabajador: "
                    + e.getMessage()
            );

            return false;
        }
    }


    // =========================================================
    // CONVERTIR LA FECHA DEL FORMULARIO A FECHA SQL
    // =========================================================

    private Date convertirFecha(String fechaTexto) {

        try {

            // Formato: 2026-07-09

            LocalDate fecha = LocalDate.parse(
                    fechaTexto,
                    DateTimeFormatter.ofPattern("yyyy-MM-dd")
            );

            return Date.valueOf(fecha);

        } catch (DateTimeParseException e1) {

            try {

                // Formato: 09-07-2026

                LocalDate fecha = LocalDate.parse(
                        fechaTexto,
                        DateTimeFormatter.ofPattern("dd-MM-yyyy")
                );

                return Date.valueOf(fecha);

            } catch (DateTimeParseException e2) {

                throw new IllegalArgumentException(
                        "La fecha debe tener formato "
                        + "AAAA-MM-DD o DD-MM-AAAA."
                );
            }
        }
    }
        public List<Object[]> listarTodos() {

        List<Object[]> lista = new ArrayList<>();

        String sql = """
                SELECT
                    t.id_trabajador,
                    t.codigo_trabajador,
                    t.numero_documento,
                    CONCAT(
                        t.nombres, ' ',
                        t.apellido_paterno, ' ',
                        t.apellido_materno
                    ) AS nombre_completo,
                    t.tipo_trabajador,
                    a.nombre_area,
                    c.nombre_cargo,
                    CASE
                        WHEN t.tipo_trabajador = 'EMPLEADO MUNICIPAL'
                            THEN t.sueldo_mensual + t.bonificacion

                        WHEN t.tipo_trabajador = 'OBRERO MUNICIPAL'
                            THEN t.jornal_diario * t.dias_trabajados

                        ELSE 0
                    END AS sueldo_calculado
                FROM trabajador t
                INNER JOIN area a
                    ON t.id_area = a.id_area
                INNER JOIN cargo c
                    ON t.id_cargo = c.id_cargo
                WHERE t.estado = TRUE
                ORDER BY t.id_trabajador
                """;

        try (
                Connection conexion = ConexionBD.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                Object[] fila = {

                    rs.getInt("id_trabajador"),

                    rs.getString("codigo_trabajador"),

                    rs.getString("numero_documento"),

                    rs.getString("nombre_completo"),

                    rs.getString("tipo_trabajador"),

                    rs.getString("nombre_area"),

                    rs.getString("nombre_cargo"),

                    rs.getDouble("sueldo_calculado")
                };

                lista.add(fila);
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error al listar trabajadores: "
                    + e.getMessage()
            );
        }

        return lista;
    }
        public Object[] buscarPorDocumento(String documento) {

         String sql = """
                 SELECT
                     t.id_trabajador,
                     t.codigo_trabajador,
                     t.numero_documento,
                     CONCAT(
                         t.nombres, ' ',
                         t.apellido_paterno, ' ',
                         t.apellido_materno
                     ) AS nombre_completo,
                     t.tipo_trabajador,
                     a.nombre_area,
                     c.nombre_cargo,
                     CASE
                         WHEN t.tipo_trabajador = 'EMPLEADO MUNICIPAL'
                             THEN t.sueldo_mensual + t.bonificacion

                         WHEN t.tipo_trabajador = 'OBRERO MUNICIPAL'
                             THEN t.jornal_diario * t.dias_trabajados

                         ELSE 0
                     END AS sueldo_calculado
                 FROM trabajador t
                 INNER JOIN area a
                     ON t.id_area = a.id_area
                 INNER JOIN cargo c
                     ON t.id_cargo = c.id_cargo
                 WHERE t.numero_documento = ?
                   AND t.estado = TRUE
                 """;

         try (
                 Connection conexion = ConexionBD.conectar();
                 PreparedStatement ps = conexion.prepareStatement(sql)
         ) {

             ps.setString(1, documento);

             try (ResultSet rs = ps.executeQuery()) {

                 if (rs.next()) {

                     return new Object[] {

                         rs.getInt("id_trabajador"),
                         rs.getString("codigo_trabajador"),
                         rs.getString("numero_documento"),
                         rs.getString("nombre_completo"),
                         rs.getString("tipo_trabajador"),
                         rs.getString("nombre_area"),
                         rs.getString("nombre_cargo"),
                         rs.getDouble("sueldo_calculado")
                     };
                 }
             }

         } catch (SQLException e) {

             System.out.println(
                     "Error al buscar trabajador por documento: "
                     + e.getMessage()
             );
         }

         return null;
     }
        public List<Object[]> buscarPorNombre(String nombre) {

        List<Object[]> lista = new ArrayList<>();

        String sql = """
                SELECT
                    t.id_trabajador,
                    t.codigo_trabajador,
                    t.numero_documento,
                    CONCAT(
                        t.nombres, ' ',
                        t.apellido_paterno, ' ',
                        t.apellido_materno
                    ) AS nombre_completo,
                    t.tipo_trabajador,
                    a.nombre_area,
                    c.nombre_cargo,
                    CASE
                        WHEN t.tipo_trabajador = 'EMPLEADO MUNICIPAL'
                            THEN t.sueldo_mensual + t.bonificacion

                        WHEN t.tipo_trabajador = 'OBRERO MUNICIPAL'
                            THEN t.jornal_diario * t.dias_trabajados

                        ELSE 0
                    END AS sueldo_calculado
                FROM trabajador t
                INNER JOIN area a
                    ON t.id_area = a.id_area
                INNER JOIN cargo c
                    ON t.id_cargo = c.id_cargo
                WHERE (
                    t.nombres LIKE ?
                    OR t.apellido_paterno LIKE ?
                    OR t.apellido_materno LIKE ?
                )
                AND t.estado = TRUE
                ORDER BY t.apellido_paterno, t.nombres
                """;

        try (
                Connection conexion = ConexionBD.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql)
        ) {

            String criterio = "%" + nombre + "%";

            ps.setString(1, criterio);
            ps.setString(2, criterio);
            ps.setString(3, criterio);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    Object[] fila = {

                        rs.getInt("id_trabajador"),
                        rs.getString("codigo_trabajador"),
                        rs.getString("numero_documento"),
                        rs.getString("nombre_completo"),
                        rs.getString("tipo_trabajador"),
                        rs.getString("nombre_area"),
                        rs.getString("nombre_cargo"),
                        rs.getDouble("sueldo_calculado")
                    };

                    lista.add(fila);
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error al buscar trabajador por nombre: "
                    + e.getMessage()
            );
        }

        return lista;
    }
        public boolean actualizar(
             String numeroDocumento,
             String celular,
             String correo,
             String regimenLaboral,
             String codigoArea,
             String codigoCargo
     ) {

         String sql = """
                 UPDATE trabajador
                 SET
                     celular = ?,
                     correo = ?,
                     regimen_laboral = ?,
                     id_area = (
                         SELECT id_area
                         FROM area
                         WHERE codigo_area = ?
                     ),
                     id_cargo = (
                         SELECT id_cargo
                         FROM cargo
                         WHERE codigo_cargo = ?
                     )
                 WHERE numero_documento = ?
                   AND estado = TRUE
                 """;

         try (
                 Connection conexion = ConexionBD.conectar();
                 PreparedStatement ps = conexion.prepareStatement(sql)
         ) {

             ps.setString(1, celular);
             ps.setString(2, correo);
             ps.setString(3, regimenLaboral);
             ps.setString(4, codigoArea);
             ps.setString(5, codigoCargo);
             ps.setString(6, numeroDocumento);

             int filasActualizadas = ps.executeUpdate();

             return filasActualizadas > 0;

         } catch (SQLException e) {

             System.out.println(
                     "Error al actualizar trabajador: "
                     + e.getMessage()
             );

             return false;
         }
     }
            public Object[] buscarDatosParaModificar(String documento) {

        String sql = """
                SELECT
                    CONCAT(
                        t.nombres, ' ',
                        t.apellido_paterno, ' ',
                        t.apellido_materno
                    ) AS nombre_completo,

                    t.celular,
                    t.correo,
                    t.regimen_laboral,
                    a.codigo_area,
                    c.codigo_cargo

                FROM trabajador t

                INNER JOIN area a
                    ON t.id_area = a.id_area

                INNER JOIN cargo c
                    ON t.id_cargo = c.id_cargo

                WHERE t.numero_documento = ?
                  AND t.estado = TRUE
                """;

        try (
                Connection conexion = ConexionBD.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql)
        ) {

            ps.setString(1, documento);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    return new Object[] {
                        rs.getString("nombre_completo"),
                        rs.getString("celular"),
                        rs.getString("correo"),
                        rs.getString("regimen_laboral"),
                        rs.getString("codigo_area"),
                        rs.getString("codigo_cargo")
                    };
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error al cargar datos del trabajador: "
                    + e.getMessage()
            );
        }

        return null;
    }
    public boolean desactivar(String numeroDocumento) {

        String sql = """
                UPDATE trabajador
                SET estado = FALSE
                WHERE numero_documento = ?
                  AND estado = TRUE
                """;

        try (
                Connection conexion = ConexionBD.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql)
        ) {

            ps.setString(1, numeroDocumento);

            int filasActualizadas = ps.executeUpdate();

            return filasActualizadas > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Error al desactivar trabajador: "
                    + e.getMessage()
            );

            return false;
        }
    }
    public boolean reactivar(String numeroDocumento) {

        String sql = """
                UPDATE trabajador
                SET estado = TRUE
                WHERE numero_documento = ?
                  AND estado = FALSE
                """;

        try (
                Connection conexion = ConexionBD.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql)
        ) {

            ps.setString(1, numeroDocumento);

            int filasActualizadas = ps.executeUpdate();

            return filasActualizadas > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Error al reactivar trabajador: "
                    + e.getMessage()
            );

            return false;
        }
    }
    public Object[] buscarIncluyendoInactivos(String documento) {

        String sql = """
                SELECT
                    t.numero_documento,
                    CONCAT(
                        t.nombres, ' ',
                        t.apellido_paterno, ' ',
                        t.apellido_materno
                    ) AS nombre_completo,
                    t.estado
                FROM trabajador t
                WHERE t.numero_documento = ?
                """;

        try (
                Connection conexion = ConexionBD.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql)
        ) {

            ps.setString(1, documento);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    return new Object[] {
                        rs.getString("numero_documento"),
                        rs.getString("nombre_completo"),
                        rs.getBoolean("estado")
                    };
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error al buscar trabajador: "
                    + e.getMessage()
            );
        }

        return null;
    }
        public String generarSiguienteCodigo(String tipoTrabajador) {

        String prefijo;

        if (tipoTrabajador.equals("Empleado Municipal")) {
            prefijo = "T";
        } else {
            prefijo = "O";
        }

        String sql = """
                SELECT codigo_trabajador
                FROM trabajador
                WHERE codigo_trabajador LIKE ?
                ORDER BY id_trabajador DESC
                LIMIT 1
                """;

        try (
                Connection conexion = ConexionBD.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql)
        ) {

            ps.setString(1, prefijo + "%");

            try (ResultSet rs = ps.executeQuery()) {

                int siguienteNumero = 1;

                if (rs.next()) {

                    String ultimoCodigo =
                            rs.getString("codigo_trabajador");

                    String parteNumerica =
                            ultimoCodigo.substring(1);

                    siguienteNumero =
                            Integer.parseInt(parteNumerica) + 1;
                }

                return prefijo
                        + String.format("%03d", siguienteNumero);
            }

        } catch (SQLException | NumberFormatException e) {

            System.out.println(
                    "Error al generar código de trabajador: "
                    + e.getMessage()
            );

            return prefijo + "001";
        }
    }
}