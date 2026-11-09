package com.example.proyectoCvPortfolio.model;

public class Formacion {

    private String nombre;
    private String centro;
    private Integer anioFinalizacion;

    public Formacion(String nombre, String centro, Integer anioFinalizacion) {
        this.nombre = nombre;
        this.centro = centro;
        this.anioFinalizacion = anioFinalizacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCentro() {
        return centro;
    }

    public void setCentro(String centro) {
        this.centro = centro;
    }

    public Integer getAnioFinalizacion() {
        return anioFinalizacion;
    }

    public void setAnioFinalizacion(Integer anioFinalizacion) {
        this.anioFinalizacion = anioFinalizacion;
    }

    @Override
    public String toString() {
        return "Formacion{" +
                "nombre='" + nombre + '\'' +
                ", centro='" + centro + '\'' +
                ", anioFinalizacion=" + anioFinalizacion +
                '}';
    }
}
