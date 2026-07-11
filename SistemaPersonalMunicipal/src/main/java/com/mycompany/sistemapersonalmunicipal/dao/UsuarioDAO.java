package com.mycompany.sistemapersonalmunicipal.dao;

import com.mycompany.sistemapersonalmunicipal.dao.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    public boolean validarLogin(String usuario, String contrasena) {

        String sql = """
                SELECT id_usuario
                FROM usuario
                WHERE nombre_usuario = ?
                  AND contrasena = ?
                  AND estado = TRUE
                """;

        try (
                Connection conexion = ConexionBD.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql)
        ) {

            ps.setString(1, usuario);
            ps.setString(2, contrasena);

            try (ResultSet rs = ps.executeQuery()) {

                return rs.next();
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error al validar usuario: " + e.getMessage()
            );

            return false;
        }
    }
}