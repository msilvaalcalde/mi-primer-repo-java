package com.mycompany.sistemapersonalmunicipal.dao;

import com.mycompany.sistemapersonalmunicipal.dao.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReporteDAO {


    // =====================================================
    // REPORTE GENERAL DE TRABAJADORES ACTIVOS
    // =====================================================

    public String obtenerReporteGeneral() {

        StringBuilder reporte = new StringBuilder();

        String sql = """
                SELECT
                    t.codigo_trabajador,
                    t.numero_documento,

                    CONCAT(
                        t.nombres, ' ',
                        t.apellido_paterno, ' ',
                        t.apellido_materno
                    ) AS nombre_completo,

                    t.tipo_trabajador,
                    t.regimen_laboral,
                    t.fecha_ingreso,

                    a.nombre_area,
                    c.nombre_cargo,

                    CASE

                        WHEN t.tipo_trabajador =
                             'EMPLEADO MUNICIPAL'

                        THEN t.sueldo_mensual
                             + t.bonificacion


                        WHEN t.tipo_trabajador =
                             'OBRERO MUNICIPAL'

                        THEN t.jornal_diario
                             * t.dias_trabajados


                        ELSE 0

                    END AS sueldo_calculado

                FROM trabajador t

                INNER JOIN area a
                    ON t.id_area = a.id_area

                INNER JOIN cargo c
                    ON t.id_cargo = c.id_cargo

                WHERE t.estado = TRUE

                ORDER BY t.codigo_trabajador
                """;


        try (
                Connection conexion =
                        ConexionBD.conectar();

                PreparedStatement ps =
                        conexion.prepareStatement(sql);

                ResultSet rs =
                        ps.executeQuery()
        ) {

            reporte.append(
                    "REPORTE GENERAL DE TRABAJADORES ACTIVOS\n"
            );

            reporte.append(
                    "============================================================\n\n"
            );


            int cantidad = 0;


            while (rs.next()) {

                cantidad++;


                reporte.append("Código: ")
                        .append(
                                rs.getString(
                                        "codigo_trabajador"
                                )
                        )
                        .append("\n");


                reporte.append("Documento: ")
                        .append(
                                rs.getString(
                                        "numero_documento"
                                )
                        )
                        .append("\n");


                reporte.append("Nombre: ")
                        .append(
                                rs.getString(
                                        "nombre_completo"
                                )
                        )
                        .append("\n");


                reporte.append("Tipo: ")
                        .append(
                                rs.getString(
                                        "tipo_trabajador"
                                )
                        )
                        .append("\n");


                reporte.append("Régimen: ")
                        .append(
                                rs.getString(
                                        "regimen_laboral"
                                )
                        )
                        .append("\n");


                reporte.append("Fecha de ingreso: ")
                        .append(
                                rs.getDate(
                                        "fecha_ingreso"
                                )
                        )
                        .append("\n");


                reporte.append("Área: ")
                        .append(
                                rs.getString(
                                        "nombre_area"
                                )
                        )
                        .append("\n");


                reporte.append("Cargo: ")
                        .append(
                                rs.getString(
                                        "nombre_cargo"
                                )
                        )
                        .append("\n");


                reporte.append("Sueldo calculado: S/ ")
                        .append(
                                rs.getDouble(
                                        "sueldo_calculado"
                                )
                        )
                        .append("\n");


                reporte.append(
                        "------------------------------------------------------------\n\n"
                );
            }


            reporte.append("TOTAL DE TRABAJADORES ACTIVOS: ")
                    .append(cantidad)
                    .append("\n");


        } catch (SQLException e) {

            reporte.setLength(0);

            reporte.append(
                    "Error al generar reporte general: "
            );

            reporte.append(
                    e.getMessage()
            );
        }


        return reporte.toString();
    }



    // =====================================================
    // REPORTE POR TIPO DE TRABAJADOR
    // =====================================================

    public String obtenerReportePorTipo() {

        StringBuilder reporte = new StringBuilder();


        String sql = """
                SELECT
                    tipo_trabajador,

                    COUNT(*) AS cantidad,

                    SUM(
                        CASE

                            WHEN tipo_trabajador =
                                 'EMPLEADO MUNICIPAL'

                            THEN sueldo_mensual
                                 + bonificacion


                            WHEN tipo_trabajador =
                                 'OBRERO MUNICIPAL'

                            THEN jornal_diario
                                 * dias_trabajados


                            ELSE 0

                        END
                    ) AS total_remuneraciones

                FROM trabajador

                WHERE estado = TRUE

                GROUP BY tipo_trabajador

                ORDER BY tipo_trabajador
                """;


        try (
                Connection conexion =
                        ConexionBD.conectar();

                PreparedStatement ps =
                        conexion.prepareStatement(sql);

                ResultSet rs =
                        ps.executeQuery()
        ) {


            reporte.append(
                    "REPORTE POR TIPO DE TRABAJADOR\n"
            );

            reporte.append(
                    "============================================================\n\n"
            );


            int totalTrabajadores = 0;


            while (rs.next()) {

                int cantidad =
                        rs.getInt("cantidad");

                totalTrabajadores += cantidad;


                reporte.append("Tipo: ")
                        .append(
                                rs.getString(
                                        "tipo_trabajador"
                                )
                        )
                        .append("\n");


                reporte.append("Cantidad: ")
                        .append(cantidad)
                        .append("\n");


                reporte.append(
                        "Total remuneraciones calculadas: S/ "
                )
                        .append(
                                rs.getDouble(
                                        "total_remuneraciones"
                                )
                        )
                        .append("\n");


                reporte.append(
                        "------------------------------------------------------------\n\n"
                );
            }


            reporte.append(
                    "TOTAL GENERAL DE TRABAJADORES: "
            )
                    .append(totalTrabajadores)
                    .append("\n");


        } catch (SQLException e) {

            reporte.setLength(0);

            reporte.append(
                    "Error al generar reporte por tipo: "
            );

            reporte.append(
                    e.getMessage()
            );
        }


        return reporte.toString();
    }
}