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

        System.out.println("Libro co ID 1 ...");
        System.out.println(libros.obtenerPorId(1));

        System.out.println("Actualizando libro con ID 1 ...");
        libros.actualizar(new Libro(1, "Cien años de soledad", "Gabriel García Márquez", 1969));
        System.out.println("Libro actualizado con éxito");

        System.out.println("Todos los libros después de actualizar:");
        for (Libro libro : libros.obtenerTodos()) {
            System.out.println(libro);
        }

        System.out.println("Eliminando libro con ID 2 ...");
        libros.eliminar(2);
        System.out.println("Libro eliminado con éxito.");

        System.out.println("Todos los libros después de eliminar:");
        for (Libro libro : libros.obtenerTodos()) {
            System.out.println(libro);
        }

    }
}