package org.example;

import org.example.patron.Figura;
import org.example.patron.FiguraFactory;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Figura triangulo = FiguraFactory.crearFigura("triangulo", "rojo");
        Figura rectangulo = FiguraFactory.crearFigura("rectangulo", "azul");
        Figura circulo = FiguraFactory.crearFigura("circulo", "verde");
        Figura cuadrado = FiguraFactory.crearFigura("cuadrado", "amarillo");

        triangulo.dibujarFigura();
        rectangulo.dibujarFigura();
        circulo.dibujarFigura();
        cuadrado.dibujarFigura();
    }
}