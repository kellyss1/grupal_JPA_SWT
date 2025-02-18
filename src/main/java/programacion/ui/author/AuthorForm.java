package programacion.ui.author;

import jakarta.transaction.Transactional;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;
import programacion.db.Author;
import programacion.repositorio.interfaces.AuthorRepository;

public class AuthorForm {
    private Shell shell;
    private Text nameText;
    private Text surnameText;
    private AuthorRepository authorRepository;
    private Author author;
    private AuthorList authorList;

    public AuthorForm(Display display, AuthorRepository authorRepository, Author author, AuthorList authorList) {
        this.authorRepository = authorRepository;
        this.author = author;
        this.authorList = authorList;

        shell = new Shell(display);
        shell.setText("Formulario de Autor");
        shell.setSize(300, 200);

        Color customColor = new Color(display, 161, 35, 25); // RGB: Azul Claro
        shell.setBackground(customColor);

        // Establecer un layout para que los elementos se acomoden correctamente
        shell.setLayout(new GridLayout(2, false));

        createForm();
    }

    private void createForm() {
        // Etiqueta y campo de texto para el nombre
        Label nameLabel = new Label(shell, SWT.NONE);
        nameLabel.setText("Nombre:");
        nameLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false));

        nameText = new Text(shell, SWT.BORDER);
        nameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

        // Etiqueta y campo de texto para el apellido
        Label surnameLabel = new Label(shell, SWT.NONE);
        surnameLabel.setText("Apellido:");
        surnameLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false));

        surnameText = new Text(shell, SWT.BORDER);
        surnameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

        // Contenedor para los botones
        Composite buttonComposite = new Composite(shell, SWT.NONE);
        buttonComposite.setLayout(new GridLayout(2, true));
        GridData buttonGridData = new GridData(SWT.CENTER, SWT.CENTER, true, false, 2, 1);
        buttonComposite.setLayoutData(buttonGridData);
        Color customColor = new Color(shell.getDisplay(), 161, 35, 25); // Azul personalizado
        buttonComposite.setBackground(customColor);


        // Definir el color de texto y el color de fondo
        Color buttonTextColor = new Color(shell.getDisplay(), 255, 255, 255); // Blanco para el texto
        Color buttonBackgroundColor = new Color(shell.getDisplay(), 0, 0, 0); // Negro para el fondo

        // Cambiar la fuente
        FontData fontData = new FontData("Arial", 10, SWT.BOLD); // Fuente Arial, tamaño 14, negrita
        Font buttonFont = new Font(shell.getDisplay(), fontData);

        // Botón de guardar
        Button saveButton = new Button(buttonComposite, SWT.PUSH);
        saveButton.setText("Guardar");
        saveButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        saveButton.setBackground(buttonBackgroundColor); // Establecer el color de fondo
        saveButton.setForeground(buttonTextColor); // Establecer el color del texto
        saveButton.setFont(buttonFont); // Establecer la fuente
        saveButton.addListener(SWT.Selection, event -> saveAuthor());

        // Botón de volver
        Button backButton = new Button(buttonComposite, SWT.PUSH);
        backButton.setText("Volver");
        backButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        backButton.setBackground(buttonBackgroundColor); // Establecer el color de fondo
        backButton.setForeground(buttonTextColor); // Establecer el color del texto
        backButton.setFont(buttonFont); // Establecer la fuente
        backButton.addListener(SWT.Selection, event -> {
            shell.dispose();
            authorList.open();
        });

        // Si estamos editando un autor, cargamos los datos en los campos de texto
        if (author != null) {
            nameText.setText(author.getNombre());
            surnameText.setText(author.getApellido());
        }
    }

    @Transactional
    private void saveAuthor() {
        String name = nameText.getText();
        String surname = surnameText.getText();

        if (author == null) {
            author = new Author();
        }
        author.setNombre(name);
        author.setApellido(surname);

        authorRepository.save(author);
        shell.close();
    }

    public void open() {
        shell.open();
        Display display = shell.getDisplay();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
    }
}
