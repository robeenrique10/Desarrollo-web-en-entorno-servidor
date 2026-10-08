package org.example.patron;

public class Círculo extends Figura {

    public Círculo(String color) {
        super(color);
    }

    @Override
    public void dibujarFigura() {
        System.out.println("Dibujando un círculo de color " + getColor());
    }
}
