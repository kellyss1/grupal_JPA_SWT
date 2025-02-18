package programacion.ui.author;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import programacion.repositorio.interfaces.AuthorRepository;
import programacion.repositorio.interfaces.BookRepository;
import programacion.ui.SelectionWindow;
import programacion.ui.book.BookList;

public class MainWindow {
    private Shell shell;
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private SelectionWindow selectionWindow;

    public MainWindow(Display display, BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        shell = new Shell(display);
        shell.setText("Aplicación CRUD con SWT");
        shell.setSize(400, 300);
        createUI();
    }

    private void createUI() {
        BookList bookList = new BookList(shell.getDisplay(), bookRepository,selectionWindow);
        bookList.open();

        AuthorList authorList = new AuthorList(shell.getDisplay(), authorRepository,selectionWindow);
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
