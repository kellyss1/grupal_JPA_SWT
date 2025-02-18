package programacion.db;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "books")
@Getter
@Setter
@ToString(exclude = "author")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String isbn;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private Double precio;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;
}