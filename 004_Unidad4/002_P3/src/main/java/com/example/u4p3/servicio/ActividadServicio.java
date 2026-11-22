package com.example.u4p3.servicio;

import com.example.u4p3.model.Actividad;
import com.example.u4p3.repositorio.ActividadRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ActividadServicio {

    private final ActividadRepository actividadRepository;

    /**
     * Constructor del servicio
     */
    public ActividadServicio(ActividadRepository actividadRepository) {
        this.actividadRepository = actividadRepository;
    }

    /**
     * Recupera todas las actividades ordenadas alfabéticamente
     * @return
     */
    public List<Actividad> obtenerTodos() {
        return actividadRepository.findActividadesPorTitulo();
    }

    /**
     * Recupera todas las actividades con inscripción abierta
     * @return
     */
    public List<Actividad> obtenerPorInscripcionAbierta() {
        return actividadRepository.findActividadProximaConInscripcionAbierta(LocalDate.now());
    }

    /**
     * Recupera todas las actividades gratuitas
     * @return
     */
    public List<Actividad> obtenerPorGratuitas() {
        return actividadRepository.findActividadGratuitasOrdenadasPorFecha(BigDecimal.ZERO);
    }

    /**
     * Construye y guarda el objeto actividad con los datos que el controlador ya ha validado.
     * @param titulo
     * @param descripcion
     * @param fecha
     * @param duracionMinutos
     * @param precio
     * @param aforo
     * @param inscripciónAbierta
     * @return entidad persistida, con el id generado automáticamente
     */
    @Transactional
    public Actividad registrarActividad(
            String titulo,
            String descripcion,
            LocalDate fecha,
            int duracionMinutos,
            BigDecimal precio,
            int aforo,
            boolean inscripciónAbierta) {
        Actividad actividad = new Actividad();
        actividad.setTitulo(titulo.trim());
        actividad.setDescripcion(descripcion != null ? descripcion.trim() : null);
        actividad.setFecha(fecha);
        actividad.setDuracionMinutos(duracionMinutos);
        actividad.setPrecio(precio);
        actividad.setAforo(aforo);
        actividad.setInscripcionAbierta(inscripciónAbierta);

        // Guarda el objeto automáticamente
        return actividadRepository.save(actividad);
    }

}
