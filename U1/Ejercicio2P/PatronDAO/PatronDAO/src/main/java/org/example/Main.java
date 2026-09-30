package org.example;

import org.example.patron.Libro;
import org.example.patron.LibroDAO;
import org.example.patron.LibroDAOImpl;
import org.example.patron.LibroNoEncontradoException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        LibroDAO libros = new LibroDAOImpl();

        libros.agregar(new Libro(1, "Cien años de soledad", "Gabriel García Márquez", 1967));
        libros.agregar(new Libro(2, "Pedro Páramo", "Juan Rulfo", 1955));

        System.out.println("Libros almacenados en la biblioteca: ");
        for (Libro libro : libros.obtenerTodos()) {
            System.out.println(libro);
        }

        try {
            libros.obtenerPorId(5);
        } catch (LibroNoEncontradoException exception) {
            System.out.println("Búsqueda: " + exception.getMessage());
        }

    }
}