package org.example.patronDI;

public class Casa {

    private double area;
    private Tejado tejado;
    private Pared[] paredes;

    public Casa(Pared[] paredes, Tejado tejado, double area) {
        this.paredes = paredes;
        this.tejado = tejado;
        this.area = area;
    }

    public void mostrarInfo() {
        System.out.println("Casa de " + area + " m²");
        for (int i = 0; i < paredes.length; i++) {
            System.out.println("Pared " + (i + 1) + " - altura: " + paredes[i].getAltura());
        }
        tejado.darSoporte();
    }
}
