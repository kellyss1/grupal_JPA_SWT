package programacion.ui.author;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.*;
import programacion.db.Author;
import programacion.repositorio.interfaces.AuthorRepository;

import java.util.List;

public class AuthorList {
    private Shell shell;
    private Table table;
    private Text searchText;
    private AuthorRepository authorRepository;

    public AuthorList(Display display, AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
        shell = new Shell(display);
        shell.setText("Lista de Autores");
        shell.setSize(400, 350);
        createTable();
        createSearchBox();
        loadAuthors();
    }

    private void createTable() {
        table = new Table(shell, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
        table.setHeaderVisible(true);
        table.setLinesVisible(true);
        table.setBounds(10, 10, 380, 200);

        String[] titles = { "ID", "Nombre", "Apellido" };
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
                Author author = (Author) table.getItem(selectedIndex).getData();
                openForm(author);
            }
        });

        Button deleteButton = new Button(shell, SWT.PUSH);
        deleteButton.setText("Eliminar");
        deleteButton.setBounds(190, 220, 80, 30);
        deleteButton.addListener(SWT.Selection, event -> {
            int selectedIndex = table.getSelectionIndex();
            if (selectedIndex != -1) {
                Author author = (Author) table.getItem(selectedIndex).getData();
                authorRepository.attachAndRemove(author);
                loadAuthors();
            }
        });
    }

    private void createSearchBox() {
        Label searchLabel = new Label(shell, SWT.NONE);
        searchLabel.setText("Buscar por Nombre:");
        searchLabel.setBounds(10, 260, 100, 25);

        searchText = new Text(shell, SWT.BORDER);
        searchText.setBounds(120, 260, 180, 25);

        Button searchButton = new Button(shell, SWT.PUSH);
        searchButton.setText("Buscar");
        searchButton.setBounds(310, 260, 80, 30);
        searchButton.addListener(SWT.Selection, event -> searchAuthors());
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