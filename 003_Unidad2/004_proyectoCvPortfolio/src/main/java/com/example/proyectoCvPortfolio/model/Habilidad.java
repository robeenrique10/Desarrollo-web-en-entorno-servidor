package com.example.proyectoCvPortfolio.model;

public class Habilidad {

    private String nombre;
    private String nivel;

    public Habilidad(String nombre, String nivel) {
        this.nombre = nombre;
        this.nivel = nivel;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        if (nivel.equalsIgnoreCase("alto") || nivel.equalsIgnoreCase("medio") || nivel.equalsIgnoreCase("bajo")) {
            this.nivel = nivel;
        }
    }

    @Override
    public String toString() {
        return "Habilidad{" +
                "nombre='" + nombre + '\'' +
                ", nivel='" + nivel + '\'' +
                '}';
    }
}
