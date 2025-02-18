package programacion.ui.book;

import org.eclipse.swt.SWT;
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

    public BookForm(Display display, BookRepository bookRepository, Book book) {
        this.bookRepository = bookRepository;
        this.book = book;
        shell = new Shell(display);
        shell.setText("Formulario de Libro");
        shell.setSize(300, 250);
        createForm();
    }

    private void createForm() {
        Label titleLabel = new Label(shell, SWT.NONE);
        titleLabel.setText("Título:");
        titleLabel.setBounds(10, 10, 80, 25);
        titleText = new Text(shell, SWT.BORDER);
        titleText.setBounds(100, 10, 180, 25);

        Label isbnLabel = new Label(shell, SWT.NONE);
        isbnLabel.setText("ISBN:");
        isbnLabel.setBounds(10, 40, 80, 25);
        isbnText = new Text(shell, SWT.BORDER);
        isbnText.setBounds(100, 40, 180, 25);

        Label priceLabel = new Label(shell, SWT.NONE);
        priceLabel.setText("Precio:");
        priceLabel.setBounds(10, 70, 80, 25);
        priceText = new Text(shell, SWT.BORDER);
        priceText.setBounds(100, 70, 180, 25);

        Label authorIdLabel = new Label(shell, SWT.NONE);
        authorIdLabel.setText("ID del Autor:");
        authorIdLabel.setBounds(10, 100, 80, 25);
        authorIdText = new Text(shell, SWT.BORDER);
        authorIdText.setBounds(100, 100, 180, 25);

        Button saveButton = new Button(shell, SWT.PUSH);
        saveButton.setText("Guardar");
        saveButton.setBounds(100, 140, 80, 30);
        saveButton.addListener(SWT.Selection, event -> saveBook());

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