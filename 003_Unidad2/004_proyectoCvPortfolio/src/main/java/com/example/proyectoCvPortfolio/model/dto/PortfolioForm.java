package com.example.proyectoCvPortfolio.model.dto;

import jakarta.validation.constraints.*;

import java.util.ArrayList;
import java.util.List;

public class PortfolioForm {

    @NotBlank(message = "El campo nombre es obligatorio.")
    private String nombre;

    @NotBlank(message = "El campo titular profesional es obligatorio.")
    private String titular;

    @NotBlank(message = "El campo presentación es obligatorio.")
    private String presentacion;

    private String ciudad;

    @Email(message = "Introduzca una dirección de correo válida.")
    private String correo;

    private List<ProyectoForm> proyectos = new ArrayList<>();

    public PortfolioForm() {
    }

    public PortfolioForm(String nombre, String titular, String presentacion, String ciudad, String correo, List<ProyectoForm> proyectos) {
        this.nombre = nombre;
        this.titular = titular;
        this.presentacion = presentacion;
        this.ciudad = ciudad;
        this.correo = correo;
        this.proyectos = proyectos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public List<ProyectoForm> getProyectos() {
        return proyectos;
    }

    public void setProyectos(List<ProyectoForm> proyectos) {
        this.proyectos = proyectos;
    }

    @Override
    public String toString() {
        return "PortfolioForm{" +
                "nombre='" + nombre + '\'' +
                ", titular='" + titular + '\'' +
                ", presentacion='" + presentacion + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", correo='" + correo + '\'' +
                ", proyectos=" + proyectos +
                '}';
    }
}
