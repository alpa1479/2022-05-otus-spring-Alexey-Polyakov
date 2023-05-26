package ru.otus.spring.hw14.domain.postgres;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import ru.otus.spring.hw14.domain.EntityForMigration;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "books")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BookEntity extends EntityForMigration {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @JoinColumn(name = "genre_id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private GenreEntity genre;

    @ToString.Exclude
    @Fetch(FetchMode.SUBSELECT)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "books_authors",
            joinColumns = @JoinColumn(name = "book_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "author_id", nullable = false)
    )
    private List<AuthorEntity> authors = new ArrayList<>();

    @ToString.Exclude
    @BatchSize(size = 10)
    @OneToMany(mappedBy = "book", fetch = FetchType.EAGER)
    private List<CommentEntity> comments = new ArrayList<>();

    public BookEntity(Long id, String title, GenreEntity genre) {
        this.id = id;
        this.title = title;
        this.genre = genre;
    }

    public BookEntity(String title, GenreEntity genre) {
        this.title = title;
        this.genre = genre;
    }

    public BookEntity(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public BookEntity(Long id) {
        this.id = id;
    }

    public Long getGenreId() {
        return genre.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BookEntity book = (BookEntity) o;
        return id != null && Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
