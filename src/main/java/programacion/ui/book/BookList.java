package programacion.ui.book;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.*;
import programacion.db.Book;
import programacion.repositorio.interfaces.BookRepository;

import java.util.List;

public class BookList {
    private Shell shell;
    private Table table;
    private Text searchText;
    private BookRepository bookRepository;

    public BookList(Display display, BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        shell = new Shell(display);
        shell.setText("Lista de Libros");
        shell.setSize(800, 700);
        createTable();
        createSearchBox();
        loadBooks();
    }

    private void createTable() {
        table = new Table(shell, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
        table.setHeaderVisible(true);
        table.setLinesVisible(true);
        table.setBounds(10, 10, 380, 200);

        String[] titles = { "ID", "Título", "ISBN", "Precio" };
        for (String title : titles) {
            TableColumn column = new TableColumn(table, SWT.NONE);
            column.setText(title);
            column.setWidth(100);
        }

        Button addButton = new Button(shell, SWT.PUSH);
        addButton.setText("Añadir");
        addButton.setBounds(10, 220, 80, 30);
        addButton.addListener(SWT.Selection, event -> openForm(null));

        Button editButton = new Button(shell, SWT.PUSH);
        editButton.setText("Editar");
        editButton.setBounds(100, 220, 80, 30);
        editButton.addListener(SWT.Selection, event -> {
            int selectedIndex = table.getSelectionIndex();
            if (selectedIndex != -1) {
                Book book = (Book) table.getItem(selectedIndex).getData();
                openForm(book);
            }
        });

        Button deleteButton = new Button(shell, SWT.PUSH);
        deleteButton.setText("Eliminar");
        deleteButton.setBounds(190, 220, 80, 30);
        deleteButton.addListener(SWT.Selection, event -> {
            int selectedIndex = table.getSelectionIndex();
            if (selectedIndex != -1) {
                Book book = (Book) table.getItem(selectedIndex).getData();
                bookRepository.delete(book.getId());
                loadBooks();
            }
        });
    }

    private void createSearchBox() {
        Label searchLabel = new Label(shell, SWT.NONE);
        searchLabel.setText("Buscar por Título:");
        searchLabel.setBounds(10, 260, 100, 25);

        searchText = new Text(shell, SWT.BORDER);
        searchText.setBounds(120, 260, 180, 25);

        Button searchButton = new Button(shell, SWT.PUSH);
        searchButton.setText("Buscar");
        searchButton.setBounds(310, 260, 80, 30);
        searchButton.addListener(SWT.Selection, event -> searchBook());
    }

    private void searchBook() {
        String title = searchText.getText();
        table.removeAll();
        Book book = bookRepository.findByTitulo(title);
        if (book != null) {
            TableItem item = new TableItem(table, SWT.NONE);
            item.setText(new String[] {
                    String.valueOf(book.getId()),
                    book.getTitulo(),
                    book.getIsbn(),
                    String.valueOf(book.getPrecio())
            });
            item.setData(book);
        }
    }

    private void loadBooks() {
        table.removeAll();
        List<Book> books = bookRepository.findAll();
        for (Book book : books) {
            TableItem item = new TableItem(table, SWT.NONE);
            item.setText(new String[] {
                    String.valueOf(book.getId()),
                    book.getTitulo(),
                    book.getIsbn(),
                    String.valueOf(book.getPrecio())
            });
            item.setData(book);
        }
    }

    private void openForm(Book book) {
        BookForm form = new BookForm(shell.getDisplay(), bookRepository, book);
        form.open();
        loadBooks();
    }

    public void open() {
        shell.open();
    }
}