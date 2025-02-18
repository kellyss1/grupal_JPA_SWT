package programacion.ui.author;

import jakarta.transaction.Transactional;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.*;
import programacion.db.Author;
import programacion.repositorio.interfaces.AuthorRepository;

public class AuthorForm {
    private Shell shell;
    private Text nameText;
    private Text surnameText;
    private AuthorRepository authorRepository;
    private Author author;

    public AuthorForm(Display display, AuthorRepository authorRepository, Author author) {
        this.authorRepository = authorRepository;
        this.author = author;
        shell = new Shell(display);
        shell.setText("Formulario de Autor");
        shell.setSize(300, 200);
        createForm();
    }

    private void createForm() {
        Label nameLabel = new Label(shell, SWT.NONE);
        nameLabel.setText("Nombre:");
        nameLabel.setBounds(10, 10, 80, 25);
        nameText = new Text(shell, SWT.BORDER);
        nameText.setBounds(100, 10, 180, 25);

        Label surnameLabel = new Label(shell, SWT.NONE);
        surnameLabel.setText("Apellido:");
        surnameLabel.setBounds(10, 40, 80, 25);
        surnameText = new Text(shell, SWT.BORDER);
        surnameText.setBounds(100, 40, 180, 25);

        Button saveButton = new Button(shell, SWT.PUSH);
        saveButton.setText("Guardar");
        saveButton.setBounds(100, 80, 80, 30);
        saveButton.addListener(SWT.Selection, event -> saveAuthor());

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
    }
}