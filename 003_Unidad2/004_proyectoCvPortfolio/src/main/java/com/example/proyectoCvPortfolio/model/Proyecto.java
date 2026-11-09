package com.example.proyectoCvPortfolio.model;

import java.util.List;

public record Proyecto(Integer id, String titulo, Integer anio, String categoria, String descripcion, String estado,
                       List<String> tecnologias, String enlace, String imagen) {

}