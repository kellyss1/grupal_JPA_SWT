package programacion.ui.book;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import programacion.repositorio.interfaces.BookRepository;

public class MainWindow {
    private Shell shell;
    private BookRepository bookRepository;

    public MainWindow(Display display, BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        shell = new Shell(display);
        shell.setText("Aplicación CRUD con SWT");
        shell.setSize(400, 300);
        createUI();
    }

    private void createUI() {
        BookList bookList = new BookList(shell.getDisplay(), bookRepository);
        bookList.open();
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
