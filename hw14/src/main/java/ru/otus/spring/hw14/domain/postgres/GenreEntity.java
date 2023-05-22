package ru.otus.spring.hw14.domain.postgres;

import lombok.*;
import org.hibernate.Hibernate;
import ru.otus.spring.hw14.domain.EntityForMigration;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "genres")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GenreEntity extends EntityForMigration {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    public GenreEntity(Long id) {
        this.id = id;
    }

    public GenreEntity(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        GenreEntity genre = (GenreEntity) o;
        return id != null && Objects.equals(id, genre.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
