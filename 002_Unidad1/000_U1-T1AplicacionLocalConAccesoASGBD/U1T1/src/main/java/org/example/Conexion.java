package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Conexion {

    private static final String URL = "jdbc:mariadb:localhost:3306/excusa_entrega";
    private static final String USUARIO = "root";
    private static final String CONTRASENA = "";

    private static Conexion instancia;
    private Connection conexion;

    private Conexion() {
    }

    public static synchronized Conexion getInstancia() {
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }

    public static synchronized Connection getConexion() throws SQLException {
        // DriverManager utiliza el controlador MariaDB para abrir la conexión.
        if (conexion == null || conexion.isClosed()) {
            conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
        }
        return conexion;
    }

    public void cerrar() throws SQLException {
        if (conexion != null && !conexion.isClosed()) {
            conexion.close();
        }
    }

}
