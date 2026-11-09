package com.example.p2springboot;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PlanEstudioController {

    @GetMapping("/")
    public String index(@RequestParam(name = "nombre", required = false, defaultValue = "estudiante") String nombre, Model model) {

        //Creamos las listas de las asignaturas y de las duraciones
        List<String> asignaturas = new ArrayList<>();
        asignaturas.add("Programación");
        asignaturas.add("Base de Datos");
        asignaturas.add("Lenguaje de marcas");
        List<Integer> duraciones = new ArrayList<>();
        duraciones.add(25);
        duraciones.add(40);
        duraciones.add(30);

        //Creamos los models
        model.addAttribute("nombreEstudiante", nombre);
        model.addAttribute("asignaturas", asignaturas);
        model.addAttribute("duraciones", duraciones);

        //Devolvemos la lista
        return "plan-estudio";
    }



}
