package programacion.repositorio.interfaces;

import programacion.db.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    // Crear un nuevo libro
    void save(Book book, Long authorId);

    // Buscar todos los libros
    List<Book> findAll();

    // Buscar un libro por su ID
    Book findByTitulo(String titulo);

    // Eliminar un libro por su ID
    void delete(Long id);

    //Encontrar el libro con el precio mas alto
    Book findMaxPriceBook();
}

