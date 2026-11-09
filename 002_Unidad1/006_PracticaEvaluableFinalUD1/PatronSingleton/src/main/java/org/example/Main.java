package org.example;

import org.example.patron.Presidente;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Presidente presidente1 = Presidente.obtenerInstancia();
        Presidente presidente2 = Presidente.obtenerInstancia();

        presidente1.setNombre("Abraham");
        presidente1.setApellido("Lincoln");
        presidente1.setAnioEleccion(1860);

        System.out.println("Datos vistos desde presidente2:");
        System.out.println("Nombre: " + presidente2.getNombre());
        System.out.println("Apellido: " + presidente2.getApellido());
        System.out.println("Año de elección: " + presidente2.getAnioEleccion());

        System.out.println();
        System.out.println("¿Son el mismo objeto? " + (presidente1 == presidente2));
        System.out.println("hashCode presidente1: " + presidente1.hashCode());
        System.out.println("hashCode presidente2: " + presidente2.hashCode());
    }
}