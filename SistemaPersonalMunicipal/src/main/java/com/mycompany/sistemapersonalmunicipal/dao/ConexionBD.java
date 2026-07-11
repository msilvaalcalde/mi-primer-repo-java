package com.mycompany.sistemapersonalmunicipal.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String URL =
            "jdbc:mysql://localhost:3306/sistema_personal_municipal";

    private static final String USUARIO = "root";

    private static final String CONTRASENA = "147.,rango";

    public static Connection conectar() throws SQLException {

        return DriverManager.getConnection(
                URL,
                USUARIO,
                CONTRASENA
        );
    }
}