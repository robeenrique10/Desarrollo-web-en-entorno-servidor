package com.example.proyectoCvPortfolio.model;

import java.util.List;

public class Curriculum {

    private int id;
    private String nombre;
    private String titularProfesional;
    private String presentacion;
    private String contacto;
    private List<Formacion> formaciones;
    private List<Experiencia> experiencias;
    private List<Habilidad> habilidades;
    private List<Idioma> idiomas;

    public Curriculum(int id, String nombre, String titularProfesional, String presentacion, String contacto, List<Formacion> formaciones, List<Experiencia> experiencias, List<Habilidad> habilidades, List<Idioma> idiomas) {
        this.id = id;
        this.nombre = nombre;
        this.titularProfesional = titularProfesional;
        this.presentacion = presentacion;
        this.contacto = contacto;
        this.formaciones = formaciones;
        this.experiencias = experiencias;
        this.habilidades = habilidades;
        this.idiomas = idiomas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTitularProfesional() {
        return titularProfesional;
    }

    public void setTitularProfesional(String titularProfesional) {
        this.titularProfesional = titularProfesional;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public List<Formacion> getFormaciones() {
        return formaciones;
    }

    public void setFormaciones(List<Formacion> formaciones) {
        this.formaciones = formaciones;
    }

    public List<Experiencia> getExperiencias() {
        return experiencias;
    }

    public void setExperiencias(List<Experiencia> experiencias) {
        this.experiencias = experiencias;
    }

    public List<Habilidad> getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(List<Habilidad> habilidades) {
        this.habilidades = habilidades;
    }

    public List<Idioma> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<Idioma> idiomas) {
        this.idiomas = idiomas;
    }

    @Override
    public String toString() {
        return "Curriculum{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", titularProfesional='" + titularProfesional + '\'' +
                ", presentacion='" + presentacion + '\'' +
                ", contacto='" + contacto + '\'' +
                ", formaciones=" + formaciones +
                ", experiencias=" + experiencias +
                ", habilidades=" + habilidades +
                ", idiomas=" + idiomas +
                '}';
    }
}
