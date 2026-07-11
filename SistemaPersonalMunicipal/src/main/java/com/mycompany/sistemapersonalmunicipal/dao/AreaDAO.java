package com.mycompany.sistemapersonalmunicipal.dao;

import com.mycompany.sistemapersonalmunicipal.dao.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AreaDAO {

    public boolean insertar(String codigoArea, String nombreArea) {

        String sql = """
                INSERT INTO area (
                    codigo_area,
                    nombre_area,
                    estado
                )
                VALUES (?, ?, TRUE)
                """;

        try (
                Connection conexion = ConexionBD.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql)
        ) {

            ps.setString(1, codigoArea);
            ps.setString(2, nombreArea);

            int filasInsertadas = ps.executeUpdate();

            return filasInsertadas > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Error al registrar área: "
                    + e.getMessage()
            );

            return false;
        }
    }
        public List<Object[]> listarTodas() {

        List<Object[]> lista = new ArrayList<>();

        String sql = """
                SELECT
                    id_area,
                    codigo_area,
                    nombre_area,
                    estado
                FROM area
                WHERE estado = TRUE
                ORDER BY id_area
                """;

        try (
                Connection conexion = ConexionBD.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                Object[] fila = {
                    rs.getInt("id_area"),
                    rs.getString("codigo_area"),
                    rs.getString("nombre_area")
                };

                lista.add(fila);
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error al listar áreas: "
                    + e.getMessage()
            );
        }

        return lista;
    }
            public Object[] buscarPorCodigo(String codigoArea) {

        String sql = """
                SELECT
                    id_area,
                    codigo_area,
                    nombre_area
                FROM area
                WHERE codigo_area = ?
                  AND estado = TRUE
                """;

        try (
                Connection conexion = ConexionBD.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql)
        ) {

            ps.setString(1, codigoArea);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    return new Object[] {
                        rs.getInt("id_area"),
                        rs.getString("codigo_area"),
                        rs.getString("nombre_area")
                    };
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error al buscar área: "
                    + e.getMessage()
            );
        }

        return null;
    }
        public boolean actualizar(
             String codigoArea,
             String nuevoNombre
     ) {

         String sql = """
                 UPDATE area
                 SET nombre_area = ?
                 WHERE codigo_area = ?
                   AND estado = TRUE
                 """;

         try (
                 Connection conexion = ConexionBD.conectar();
                 PreparedStatement ps = conexion.prepareStatement(sql)
         ) {

             ps.setString(1, nuevoNombre);
             ps.setString(2, codigoArea);

             int filasActualizadas = ps.executeUpdate();

             return filasActualizadas > 0;

         } catch (SQLException e) {

             System.out.println(
                     "Error al actualizar área: "
                     + e.getMessage()
             );

             return false;
         }
    }
            public boolean desactivar(String codigoArea) {

        String sql = """
                UPDATE area
                SET estado = FALSE
                WHERE codigo_area = ?
                  AND estado = TRUE
                """;

        try (
                Connection conexion = ConexionBD.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql)
        ) {

            ps.setString(1, codigoArea);

            int filasActualizadas = ps.executeUpdate();

            return filasActualizadas > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Error al desactivar área: "
                    + e.getMessage()
            );

            return false;
        }
    }
    public boolean reactivar(String codigoArea) {

        String sql = """
                UPDATE area
                SET estado = TRUE
                WHERE codigo_area = ?
                  AND estado = FALSE
                """;

        try (
                Connection conexion = ConexionBD.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql)
        ) {

            ps.setString(1, codigoArea);

            int filasActualizadas = ps.executeUpdate();

            return filasActualizadas > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Error al reactivar área: "
                    + e.getMessage()
            );

            return false;
        }
    }
    public Object[] buscarPorCodigoIncluyendoInactivos(
            String codigoArea
    ) {

        String sql = """
                SELECT
                    id_area,
                    codigo_area,
                    nombre_area,
                    estado
                FROM area
                WHERE codigo_area = ?
                """;

        try (
                Connection conexion = ConexionBD.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql)
        ) {

            ps.setString(1, codigoArea);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    return new Object[] {
                        rs.getInt("id_area"),
                        rs.getString("codigo_area"),
                        rs.getString("nombre_area"),
                        rs.getBoolean("estado")
                    };
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error al buscar área: "
                    + e.getMessage()
            );
        }

        return null;
    }
}