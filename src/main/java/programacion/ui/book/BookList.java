package programacion.ui.book;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;
import programacion.db.Book;
import programacion.repositorio.interfaces.BookRepository;
import programacion.ui.SelectionWindow;

import java.util.List;

public class BookList {
    private Shell shell;
    private Table table;
    private Text searchText;
    private BookRepository bookRepository;
    private SelectionWindow selectionWindow;

    public BookList(Display display, BookRepository bookRepository, SelectionWindow selectionWindow) {
        this.bookRepository = bookRepository;
        this.selectionWindow = selectionWindow;
        shell = new Shell(display);
        shell.setText("Lista de Libros");
        shell.setSize(800, 700);
        shell.setLayout(new GridLayout(1, false));

        Color customColor = new Color(display, 161, 35, 25); // RGB: Azul Claro
        shell.setBackground(customColor);

        // Configurar fuente UTF-8 compatible
        FontData fontData = new FontData("Arial", 14, SWT.NORMAL); // Define the font
        Font font = new Font(shell.getDisplay(), fontData); // Apply the font
        shell.setFont(font);

        createTable();
        createButtons();
        createSearchBox();
        loadBooks();
    }

    private void createTable() {
        table = new Table(shell, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
        table.setHeaderVisible(true);
        table.setLinesVisible(true);
        GridData tableGridData = new GridData(SWT.FILL, SWT.FILL, true, true);
        tableGridData.heightHint = 300;
        table.setLayoutData(tableGridData);

        String[] titles = { "ID", "Titulo", "ISBN", "Precio" };
        for (String title : titles) {
            TableColumn column = new TableColumn(table, SWT.NONE);
            column.setText(title);
            column.setWidth(200);
        }
    }

    private void createButtons() {
        Composite buttonComposite = new Composite(shell, SWT.NONE);
        buttonComposite.setLayout(new GridLayout(4, true));
        buttonComposite.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false));
        Color customColor = new Color(shell.getDisplay(),161 , 35, 25); // Azul personalizado
        buttonComposite.setBackground(customColor);

        // Definir el color de texto y el color de fondo
        Color buttonTextColor = new Color(shell.getDisplay(), 255, 255, 255); // Blanco para el texto
        Color buttonBackgroundColor = new Color(shell.getDisplay(), 0, 0, 0); // Negro para el fondo

        // Cambiar la fuente
        FontData fontData = new FontData("Arial", 10, SWT.BOLD); // Fuente Arial, tamaño 14, negrita
        Font buttonFont = new Font(shell.getDisplay(), fontData);

        // Botón "Añadir"
        Button addButton = new Button(buttonComposite, SWT.PUSH);
        addButton.setText("Añadir");
        addButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        addButton.setBackground(buttonBackgroundColor); // Establecer el color de fondo
        addButton.setForeground(buttonTextColor); // Establecer el color del texto
        addButton.setFont(buttonFont); // Establecer la fuente
        addButton.addListener(SWT.Selection, event -> openForm(null));

        // Botón "Editar"
        Button editButton = new Button(buttonComposite, SWT.PUSH);
        editButton.setText("Editar");
        editButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        editButton.setBackground(buttonBackgroundColor);
        editButton.setForeground(buttonTextColor);
        editButton.setFont(buttonFont);
        editButton.addListener(SWT.Selection, event -> {
            int selectedIndex = table.getSelectionIndex();
            if (selectedIndex != -1) {
                Book book = (Book) table.getItem(selectedIndex).getData();
                openForm(book);
            }
        });

        // Botón "Eliminar"
        Button deleteButton = new Button(buttonComposite, SWT.PUSH);
        deleteButton.setText("Eliminar");
        deleteButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        deleteButton.setBackground(buttonBackgroundColor);
        deleteButton.setForeground(buttonTextColor);
        deleteButton.setFont(buttonFont);
        deleteButton.addListener(SWT.Selection, event -> {
            int selectedIndex = table.getSelectionIndex();
            if (selectedIndex != -1) {
                Book book = (Book) table.getItem(selectedIndex).getData();
                bookRepository.delete(book.getId());
                loadBooks();  // Recargar los libros después de eliminar
            }
        });

        // Botón "Precio Máximo"
        Button maxPriceButton = new Button(buttonComposite, SWT.PUSH);
        maxPriceButton.setText("Precio Máximo");
        maxPriceButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        maxPriceButton.setBackground(buttonBackgroundColor);
        maxPriceButton.setForeground(buttonTextColor);
        maxPriceButton.setFont(buttonFont);
        maxPriceButton.addListener(SWT.Selection, event -> findMaxPriceBook());

        // Botón "Volver"
        Button backButton = new Button(buttonComposite, SWT.PUSH);
        backButton.setText("Volver");
        backButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        backButton.setBackground(buttonBackgroundColor);
        backButton.setForeground(buttonTextColor);
        backButton.setFont(buttonFont);
        backButton.addListener(SWT.Selection, event -> {
            shell.dispose();  // Cierra la ventana actual
            selectionWindow.open();  // Abre la ventana SelectionWindow
        });
    }

    private void findMaxPriceBook() {
        Book maxPriceBook = bookRepository.findMaxPriceBook();
        if (maxPriceBook != null) {
            table.removeAll();
            TableItem item = new TableItem(table, SWT.NONE);
            item.setText(new String[] {
                    String.valueOf(maxPriceBook.getId()),
                    maxPriceBook.getTitulo(),
                    maxPriceBook.getIsbn(),
                    String.valueOf(maxPriceBook.getPrecio())
            });
            item.setData(maxPriceBook);
        }
    }

    private void createSearchBox() {
        Composite searchComposite = new Composite(shell, SWT.NONE);
        searchComposite.setLayout(new GridLayout(3, false));
        searchComposite.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false));

        Label searchLabel = new Label(searchComposite, SWT.NONE);
        searchLabel.setText("Buscar por Título:");

        searchText = new Text(searchComposite, SWT.BORDER);
        GridData textGridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
        textGridData.widthHint = 240; // Define un ancho mínimo más grande
        searchText.setLayoutData(textGridData);

        Button searchButton = new Button(searchComposite, SWT.PUSH);
        searchButton.setText("Buscar");
        searchButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
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
        BookForm form = new BookForm(shell.getDisplay(), bookRepository, book, this);
        form.open();
        loadBooks();
    }

    public void open() {
        shell.open();
    }
}
