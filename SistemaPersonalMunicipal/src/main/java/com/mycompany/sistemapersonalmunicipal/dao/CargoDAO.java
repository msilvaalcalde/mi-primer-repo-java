package com.mycompany.sistemapersonalmunicipal.dao;

import com.mycompany.sistemapersonalmunicipal.dao.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class CargoDAO {

    public List<Object[]> listarTodos() {

        List<Object[]> lista = new ArrayList<>();

        String sql = """
                SELECT
                    id_cargo,
                    codigo_cargo,
                    nombre_cargo
                FROM cargo
                WHERE estado = TRUE
                ORDER BY id_cargo
                """;

        try (
                Connection conexion = ConexionBD.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                Object[] fila = {
                    rs.getInt("id_cargo"),
                    rs.getString("codigo_cargo"),
                    rs.getString("nombre_cargo")
                };

                lista.add(fila);
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error al listar cargos: "
                    + e.getMessage()
            );
        }

        return lista;
    }
}