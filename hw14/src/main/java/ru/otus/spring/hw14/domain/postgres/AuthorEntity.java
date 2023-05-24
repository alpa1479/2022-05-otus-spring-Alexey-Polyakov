package ru.otus.spring.hw14.domain.postgres;

import lombok.*;
import org.hibernate.Hibernate;
import ru.otus.spring.hw14.domain.EntityForMigration;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "authors")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AuthorEntity extends EntityForMigration {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    public AuthorEntity(Long id) {
        this.id = id;
    }

    public AuthorEntity(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AuthorEntity author = (AuthorEntity) o;
        return id != null && Objects.equals(id, author.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
