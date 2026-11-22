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

    /**
     * GET el cual nos muestra la página inicial con una ordenación de actividades únicamente ordenas por
     * el nombre de la actividad.
     * El formulario nos dejará incluir diferentes actividades con los campos que se muestran en él.
     * @param model
     * @return página index donde se muestra todo el contenido
     */
    @GetMapping("/")
    public String mostrarInventario(Model model) {
        prepararPaginaSinFiltros(model, new NuevaActividadForm());
        return "index";
    }

    /**
     * GET el cual nos muestra la página inicial con una ordenación de actividades ordenadas por
     * el plazo de la actividad, en este caso por el orden de la apertura de estas.
     * El formulario nos dejará incluir diferentes actividades con los campos que se muestran en él.
     * @param model
     * @return página index con ordenación de las actividades por apertura
     */
    @GetMapping("/proximas-abiertas")
    public String mostrarInventario2(Model model) {
        prepararPaginaProximaApertura(model, new NuevaActividadForm());
        return "index";
    }

    /**
     * GET el cual nos muestra la página inicial con una ordenación de actividades por si son gratuitas.
     * El formulario nos dejará incluir diferentes actividades con los campos que se muestran en él.
     * @param model
     * @return página index con ordenación de actividades gratuitas
     */
    @GetMapping("/gratuitas")
    public String mostrarInventario3(Model model) {
        prepararPaginaGratiutas(model, new NuevaActividadForm());
        return "index";
    }

    /**
     * Método POST que valida los datos introducidos en el formulario con el Servidor, en el caso de que
     * sean válidos se introducirá otra tupla en la base de datos.
     * @param formulario
     * @param resultado
     * @param model
     * @param redirectAttributes
     * @return la página de inicio con la nueva actividad introducida en la base de datos.
     */
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

            actividadServicio.registrarActividad(
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

    /**
     * Método que prepara la página de inicio con las actividades ordenadas por nombre de la actividad
     * @param model
     * @param formulario
     */
    private void prepararPaginaSinFiltros(Model model, NuevaActividadForm formulario) {
        List<Actividad> actividades = actividadServicio.obtenerTodos();
        model.addAttribute("actividades", actividades != null ? actividades : List.of());
        model.addAttribute("nuevaActividad", formulario);
    }

    /**
     * Método que preparar la página de inicio con las actividades ordenadas por la inscripción abierta
     * @param model
     * @param formulario
     */
    private void prepararPaginaProximaApertura(Model model, NuevaActividadForm formulario) {
        List<Actividad> actividades = actividadServicio.obtenerPorInscripcionAbierta();
        model.addAttribute("actividades", actividades != null ? actividades : List.of());
        model.addAttribute("nuevaActividad", formulario);
    }

    /**
     * Método que prepara la página nde inicio con las actividades filtradas por si son gratuitas o no
     * @param model
     * @param formulario
     */
    private void prepararPaginaGratiutas(Model model, NuevaActividadForm formulario) {
        List<Actividad> actividades = actividadServicio.obtenerPorGratuitas();
        model.addAttribute("actividades", actividades != null ? actividades : List.of());
        model.addAttribute("nuevaActividad", formulario);
    }
}
