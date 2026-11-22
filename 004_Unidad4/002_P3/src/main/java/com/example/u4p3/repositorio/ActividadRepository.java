package com.example.u4p3.repositorio;

import com.example.u4p3.model.Actividad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface ActividadRepository extends JpaRepository<Actividad, Long> {

    /**
     * Consulta SQL a la base de datos sobre todas las actividades ordenadas por título
     */
    @Query("SELECT actividad FROM Actividad actividad ORDER BY actividad.titulo ASC")
    List<Actividad> findActividadesPorTitulo();

    /**
     * Consulta SQL a la base de datos sobre todas las actividades con inscripción abierta
     */
    @Query("SELECT actividad FROM Actividad actividad WHERE actividad.inscripcionAbierta = true AND actividad.fecha >= :hoy ORDER BY actividad.fecha ASC")
    List<Actividad> findActividadProximaConInscripcionAbierta(@Param("hoy") LocalDate hoy);

    /**
     *Consulta SQL a la base de datos sobre todas las actividades que sean gratuitas
     */
    @Query("SELECT actividad FROM Actividad actividad WHERE actividad.precio = :precioCero ORDER BY actividad.fecha ASC")
    List<Actividad> findActividadGratuitasOrdenadasPorFecha(@Param("precioCero") BigDecimal precioCero);
}
