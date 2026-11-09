package org.example;

import org.example.dao.ExcusaDAO;
import org.example.dao.ExcusaDAOImpl;
import org.example.excusa_entrega.Excusa;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ExcusaDAO dao = new ExcusaDAOImpl();

        try {
            System.out.println("=== 1. CONSULTA BÁSICA ===");
            List<Excusa> r1 = dao.obtenerPorNivelDramaDesc();
            for (Excusa e : r1) {
                System.out.println("- " + e.getAlumno() + " | Drama: " + e.getNivel_drama() + " | " + e.getExcusa());
            }

            System.out.println("\n=== 2. CONSULTA LIKE Y BETWEEN ===");
            List<Excusa> r2 = dao.obtenerExcusasMascotasYRetraso();
            for (Excusa e : r2) {
                System.out.println("- " + e.getAlumno() + " | Retraso: " + e.getDias_retraso() + " | " + e.getExcusa());
            }

            System.out.println("\n=== 3. CONSULTA IS NULL ===");
            List<Excusa> r3 = dao.obtenerExcusasSinFechaYCondicionesDramaCredibilidad();
            for (Excusa e : r3) {
                System.out.println("- " + e.getAlumno() + " | Fecha: " + e.getFecha_entrega() + " | Drama: " + e.getNivel_drama() + " | Credibilidad: " + e.getCredibilidad());
            }

            System.out.println("\n=== 4. CONSULTA AVANZADA ===");
            List<Excusa> r4 = dao.obtenerExcusasAvanzada();
            for (Excusa e : r4) {
                System.out.println("- " + e.getAlumno() + " | Drama: " + e.getNivel_drama() + " | Credibilidad: " + e.getCredibilidad() + " | Retraso: " + e.getDias_retraso() + " | " + e.getExcusa());
            }

        } catch (SQLException e) {
            System.err.println("Error al ejecutar las consultas SQL: " + e.getMessage());
            e.printStackTrace();
        }
    }
}