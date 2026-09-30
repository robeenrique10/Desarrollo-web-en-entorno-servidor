package org.example.patron;

public class LibroNoEncontradoException extends RuntimeException{

    public LibroNoEncontradoException (int id) {
        super("No existe un libro con ID: " + id);
    }
}
