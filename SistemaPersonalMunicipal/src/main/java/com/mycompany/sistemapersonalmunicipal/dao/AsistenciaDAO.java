package com.mycompany.sistemapersonalmunicipal.dao;

import com.mycompany.sistemapersonalmunicipal.dao.ConexionBD;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

public class AsistenciaDAO {

    public Object[] buscarTrabajadorPorDocumento(
            String numeroDocumento
    ) {

        String sql = """
                SELECT
                    id_trabajador,
                    codigo_trabajador,
                    CONCAT(
                        nombres, ' ',
                        apellido_paterno, ' ',
                        apellido_materno
                    ) AS nombre_completo,
                    tipo_trabajador
                FROM trabajador
                WHERE numero_documento = ?
                  AND estado = TRUE
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
                        rs.getInt("id_trabajador"),
                        rs.getString("codigo_trabajador"),
                        rs.getString("nombre_completo"),
                        rs.getString("tipo_trabajador")
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


    public boolean registrar(
            String numeroDocumento,
            String fecha,
            String horaIngreso,
            String horaSalida
    ) {

        boolean tardanza =
                horaIngreso.compareTo("08:00") > 0;

        String sql = """
                INSERT INTO asistencia (
                    fecha,
                    hora_ingreso,
                    hora_salida,
                    tardanza,
                    id_trabajador
                )
                VALUES (
                    ?,
                    ?,
                    ?,
                    ?,
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

            ps.setDate(
                    1,
                    Date.valueOf(fecha)
            );

            ps.setTime(
                    2,
                    Time.valueOf(horaIngreso + ":00")
            );

            ps.setTime(
                    3,
                    Time.valueOf(horaSalida + ":00")
            );

            ps.setBoolean(
                    4,
                    tardanza
            );

            ps.setString(
                    5,
                    numeroDocumento
            );

            int filasInsertadas =
                    ps.executeUpdate();

            return filasInsertadas > 0;

        } catch (IllegalArgumentException e) {

            throw new IllegalArgumentException(
                    "Use fecha AAAA-MM-DD y horas HH:MM."
            );

        } catch (SQLException e) {

            System.out.println(
                    "Error al registrar asistencia: "
                    + e.getMessage()
            );

            return false;
        }
    }
        public java.util.List<Object[]> listarPorDocumento(
            String numeroDocumento
    ) {

        java.util.List<Object[]> lista =
                new java.util.ArrayList<>();

        String sql = """
                SELECT
                    a.id_asistencia,
                    a.fecha,
                    a.hora_ingreso,
                    a.hora_salida,
                    a.tardanza
                FROM asistencia a
                INNER JOIN trabajador t
                    ON a.id_trabajador = t.id_trabajador
                WHERE t.numero_documento = ?
                ORDER BY a.fecha DESC, a.hora_ingreso DESC
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

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    Object[] fila = {

                        rs.getInt(
                                "id_asistencia"
                        ),

                        rs.getDate(
                                "fecha"
                        ).toString(),

                        rs.getTime(
                                "hora_ingreso"
                        ).toString(),

                        rs.getTime(
                                "hora_salida"
                        ).toString(),

                        rs.getBoolean(
                                "tardanza"
                        )
                    };

                    lista.add(fila);
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error al listar asistencias: "
                    + e.getMessage()
            );
        }

        return lista;
    }
}