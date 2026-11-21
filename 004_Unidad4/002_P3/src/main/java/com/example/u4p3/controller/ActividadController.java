package com.example.u4p3.controller;


import com.example.u4p3.dto.NuevaActividadForm;
import com.example.u4p3.model.Actividad;
import com.example.u4p3.servicio.ActividadServicio;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ActividadController {

    private final ActividadServicio actividadServicio;

    public ActividadController(ActividadServicio actividadServicio) {
        this.actividadServicio = actividadServicio;
    }

    @GetMapping("/")
    public String mostrarInventario(Model model) {
        prepararPaginaSinFiltros(model, new NuevaActividadForm());
        return "index";
    }

    @GetMapping("/proximas-abiertas")
    public String mostrarInventario2(Model model) {
        prepararPaginaProximaApertura(model, new NuevaActividadForm());
        return "index";
    }

    @GetMapping("/gratuitas")
    public String mostrarInventario3(Model model) {
        prepararPaginaGratiutas(model, new NuevaActividadForm());
        return "index";
    }

    @PostMapping("/actividades")
    public String registrarActividad(
            @Valid @ModelAttribute("nuevaActividad") NuevaActividadForm formulario,
            BindingResult resultado,
            Model model,
            RedirectAttributes redirectAttributes) {
            if (resultado.hasErrors()) {
                prepararPaginaSinFiltros(model, formulario);
                return "index";
            }

            actividadServicio.registrar(
                    formulario.getTitulo(),
                    formulario.getDescripcion(),
                    formulario.getFecha(),
                    formulario.getDuracionMinutos(),
                    formulario.getPrecio(),
                    formulario.getAforo(),
                    formulario.isInscripcionAbierta());

            redirectAttributes.addFlashAttribute("mensajeExito", "Actividad añadida con éxito");
        return "redirect:/";
    }

    private void prepararPaginaSinFiltros(Model model, NuevaActividadForm formulario) {
        List<Actividad> actividades = actividadServicio.obtenerTodos();
        model.addAttribute("actividades", actividades != null ? actividades : List.of());
        model.addAttribute("nuevaActividad", formulario);
    }

    private void prepararPaginaProximaApertura(Model model, NuevaActividadForm formulario) {
        List<Actividad> actividades = actividadServicio.obtenerPorInscripcionAbierta();
        model.addAttribute("actividades", actividades != null ? actividades : List.of());
        model.addAttribute("nuevaActividad", formulario);
    }

    private void prepararPaginaGratiutas(Model model, NuevaActividadForm formulario) {
        List<Actividad> actividades = actividadServicio.obtenerPorGratuitas();
        model.addAttribute("actividades", actividades != null ? actividades : List.of());
        model.addAttribute("nuevaActividad", formulario);
    }
}
