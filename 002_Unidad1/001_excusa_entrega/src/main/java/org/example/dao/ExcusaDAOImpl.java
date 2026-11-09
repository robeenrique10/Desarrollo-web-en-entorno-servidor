package org.example.dao;

import org.example.Conexion;
import org.example.excusa_entrega.Excusa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExcusaDAOImpl implements ExcusaDAO {

    @Override
    public List<Excusa> obtenerPorNivelDramaDesc() throws SQLException {
        List<Excusa> excusas = new ArrayList<>();
        String sql = "SELECT * FROM excusa_entrega ORDER BY nivel_drama DESC";

        try (Connection conn = Conexion.getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                excusas.add(mapearExcusa(rs));
            }
        }
        return excusas;
    }

    @Override
    public List<Excusa> obtenerExcusasMascotasYRetraso() throws SQLException {
        List<Excusa> excusas = new ArrayList<>();
        String sql = "SELECT * FROM excusa_entrega " +
                "WHERE (excusa LIKE '%perro%' OR excusa LIKE '%gato%') " +
                "AND dias_retraso BETWEEN 2 AND 6 ORDER BY dias_retraso DESC";

        try (Connection conn = Conexion.getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                excusas.add(mapearExcusa(rs));
            }
        }
        return excusas;
    }

    @Override
    public List<Excusa> obtenerExcusasSinFechaYCondicionesDramaCredibilidad() throws SQLException {
        List<Excusa> excusas = new ArrayList<>();
        String sql = "SELECT * FROM excusa_entrega " +
                "WHERE fecha_entrega IS NULL " +
                "AND (credibilidad < 4 OR nivel_drama >= 9)";

        try (Connection conn = Conexion.getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                excusas.add(mapearExcusa(rs));
            }
        }
        return excusas;
    }

    @Override
    public List<Excusa> obtenerExcusasAvanzada() throws SQLException {
        List<Excusa> excusas = new ArrayList<>();
        String sql = "SELECT * FROM excusa_entrega " +
                "WHERE (nivel_drama >= 8 AND credibilidad <= 3) " +
                "OR (dias_retraso BETWEEN 2 AND 5 AND excusa LIKE '%perro%') " +
                "ORDER BY dias_retraso DESC";

        try (Connection conn = Conexion.getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                excusas.add(mapearExcusa(rs));
            }
        }
        return excusas;
    }

    private Excusa mapearExcusa(ResultSet rs) throws SQLException {
        return new Excusa(
                rs.getInt("id"),
                rs.getString("alumno"),
                rs.getString("curso"),
                rs.getString("excusa"),
                rs.getInt("dias_retraso"),
                rs.getInt("credibilidad"),
                rs.getString("fecha_entrega"),
                rs.getBoolean("aprobada_por_profesor"),
                rs.getInt("nivel_drama")
        );
    }
}