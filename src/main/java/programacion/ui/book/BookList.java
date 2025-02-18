package programacion.ui.book;

import org.eclipse.swt.SWT;
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

            // Configurar fuente UTF-8 compatible
            FontData fontData = new FontData("Arial", 14, SWT.NORMAL);
            Font font = new Font(shell.getDisplay(), fontData);
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

            Button addButton = new Button(buttonComposite, SWT.PUSH);
            addButton.setText("Añadir");
            addButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
            addButton.addListener(SWT.Selection, event -> openForm(null));

            Button editButton = new Button(buttonComposite, SWT.PUSH);
            editButton.setText("Editar");
            editButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
            editButton.addListener(SWT.Selection, event -> {
                int selectedIndex = table.getSelectionIndex();
                if (selectedIndex != -1) {
                    Book book = (Book) table.getItem(selectedIndex).getData();
                    openForm(book);
                }
            });

            Button deleteButton = new Button(buttonComposite, SWT.PUSH);
            deleteButton.setText("Eliminar");
            deleteButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
            deleteButton.addListener(SWT.Selection, event -> {
                int selectedIndex = table.getSelectionIndex();
                if (selectedIndex != -1) {
                    Book book = (Book) table.getItem(selectedIndex).getData();
                    bookRepository.delete(book.getId());
                    loadBooks();
                }
            });

            Button maxPriceButton = new Button(buttonComposite, SWT.PUSH);
            maxPriceButton.setText("Precio Máximo");
            maxPriceButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
            maxPriceButton.addListener(SWT.Selection, event -> findMaxPriceBook());


            Button backButton = new Button(buttonComposite, SWT.PUSH);
            backButton.setText("Volver");
            backButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
            backButton.addListener(SWT.Selection, event -> {
                shell.dispose(); // Cierra la ventana actual
                selectionWindow.open(); // Abre la ventana SelectionWindow usando la instancia correcta
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
            searchText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

            Button searchButton = new Button(searchComposite, SWT.PUSH);
            searchButton.setText("Buscar");
            searchButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
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
            BookForm form = new BookForm(shell.getDisplay(), bookRepository, book,this);
            form.open();
            loadBooks();
        }

        public void open() {
            shell.open();
        }
    }

