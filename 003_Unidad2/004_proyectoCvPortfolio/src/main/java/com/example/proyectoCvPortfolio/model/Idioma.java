package com.example.proyectoCvPortfolio.model;

public class Idioma {

    private String nombre;
    private String nivel;

    public Idioma(String nombre, String nivel) {
        this.nombre = nombre;
        this.nivel = nivel;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        if (nivel.equalsIgnoreCase("alto") || nivel.equalsIgnoreCase("medio") || nivel.equalsIgnoreCase("bajo")) {
            this.nivel = nivel;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Idioma{" +
                "nombre='" + nombre + '\'' +
                ", nivel='" + nivel + '\'' +
                '}';
    }
}
