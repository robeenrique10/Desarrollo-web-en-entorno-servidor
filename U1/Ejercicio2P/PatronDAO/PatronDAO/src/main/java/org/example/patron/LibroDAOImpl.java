package org.example.patron;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LibroDAOImpl implements LibroDAO{

    private final Map<Integer, Libro> libros = new LinkedHashMap<>();

    @Override
    public List<Libro> obtenerTodos() {
        return List.copyOf(libros.values());
    }

    @Override
    public Libro obtenerPorId(int id) {
        Libro libro = libros.get(id);
        if (libro == null) {
            throw new LibroNoEncontradoException(id);
        }
        return libro;
    }

    @Override
    public void agregar(Libro libro) {
        if (libros.containsKey(libro.getId())) {
            throw new IllegalArgumentException("El libro con ID " + libro.getId() + "ya existe.");
        }
        libros.put(libro.getId(), libro);
    }

    @Override
    public void actualizar(Libro libro) {
        obtenerPorId(libro.getId());
        libros.put(libro.getId(), libro);
    }

    @Override
    public void eliminar(int id) {
        obtenerPorId(id);
        libros.remove(id);
    }
}
