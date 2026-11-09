package org.example;

import org.example.patronDI.Casa;
import org.example.patronDI.Pared;
import org.example.patronDI.Tejado;
import org.example.patronDI.TejadoTejas;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Pared[] paredesCasa1 = new Pared[4];
        for (int i = 0; i < paredesCasa1.length; i++) {
            paredesCasa1[i] = new Pared();
        }
        Tejado tejadoCasa1 = new TejadoTejas();
        Casa casa1 = new Casa(paredesCasa1, tejadoCasa1, 250.0);

        Pared[] paredesCasa2 = new Pared[4];
        for (int i = 0; i < paredesCasa2.length; i++) {
            paredesCasa2[i] = new Pared();
        }
        Tejado tejadoCasa2 = new TejadoTejas();
        Casa casa2 = new Casa(paredesCasa2, tejadoCasa2, 150.0);

        casa1.mostrarInfo();
        casa2.mostrarInfo();

    }
}