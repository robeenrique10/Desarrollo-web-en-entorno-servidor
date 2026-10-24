package com.example.p7springboot.controller;

import com.example.p7springboot.objetos.Actividad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Controller
public class CentroController {

    @Autowired
    private MessageSource messageSource;

    /**
     * Muestra la lista de actividades en el index de forma internacionalizada
     *
     * @param model el modelo para la vista
     * @param locale el idioma actual detectado por la aplicación
     * @return nombre de la vista
     */
    @GetMapping("/")
    public String index(Model model, Locale locale) {

        // Lista de actividades traducidas dinámicamente mediante el MessageSource y el Locale
        List<Actividad> actividades = Arrays.asList(
                new Actividad(
                        messageSource.getMessage("actividad.1.titulo", null, locale),
                        messageSource.getMessage("actividad.1.descripcion", null, locale),
                        messageSource.getMessage("actividad.1.fecha", null, locale),
                        messageSource.getMessage("actividad.1.duracion", null, locale),
                        messageSource.getMessage("actividad.1.lugar", null, locale)
                ),
                new Actividad(
                        messageSource.getMessage("actividad.2.titulo", null, locale),
                        messageSource.getMessage("actividad.2.descripcion", null, locale),
                        messageSource.getMessage("actividad.2.fecha", null, locale),
                        messageSource.getMessage("actividad.2.duracion", null, locale),
                        messageSource.getMessage("actividad.2.lugar", null, locale)
                ),
                new Actividad(
                        messageSource.getMessage("actividad.3.titulo", null, locale),
                        messageSource.getMessage("actividad.3.descripcion", null, locale),
                        messageSource.getMessage("actividad.3.fecha", null, locale),
                        messageSource.getMessage("actividad.3.duracion", null, locale),
                        messageSource.getMessage("actividad.3.lugar", null, locale)
                )
        );

        model.addAttribute("actividades", actividades);

        return "index";
    }

    @GetMapping("/visita")
    public String visita() {
        return "visita";
    }

}