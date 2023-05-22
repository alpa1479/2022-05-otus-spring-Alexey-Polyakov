package ru.otus.spring.hw14.domain.postgres;

import lombok.*;
import org.hibernate.Hibernate;
import ru.otus.spring.hw14.domain.EntityForMigration;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "comments")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommentEntity extends EntityForMigration {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "text", nullable = false)
    private String text;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private BookEntity book;

    public CommentEntity(Long id, String text) {
        this.id = id;
        this.text = text;
    }

    public CommentEntity(String text, BookEntity book) {
        this.text = text;
        this.book = book;
    }

    public CommentEntity(Long id, Long bookId, String bookTitle, String text) {
        this.id = id;
        this.text = text;
        this.book = new BookEntity(bookId, bookTitle);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CommentEntity comment = (CommentEntity) o;
        return id != null && Objects.equals(id, comment.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
