package org.example.dao;

import org.example.excusa_entrega.Excusa;
import java.sql.SQLException;
import java.util.List;


public interface ExcusaDAO {

    List<Excusa> obtenerPorNivelDramaDesc() throws SQLException;

    List<Excusa> obtenerExcusasMascotasYRetraso() throws SQLException;

    List<Excusa> obtenerExcusasSinFechaYCondicionesDramaCredibilidad() throws SQLException;

    List<Excusa> obtenerExcusasAvanzada() throws SQLException;


}
