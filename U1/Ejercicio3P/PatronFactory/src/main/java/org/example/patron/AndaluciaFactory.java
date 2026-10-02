package org.example.patron;

import java.util.Locale;

public class AndaluciaFactory extends ElementoAndaluzFactory {

    @Override
    public ElementoAndaluz createElementoAndaluz(String nombre) {

        switch (nombre.toLowerCase()) {
            case "flamenco":
                return new Flamenco();
            case "gazpacho":
                return new Gazpacho();
            case "feria de abril":
                return new FeriaDeAbril();
            default:
                System.out.printf("No se encontró ningún elemento");
                return null;
        }

    }
}
