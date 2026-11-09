package com.example.proyectoCvPortfolio.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class ProyectoForm {

    @NotBlank(message = "El campo título es obligatorio.")
    private String titulo;

    @NotBlank(message = "El campo año es obligatorio.")
    private String anio;

    @NotBlank(message = "El campo categoría es obligatorio.")
    @Pattern(regexp = "Todas|Aplicaciones|Sitios Web|Diseño",
            message = "Selecciona una categoría")
    private String categoria;

    @NotBlank(message = "El campo estado es obligatorio")
    @Pattern(regexp = "En curso|Terminado",
            message = "Elige una opción")
    private String estado;

    @NotBlank(message = "El campo descripción es obligatorio")
    private String descripcion;

    public ProyectoForm() {
    }

    public ProyectoForm(String titulo, String anio, String categoria, String estado, String descripcion) {
        this.titulo = titulo;
        this.anio = anio;
        this.categoria = categoria;
        this.estado = estado;
        this.descripcion = descripcion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "ProyectoForm{" +
                "titulo='" + titulo + '\'' +
                ", anio=" + anio +
                ", categoria='" + categoria + '\'' +
                ", estado='" + estado + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
