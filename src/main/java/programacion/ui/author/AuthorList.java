package programacion.ui.author;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;
import programacion.db.Author;
import programacion.repositorio.interfaces.AuthorRepository;
import programacion.ui.SelectionWindow;

import java.util.List;

public class AuthorList {
    private Shell shell;
    private Table table;
    private Text searchText;
    private AuthorRepository authorRepository;
    private SelectionWindow selectionWindow;

    public AuthorList(Display display, AuthorRepository authorRepository, SelectionWindow selectionWindow) {
        this.authorRepository = authorRepository;
        this.selectionWindow = selectionWindow;
        shell = new Shell(display);
        shell.setText("Lista de Autores");
        shell.setSize(450, 400);
        shell.setLayout(new GridLayout(1, false));

        Color customColor = new Color(display, 161, 35, 25); // RGB: Azul Claro
        shell.setBackground(customColor);

        createTable();
        createSearchBox();
        createButtons();
        loadAuthors();
    }

    private void createTable() {
        table = new Table(shell, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
        table.setHeaderVisible(true);
        table.setLinesVisible(true);
        table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        String[] titles = { "ID", "Nombre", "Apellido" };
        for (String title : titles) {
            TableColumn column = new TableColumn(table, SWT.NONE);
            column.setText(title);
            column.setWidth(150);
        }
    }

    private void createSearchBox() {
        Composite searchComposite = new Composite(shell, SWT.NONE);
        searchComposite.setLayout(new GridLayout(3, false));
        searchComposite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

        Label searchLabel = new Label(searchComposite, SWT.NONE);
        searchLabel.setText("Buscar por Nombre:");

        searchText = new Text(searchComposite, SWT.BORDER);
        searchText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

        Button searchButton = new Button(searchComposite, SWT.PUSH);
        searchButton.setText("Buscar");
        searchButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
        searchButton.addListener(SWT.Selection, event -> searchAuthors());
    }

    private void createButtons() {
        Composite buttonComposite = new Composite(shell, SWT.NONE);
        buttonComposite.setLayout(new GridLayout(4, true));
        buttonComposite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        Color customColor = new Color(shell.getDisplay(), 161, 35, 25); // Azul personalizado
        buttonComposite.setBackground(customColor);

        // Definir el color de texto y el color de fondo
        Color buttonTextColor = new Color(shell.getDisplay(), 255, 255, 255); // Blanco para el texto
        Color buttonBackgroundColor = new Color(shell.getDisplay(), 0, 0, 0); // Negro para el fondo

        // Cambiar la fuente
        FontData fontData = new FontData("Arial", 10, SWT.BOLD); // Fuente Arial, tamaño 14, negrita
        Font buttonFont = new Font(shell.getDisplay(), fontData);

        Button addButton = new Button(buttonComposite, SWT.PUSH);
        addButton.setText("Añadir");
        addButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        addButton.setBackground(buttonBackgroundColor); // Establecer el color de fondo
        addButton.setForeground(buttonTextColor); // Establecer el color del texto
        addButton.setFont(buttonFont); // Establecer la fuente
        addButton.addListener(SWT.Selection, event -> openForm(null));

        Button editButton = new Button(buttonComposite, SWT.PUSH);
        editButton.setText("Editar");
        editButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        editButton.setBackground(buttonBackgroundColor);
        editButton.setForeground(buttonTextColor);
        editButton.setFont(buttonFont);
        editButton.addListener(SWT.Selection, event -> {
            int selectedIndex = table.getSelectionIndex();
            if (selectedIndex != -1) {
                Author author = (Author) table.getItem(selectedIndex).getData();
                openForm(author);
            }
        });

        Button deleteButton = new Button(buttonComposite, SWT.PUSH);
        deleteButton.setText("Eliminar");
        deleteButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        deleteButton.setBackground(buttonBackgroundColor);
        deleteButton.setForeground(buttonTextColor);
        deleteButton.setFont(buttonFont);
        deleteButton.addListener(SWT.Selection, event -> {
            int selectedIndex = table.getSelectionIndex();
            if (selectedIndex != -1) {
                Author author = (Author) table.getItem(selectedIndex).getData();
                authorRepository.attachAndRemove(author);
                loadAuthors();
            }
        });

        Button backButton = new Button(buttonComposite, SWT.PUSH);
        backButton.setText("Volver");
        backButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        backButton.setBackground(buttonBackgroundColor);
        backButton.setForeground(buttonTextColor);
        backButton.setFont(buttonFont);
        backButton.addListener(SWT.Selection, event -> {
            shell.dispose(); // Cierra la ventana actual
            selectionWindow.open(); // Abre la ventana SelectionWindow usando la instancia correcta
        });
    }

    private void searchAuthors() {
        String name = searchText.getText();
        table.removeAll();
        List<Author> authors = authorRepository.findByNombre(name);
        for (Author author : authors) {
            TableItem item = new TableItem(table, SWT.NONE);
            item.setText(new String[] {
                    String.valueOf(author.getId()),
                    author.getNombre(),
                    author.getApellido()
            });
            item.setData(author);
        }
    }

    private void loadAuthors() {
        table.removeAll();
        List<Author> authors = authorRepository.findAll();
        for (Author author : authors) {
            TableItem item = new TableItem(table, SWT.NONE);
            item.setText(new String[] {
                    String.valueOf(author.getId()),
                    author.getNombre(),
                    author.getApellido()
            });
            item.setData(author);
        }
    }

    private void openForm(Author author) {
        AuthorForm form = new AuthorForm(shell.getDisplay(), authorRepository, author, this);
        form.open();
        loadAuthors();
    }

    public void open() {
        shell.open();
    }
}
