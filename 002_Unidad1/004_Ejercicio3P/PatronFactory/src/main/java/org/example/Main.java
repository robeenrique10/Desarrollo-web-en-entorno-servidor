package org.example;

import org.example.patron.AndaluciaFactory;
import org.example.patron.ElementoAndaluz;
import org.example.patron.ElementoAndaluzFactory;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ElementoAndaluzFactory andalusia = new AndaluciaFactory();

        ElementoAndaluz flamenkito = andalusia.createElementoAndaluz("flamenco");
        ElementoAndaluz gazpachito = andalusia.createElementoAndaluz("gazpacho");
        ElementoAndaluz laFeriaDeZevilla = andalusia.createElementoAndaluz("Feria de Abril");

        flamenkito.describir();
        gazpachito.describir();
        laFeriaDeZevilla.describir();

    }
}