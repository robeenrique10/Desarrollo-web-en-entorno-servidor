package org.example.maquinaria;

import org.example.personal.Maquinista;

public class Tren implements MaquinistaInyectable {

    private static final int MAX_VAGONES = 5;

    private Locomotora locomotora;
    private Maquinista maquinista;
    private Vagon[] vagones = new Vagon[MAX_VAGONES];
    private int numVagones  = 0;

    public Tren(Locomotora locomotora) {
        this.locomotora = locomotora;
    }

    @Override
    public void inyectarMaquinista(Maquinista maquinista) {
        this.maquinista = maquinista;
    }

    public boolean anadirVagon(double capacidadMaxima, double cargaActual, String tipoCarga) {
        if (numVagones >= MAX_VAGONES) {
            return false;
        }
        vagones[numVagones] = new Vagon(capacidadMaxima, cargaActual, tipoCarga);
        numVagones++;
        return true;
    }
}
