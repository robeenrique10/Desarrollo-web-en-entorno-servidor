package org.example.patron;

public class Configurador {

    private static final Configurador INSTANCIA = new Configurador();

    private String configuracion = "";

    private Configurador() {
    }

    public String getConfiguracion() {
        return configuracion;
    }

    public void setConfiguracion(String configuracion) {
        this.configuracion = configuracion;
    }

    public static Configurador obtenerInstancia() {
        return INSTANCIA;
    }

}
