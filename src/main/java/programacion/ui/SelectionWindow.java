package programacion.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.*;
import programacion.repositorio.interfaces.AuthorRepository;
import programacion.repositorio.interfaces.BookRepository;
import programacion.ui.author.AuthorList;
import programacion.ui.book.BookList;

public class SelectionWindow {
    private Shell shell;
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    public SelectionWindow(Display display, BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        shell = new Shell(display);
        shell.setText("Seleccionar CRUD");
        shell.setSize(600, 400);
        createUI();
    }

    private void createUI() {
        Button bookButton = new Button(shell, SWT.PUSH);
        bookButton.setText("CRUD Libros");
        bookButton.setBounds(50, 50, 200, 30);
        bookButton.addListener(SWT.Selection, event -> openBookCRUD());

        Button authorButton = new Button(shell, SWT.PUSH);
        authorButton.setText("CRUD Autores");
        authorButton.setBounds(50, 100, 200, 30);
        authorButton.addListener(SWT.Selection, event -> openAuthorCRUD());
    }

    private void openBookCRUD() {
        BookList bookList = new BookList(shell.getDisplay(), bookRepository);
        bookList.open();
    }

    private void openAuthorCRUD() {
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
