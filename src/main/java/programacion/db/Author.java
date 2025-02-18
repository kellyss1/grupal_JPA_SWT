package programacion.db;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "authors")
@Getter
@Setter
@ToString(exclude = "books")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> books;
}
