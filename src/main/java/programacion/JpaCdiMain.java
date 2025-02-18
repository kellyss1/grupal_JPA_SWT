package programacion;

import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import org.eclipse.swt.widgets.Display;
import programacion.repositorio.interfaces.AuthorRepository;
import programacion.repositorio.interfaces.BookRepository;
import programacion.ui.SelectionWindow;

public class JpaCdiMain {
    public static void main(String[] args) {

//        SeContainer container = SeContainerInitializer.newInstance().initialize();
//        var repoBooks = container.select(BookRepository.class).get();
//        var repoAuthors = container.select(AuthorRepository.class).get();

//        Book book = new Book();
//        book.setTitulo("El Quijote");
//        book.setIsbn("1234567890");
//        book.setPrecio(10.0);
//        repoBooks.save(book, 11L);

//        repoBooks.deleteById(51L);
        //System.out.println(repoBooks.findByTitulo("Greedy Algorithms"));

        //'''''''''''''''''''

//        Author autor = new Author();
//        autor.setNombre("Juan");
//        autor.setApellido("Perez");
//
//        repoAuthors.save(autor);
//        System.out.println(repoAuthors.findByNombre("Juan"));
//
//        repoAuthors.attachAndRemove(repoAuthors.findById(12L));


       // System.setProperty("file.encoding", "UTF-8");
        SeContainer container = SeContainerInitializer.newInstance().initialize();
        BookRepository bookRepository = container.select(BookRepository.class).get();
        AuthorRepository authorRepository = container.select(AuthorRepository.class).get();

        Display display = new Display();
        SelectionWindow selectionWindow = new SelectionWindow(display, bookRepository, authorRepository);
        selectionWindow.open();
        display.dispose();
    }
}
