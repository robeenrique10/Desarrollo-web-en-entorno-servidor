package org.example.dao;

import org.example.dao.ExcusaDAO;
import org.example.excusa_entrega.Excusa;
import org.example.Conexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class excusaDAOImpl implements ExcusaDAO {


    @Override
    public List<Excusa> obtenerPorNivelDramaDesc() throws SQLException {

        List<Excusa> lista = new ArrayList<>();
        String sql = "SELECT alumno, excusa, dias_retraso, nivel_drama " +
                "FROM excusa_entrega " +
                "ORDER BY nivel_drama DESC";

        Connection conn = Conexion.getConexion();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String alumno = rs.getString("alumno");
                String excusa = rs.getString("excusa");
                int diasRetraso = rs.getInt("dias_retraso");
                int nivelDrama = rs.getInt("nivel_drama");

                // Para los campos no consultados en esta query se pasan valores por defecto
                lista.add(new Excusa(0, alumno, excusa, diasRetraso, nivelDrama, 0, null));
            }
        }
        return lista;
    }

    @Override
    public List<Excusa> obtenerExcusasMascotasYRetraso() throws SQLException {
        return List.of();
    }

    @Override
    public List<Excusa> obtenerExcusasSinFechaYCondicionesDramaCredibilidad() throws SQLException {
        return List.of();
    }

    @Override
    public List<Excusa> obtenerExcusasAvanzada() throws SQLException {
        return List.of();
    }
}
