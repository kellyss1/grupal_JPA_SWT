package programacion.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

import programacion.repositorio.interfaces.AuthorRepository;
import programacion.repositorio.interfaces.BookRepository;
import programacion.ui.author.AuthorList;
import programacion.ui.book.BookList;

public class SelectionWindow {
    private Shell shell;
    private Display display;
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private Image backgroundImage;

    public SelectionWindow(Display display, BookRepository bookRepository, AuthorRepository authorRepository) {
        this.display = display;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;

        shell = new Shell(display);
        shell.setText("Seleccionar CRUD");
        shell.setSize(600, 400);

        Color customColor = new Color(display, 161, 35, 25); // RGB: Azul Claro
        shell.setBackground(customColor);

        loadBackgroundImage();
        createUI();
    }

    private void loadBackgroundImage() {
        try {
            backgroundImage = new Image(display, "src/main/resources/Images/Biblio1.jpg"); // Ajusta la ruta
        } catch (Exception e) {
            System.err.println("Error cargando la imagen de fondo: " + e.getMessage());
        }
    }

    private void createUI() {
        // Layout principal de la Shell
        shell.setLayout(new GridLayout(1, false));

        // Canvas para la imagen de fondo
        Canvas canvas = new Canvas(shell, SWT.NONE);
        canvas.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        canvas.addPaintListener(e -> {
            if (backgroundImage != null) {
                e.gc.drawImage(backgroundImage, 0, 0,
                        backgroundImage.getBounds().width, backgroundImage.getBounds().height,
                        0, 0, shell.getClientArea().width, shell.getClientArea().height);
            }
        });

        // Composite para los botones (con transparencia)
        Composite buttonComposite = new Composite(shell, SWT.NONE);
        buttonComposite.setLayout(new GridLayout(1, false));
        buttonComposite.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false));
        buttonComposite.setBackgroundMode(SWT.INHERIT_FORCE);
        Color customColor = new Color(shell.getDisplay(), 161, 35, 25); // Azul personalizado
        buttonComposite.setBackground(customColor);

        // Definir el color de texto y el color de fondo
        Color buttonTextColor = new Color(shell.getDisplay(), 255, 255, 255); // Blanco para el texto
        Color buttonBackgroundColor = new Color(shell.getDisplay(), 0, 0, 0); // Negro para el fondo

        // Cambiar la fuente
        FontData fontData = new FontData("Arial", 10, SWT.BOLD); // Fuente Arial, tamaño 14, negrita
        Font buttonFont = new Font(shell.getDisplay(), fontData);


        // Botón CRUD Libros
        Button bookButton = new Button(buttonComposite, SWT.PUSH);
        bookButton.setText("Administrador de Libros");
        bookButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        bookButton.setBackground(buttonBackgroundColor);
        bookButton.setForeground(buttonTextColor);
        bookButton.setFont(buttonFont);
        bookButton.addListener(SWT.Selection, event -> openBookCRUD());

        // Botón CRUD Autores
        Button authorButton = new Button(buttonComposite, SWT.PUSH);
        authorButton.setText("Administrador de Autores");
        authorButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        authorButton.setBackground(buttonBackgroundColor);
        authorButton.setForeground(buttonTextColor);
        authorButton.setFont(buttonFont);
        authorButton.addListener(SWT.Selection, event -> openAuthorCRUD());

        shell.layout();
    }

    private void openBookCRUD() {
        shell.setVisible(false);
        BookList bookList = new BookList(display, bookRepository, this);
        bookList.open();
    }

    private void openAuthorCRUD() {
        shell.setVisible(false);
        AuthorList authorList = new AuthorList(display, authorRepository, this);
        authorList.open();
    }

    public void open() {
        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
    }

    public void dispose() {
        if (backgroundImage != null) {
            backgroundImage.dispose();
        }
        shell.dispose();
    }
}
