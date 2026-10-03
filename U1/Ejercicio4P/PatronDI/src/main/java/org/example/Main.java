package org.example;

import org.example.maquinaria.Locomotora;
import org.example.maquinaria.Tren;
import org.example.personal.Maquinista;
import org.example.personal.Mecanico;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Mecanico mecanico = new Mecanico("Manolito Palotes", "111111111", "Frenos");
        Locomotora locomotora = new Locomotora("Locomotora-1", 3000, 2015, mecanico);
        Tren tren = new Tren(locomotora);
        Maquinista maquinista = new Maquinista("Lolito", "12345678A", 2100, "Senior");
        tren.inyectarMaquinista(maquinista);

        System.out.println(tren.anadirVagon(1000, 200, "carbón"));
        System.out.println(tren.anadirVagon(500, 0, "grano"));
        System.out.println(tren.anadirVagon(750, 200, "hierro"));
        System.out.println(tren.anadirVagon(600, 300, "trigo"));
        System.out.println(tren.anadirVagon(150, 50, "jugetes"));
        System.out.println(tren.anadirVagon(900,800,"ovejas"));
    }
}