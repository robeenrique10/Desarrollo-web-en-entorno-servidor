package org.example.maquinaria;

class Vagon {

    private final double capacidadMaxima;
    private double cargaActual;
    private String tipoCarga;

   Vagon(double capacidadMaxima, double cargaActual, String carga) {
        this.capacidadMaxima = capacidadMaxima;
        this.tipoCarga = carga;
        setCargaActual(cargaActual);
    }

    public double getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public double getCargaActual() {
        return cargaActual;
    }

    public void setCargaActual(double cargaActual) {
       if (cargaActual< 0 || cargaActual > this.capacidadMaxima) {
           throw new IllegalArgumentException("La carga debe estar entre 0 y " + capacidadMaxima);
       }
        this.cargaActual = cargaActual;
    }

    public String getTipoCarga() {
        return tipoCarga;
    }

    public void setTipoCarga(String tipoCarga) {
        this.tipoCarga = tipoCarga;
    }

    @Override
    public String toString() {
        return "Vagon{" +
                "capacidadMaxima=" + capacidadMaxima +
                ", cargaActual=" + cargaActual +
                ", tipoCarga='" + tipoCarga + '\'' +
                '}';
    }
}
