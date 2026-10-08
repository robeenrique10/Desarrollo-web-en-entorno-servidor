package org.example.patron;

public class Presidente {

    private static final Presidente INSTANCIA = new Presidente();

    private String nombre;
    private String apellidos;
    private int anioEleccion;

    private Presidente() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellidos;
    }

    public void setApellido(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getAnioEleccion() {
        return anioEleccion;
    }

    public void setAnioEleccion(int anioEleccion) {
        this.anioEleccion = anioEleccion;
    }

    public static Presidente obtenerInstancia() {
        return INSTANCIA;
    }

    @Override
    public String toString() {
        return  "nombre: '" + nombre + '\'' +
                ", apellidos: '" + apellidos + '\'' +
                ", anioEleccion: " + anioEleccion;
    }
}
