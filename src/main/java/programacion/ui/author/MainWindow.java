package programacion.ui.author;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import programacion.repositorio.interfaces.AuthorRepository;
import programacion.repositorio.interfaces.BookRepository;
import programacion.ui.book.BookList;

public class MainWindow {
    private Shell shell;
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    public MainWindow(Display display, BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        shell = new Shell(display);
        shell.setText("Aplicación CRUD con SWT");
        shell.setSize(400, 300);
        createUI();
    }

    private void createUI() {
        BookList bookList = new BookList(shell.getDisplay(), bookRepository);
        bookList.open();

        AuthorList authorList = new AuthorList(shell.getDisplay(), authorRepository);
        authorList.open();
    }

    public void open() {
        shell.open();
        while (!shell.isDisposed()) {
            if (!shell.getDisplay().readAndDispatch()) {
                shell.getDisplay().sleep();
            }
        }
    }
}
