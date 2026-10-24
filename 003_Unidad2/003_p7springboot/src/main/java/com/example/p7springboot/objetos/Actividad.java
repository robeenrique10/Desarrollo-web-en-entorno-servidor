package com.example.p7springboot.objetos;

public class Actividad {

    private String titulo;
    private String descripcion;
    private String fecha;
    private String duracion;
    private String lugar;

    public Actividad(String titulo, String descripcion, String fecha, String duracion, String lugar) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.duracion = duracion;
        this.lugar = lugar;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDuracion() {
        return duracion = duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }
}
