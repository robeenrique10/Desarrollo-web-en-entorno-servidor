package org.example.patron;

public class Rectangulo extends Figura{

    public Rectangulo(String color) {
        super(color);
    }

    @Override
    public void dibujarFigura() {
        System.out.println("Dibujando un Rectángulo de color " + getColor());
    }
}
