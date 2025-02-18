package programacion.repositorio.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.apache.deltaspike.jpa.api.transaction.Transactional;
import programacion.db.Author;
import programacion.db.Book;
import programacion.repositorio.interfaces.BookRepository;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Transactional
public class BookRepositoryImpl implements BookRepository {

    @Inject
    private EntityManager em;


    @Override
    @Transactional
    public void save(Book book, Long authorId) {
        try {
            em.getTransaction().begin();
            Author author = em.find(Author.class, authorId);
            if (author == null) {
                throw new IllegalArgumentException("El autor no existe.");
            }
            book.setAuthor(author);
            author.getBooks().add(book);
            System.out.println(book);
            em.persist(book);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Book> findAll() {
        String query = "SELECT b FROM Book b";  // JPQL para obtener todos los libros
        return em.createQuery(query, Book.class).getResultList();
    }

    @Override
    public Book findByTitulo(String titulo) {
        String query = "SELECT b FROM Book b WHERE b.titulo = :titulo";
        return em.createQuery(query, Book.class)
                .setParameter("titulo", titulo)
                .getSingleResult();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        em.getTransaction().begin();
        Book book = em.find(Book.class, id);  // Buscar el libro por ID
        if (book != null) {
            em.remove(book);  // Eliminar el libro de la base de datos
        }
        em.getTransaction().commit();
    }

}
