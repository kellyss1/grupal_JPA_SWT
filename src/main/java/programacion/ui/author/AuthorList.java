package programacion.ui.author;

import org.eclipse.swt.SWT;
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
                Author author = (Author) table.getItem(selectedIndex).getData();
                openForm(author);
            }
        });

        Button deleteButton = new Button(buttonComposite, SWT.PUSH);
        deleteButton.setText("Eliminar");
        deleteButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
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
        AuthorForm form = new AuthorForm(shell.getDisplay(), authorRepository, author);
        form.open();
        loadAuthors();
    }

    public void open() {
        shell.open();
    }
}
