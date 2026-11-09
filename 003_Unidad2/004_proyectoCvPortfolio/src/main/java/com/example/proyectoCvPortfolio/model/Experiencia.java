package com.example.proyectoCvPortfolio.model;

public class Experiencia {

    private String puesto;
    private String organizacion;
    private String periodo;

    public Experiencia(String puesto, String organizacion, String periodo) {
        this.puesto = puesto;
        this.organizacion = organizacion;
        this.periodo = periodo;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(String organizacion) {
        this.organizacion = organizacion;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    @Override
    public String toString() {
        return "Experiencia{" +
                "puesto='" + puesto + '\'' +
                ", organizacion='" + organizacion + '\'' +
                ", periodo='" + periodo + '\'' +
                '}';
    }
}
