package com.mycompany.sistemapersonalmunicipal.dao;

import com.mycompany.sistemapersonalmunicipal.dao.ConexionBD;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ContratoDAO {


    // =====================================================
    // BUSCAR TRABAJADOR ACTIVO POR DOCUMENTO
    // =====================================================

    public Object[] buscarTrabajadorPorDocumento(
            String numeroDocumento
    ) {

        String sql = """
                SELECT
                    id_trabajador,
                    codigo_trabajador,
                    numero_documento,
                    CONCAT(
                        nombres, ' ',
                        apellido_paterno, ' ',
                        apellido_materno
                    ) AS nombre_completo,
                    tipo_trabajador,
                     fecha_ingreso
                FROM trabajador
                WHERE numero_documento = ?
                  AND estado = TRUE
                """;

        try (
                Connection conexion = ConexionBD.conectar();
                PreparedStatement ps =
                        conexion.prepareStatement(sql)
        ) {

            ps.setString(
                    1,
                    numeroDocumento
            );

            try (
                    ResultSet rs =
                            ps.executeQuery()
            ) {

                if (rs.next()) {

                    return new Object[] {

                        rs.getInt(
                                "id_trabajador"
                        ),

                        rs.getString(
                                "codigo_trabajador"
                        ),

                        rs.getString(
                                "numero_documento"
                        ),

                        rs.getString(
                                "nombre_completo"
                        ),

                        rs.getString(
                                "tipo_trabajador"
                        ),
                        rs.getDate(
                                "fecha_ingreso").toString()
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


    // =====================================================
    // REGISTRAR CONTRATO
    // =====================================================

    public boolean registrar(
            String numeroDocumento,
            String codigoContrato,
            String fechaInicio,
            String fechaFin
    ) {

        String sql = """
                INSERT INTO contrato (
                    codigo_contrato,
                    fecha_inicio,
                    fecha_fin,
                    estado_contrato,
                    id_trabajador
                )
                VALUES (
                    ?,
                    ?,
                    ?,
                    TRUE,
                    (
                        SELECT id_trabajador
                        FROM trabajador
                        WHERE numero_documento = ?
                          AND estado = TRUE
                    )
                )
                """;

        try (
                Connection conexion = ConexionBD.conectar();
                PreparedStatement ps =
                        conexion.prepareStatement(sql)
        ) {

            ps.setString(
                    1,
                    codigoContrato
            );

            ps.setDate(
                    2,
                    convertirFecha(fechaInicio)
            );

            ps.setDate(
                    3,
                    convertirFecha(fechaFin)
            );

            ps.setString(
                    4,
                    numeroDocumento
            );

            int filasInsertadas =
                    ps.executeUpdate();

            return filasInsertadas > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Error al registrar contrato: "
                    + e.getMessage()
            );

            return false;
        }
    }


    // =====================================================
    // CONVERTIR FECHA
    // =====================================================

    private Date convertirFecha(
            String fechaTexto
    ) {

        try {

            LocalDate fecha =
                    LocalDate.parse(
                            fechaTexto,
                            DateTimeFormatter.ofPattern(
                                    "yyyy-MM-dd"
                            )
                    );

            return Date.valueOf(fecha);

        } catch (DateTimeParseException e) {

            throw new IllegalArgumentException(
                    "La fecha debe tener formato AAAA-MM-DD."
            );
        }
    }
        public String generarSiguienteCodigo() {

        String sql = """
                SELECT codigo_contrato
                FROM contrato
                ORDER BY id_contrato DESC
                LIMIT 1
                """;

        try (
                Connection conexion = ConexionBD.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            int siguienteNumero = 1;

            if (rs.next()) {

                String ultimoCodigo =
                        rs.getString("codigo_contrato");

                String parteNumerica =
                        ultimoCodigo.substring(2);

                siguienteNumero =
                        Integer.parseInt(parteNumerica) + 1;
            }

            return "CT"
                    + String.format(
                            "%03d",
                            siguienteNumero
                    );

        } catch (SQLException | NumberFormatException e) {

            System.out.println(
                    "Error al generar código de contrato: "
                    + e.getMessage()
            );

            return "CT001";
        }
    }
            public Object[] consultarPorDocumento(String numeroDocumento) {

        String sql = """
                SELECT
                    c.codigo_contrato,
                    c.fecha_inicio,
                    c.fecha_fin,
                    c.estado_contrato,
                    CONCAT(
                        t.nombres, ' ',
                        t.apellido_paterno, ' ',
                        t.apellido_materno
                    ) AS nombre_completo,
                    t.tipo_trabajador
                FROM contrato c
                INNER JOIN trabajador t
                    ON c.id_trabajador = t.id_trabajador
                WHERE t.numero_documento = ?
                """;

        try (
                Connection conexion = ConexionBD.conectar();
                PreparedStatement ps =
                        conexion.prepareStatement(sql)
        ) {

            ps.setString(1, numeroDocumento);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    return new Object[] {
                        rs.getString("codigo_contrato"),
                        rs.getDate("fecha_inicio").toString(),
                        rs.getDate("fecha_fin").toString(),
                        rs.getBoolean("estado_contrato"),
                        rs.getString("nombre_completo"),
                        rs.getString("tipo_trabajador")
                    };
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error al consultar contrato: "
                    + e.getMessage()
            );
        }

        return null;
    }
                public boolean actualizarFechaFin(
            String numeroDocumento,
            String nuevaFechaFin
    ) {

        String sql = """
                UPDATE contrato c
                INNER JOIN trabajador t
                    ON c.id_trabajador = t.id_trabajador
                SET c.fecha_fin = ?
                WHERE t.numero_documento = ?
                  AND c.estado_contrato = TRUE
                """;

        try (
                Connection conexion = ConexionBD.conectar();
                PreparedStatement ps =
                        conexion.prepareStatement(sql)
        ) {

            ps.setDate(
                    1,
                    convertirFecha(nuevaFechaFin)
            );

            ps.setString(
                    2,
                    numeroDocumento
            );

            int filasActualizadas =
                    ps.executeUpdate();

            return filasActualizadas > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Error al actualizar contrato: "
                    + e.getMessage()
            );

            return false;
        }
    }
}