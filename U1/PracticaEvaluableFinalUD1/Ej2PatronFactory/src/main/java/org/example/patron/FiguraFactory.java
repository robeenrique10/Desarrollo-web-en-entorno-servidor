package org.example.patron;

import java.util.Locale;

public class FiguraFactory {

    public static Figura crearFigura(String tipo, String color) {
        switch (tipo.toLowerCase()) {
            case "triangulo":
                return new Triangulo(color);
            case "rectangulo":
                return new Rectangulo(color);
            case "circulo":
                return new Círculo(color);
            case "cuadrado":
                return new Cuadrado(color);
            default:
                throw new IllegalArgumentException("Tipo de figura no adminitida: " +tipo);
        }
    }

}
