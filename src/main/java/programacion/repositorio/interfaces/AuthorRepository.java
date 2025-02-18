package programacion.repositorio.interfaces;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;
import programacion.db.Author;

import java.util.List;

@Repository
public interface AuthorRepository extends EntityRepository<Author, Long> {

    //Author save(Author author);
    List<Author> findAll();
    List<Author> findByNombre(String nombre);
    //void attachAndRemove(Author author);
}


