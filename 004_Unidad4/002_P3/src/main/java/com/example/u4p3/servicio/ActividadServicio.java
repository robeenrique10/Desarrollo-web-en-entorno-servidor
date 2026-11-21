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

    public ActividadServicio(ActividadRepository actividadRepository) {
        this.actividadRepository = actividadRepository;
    }

    public List<Actividad> obtenerTodos() {
        return actividadRepository.findActividadesPorTitulo();
    }

    public List<Actividad> obtenerPorInscripcionAbierta() {
        return actividadRepository.findActividadProximaConInscripcionAbierta(LocalDate.now());
    }

    public List<Actividad> obtenerPorGratuitas() {
        return actividadRepository.findActividadGratuitasOrdenadasPorFecha(BigDecimal.ZERO);
    }

    @Transactional
    public Actividad registrar(
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
        
        return actividadRepository.save(actividad);
    }

}
