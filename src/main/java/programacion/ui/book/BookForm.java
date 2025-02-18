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

public class BookForm {
    private Shell shell;
    private Text titleText;
    private Text isbnText;
    private Text priceText;
    private Text authorIdText;
    private BookRepository bookRepository;
    private Book book;
    private BookList booklist;

    public BookForm(Display display, BookRepository bookRepository, Book book, BookList booklist) {
        this.bookRepository = bookRepository;
        this.book = book;
        this.booklist = booklist;
        shell = new Shell(display);
        shell.setText("Formulario de Libro");
        shell.setSize(400, 300);
        shell.setLayout(new GridLayout(2, false)); // Usa GridLayout para organizar los elementos

        Color customColor = new Color(display, 161, 35, 25); // RGB: Azul Claro
        shell.setBackground(customColor);
        createForm();
    }

    private void createForm() {
        Label titleLabel = new Label(shell, SWT.NONE);
        titleLabel.setText("Título:");
        titleLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false));

        titleText = new Text(shell, SWT.BORDER);
        titleText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

        Label isbnLabel = new Label(shell, SWT.NONE);
        isbnLabel.setText("ISBN:");
        isbnLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false));

        isbnText = new Text(shell, SWT.BORDER);
        isbnText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

        Label priceLabel = new Label(shell, SWT.NONE);
        priceLabel.setText("Precio:");
        priceLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false));

        priceText = new Text(shell, SWT.BORDER);
        priceText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

        Label authorIdLabel = new Label(shell, SWT.NONE);
        authorIdLabel.setText("ID del Autor:");
        authorIdLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false));

        authorIdText = new Text(shell, SWT.BORDER);
        authorIdText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

        // Sección de botones en un Composite con GridLayout
        Composite buttonComposite = new Composite(shell, SWT.NONE);
        buttonComposite.setLayout(new GridLayout(2, true));
        buttonComposite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
        Color customColor = new Color(shell.getDisplay(), 161, 35, 25); // Azul personalizado
        buttonComposite.setBackground(customColor);

        // Definir el color de texto y el color de fondo
        Color buttonTextColor = new Color(shell.getDisplay(), 255, 255, 255); // Blanco para el texto
        Color buttonBackgroundColor = new Color(shell.getDisplay(), 0, 0, 0); // Negro para el fondo

        // Cambiar la fuente
        FontData fontData = new FontData("Arial", 10, SWT.BOLD); // Fuente Arial, tamaño 14, negrita
        Font buttonFont = new Font(shell.getDisplay(), fontData);

        Button saveButton = new Button(buttonComposite, SWT.PUSH);
        saveButton.setText("Guardar");
        saveButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        saveButton.setBackground(buttonBackgroundColor); // Establecer el color de fondo
        saveButton.setForeground(buttonTextColor); // Establecer el color del texto
        saveButton.setFont(buttonFont); // Establecer la fuente
        saveButton.addListener(SWT.Selection, event -> saveBook());

        Button backButton = new Button(buttonComposite, SWT.PUSH);
        backButton.setText("Volver");
        backButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        backButton.setBackground(buttonBackgroundColor); // Establecer el color de fondo
        backButton.setForeground(buttonTextColor); // Establecer el color del texto
        backButton.setFont(buttonFont); // Establecer la fuente
        backButton.addListener(SWT.Selection, event -> {
            shell.dispose();
            booklist.open();
        });

        if (book != null) {
            titleText.setText(book.getTitulo());
            isbnText.setText(book.getIsbn());
            priceText.setText(String.valueOf(book.getPrecio()));
            authorIdText.setText(String.valueOf(book.getAuthor().getId()));
        }
    }

    private void saveBook() {
        String title = titleText.getText();
        String isbn = isbnText.getText();
        double price = Double.parseDouble(priceText.getText());
        Long authorId = Long.parseLong(authorIdText.getText());

        if (book == null) {
            book = new Book();
        }
        book.setTitulo(title);
        book.setIsbn(isbn);
        book.setPrecio(price);

        bookRepository.save(book, authorId);

        shell.close();
    }

    public void open() {
        shell.open();
    }
}
