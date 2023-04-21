package ru.otus.spring.hw06.dao.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.spring.hw06.domain.Book;
import ru.otus.spring.hw06.domain.Comment;
import ru.otus.spring.hw06.repository.CommentRepository;
import ru.otus.spring.hw06.repository.impl.CommentRepositoryJpa;

import javax.persistence.TypedQuery;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({CommentRepositoryJpa.class})
@DisplayName("Given comment repository jpa")
class CommentRepositoryJpaTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private CommentRepository commentRepository;

    @Test
    @DisplayName("should return all comments")
    void shouldReturnAllComments() {
        assertThat(commentRepository.findAll()).isEqualTo(getExpectedComments());
    }

    @Test
    @DisplayName("should return comment by id")
    void shouldReturnCommentById() {
        assertThat(commentRepository.findById(1)).isEqualTo(getExpectedComment());
    }

    @Test
    @DisplayName("should create comment")
    void shouldCreateComment() {
        Comment commentToCreate = new Comment("new comment", getBookByTitle("The Oblong Box"));
        Comment createdComment = commentRepository.create(commentToCreate);
        assertThat(createdComment).isEqualTo(commentToCreate);
    }

    @Test
    @DisplayName("should update comment")
    void shouldUpdateComment() {
        Comment commentToUpdate = new Comment(1L, "updated comment text", getBookByTitle("The Oblong Box"));
        commentRepository.update(commentToUpdate);
        assertThat(commentRepository.findById(commentToUpdate.getId())).isEqualTo(commentToUpdate);
    }

    @Test
    @DisplayName("should delete comment by id")
    void shouldDeleteCommentById() {
        commentRepository.deleteById(1);
        assertThat(commentRepository.findById(1)).isNull();
    }

    Comment getExpectedComment() {
        return new Comment(1L, "Cras non velit nec nisi vulputate nonummy. Maecenas tincidunt lacus at velit. Vivamus vel nulla eget eros elementum pellentesque.", getBookByTitle("The Oblong Box"));
    }

    List<Comment> getExpectedComments() {
        return List.of(
                getExpectedComment(),
                new Comment(2L, "Suspendisse potenti. In eleifend quam a odio. In hac habitasse platea dictumst.", getBookByTitle("The Oblong Box")),
                new Comment(3L, "Fusce posuere felis sed lacus. Morbi sem mauris, laoreet ut, rhoncus aliquet, pulvinar sed, nisl. Nunc rhoncus dui vel sem.", getBookByTitle("The Oblong Box")),
                new Comment(4L, "Nullam sit amet turpis elementum ligula vehicula consequat. Morbi a ipsum. Integer a nibh", getBookByTitle("Skin Deep")),
                new Comment(5L, "Morbi non lectus. Aliquam sit amet diam in magna bibendum imperdiet. Nullam orci pede, venenatis non, sodales sed, tincidunt eu, felis", getBookByTitle("Summer Catch")),
                new Comment(6L, "Quisque porta volutpat erat. Quisque erat eros, viverra eget, congue eget, semper rutrum, nulla. Nunc purus.", getBookByTitle("Summer Catch")),
                new Comment(7L, "Maecenas leo odio, condimentum id, luctus nec, molestie sed, justo. Pellentesque viverra pede ac diam. Cras pellentesque volutpat dui.", getBookByTitle("Summer Catch")),
                new Comment(8L, "Duis aliquam convallis nunc. Proin at turpis a pede posuere nonummy. Integer non velit.", getBookByTitle("The Missionaries")),
                new Comment(9L, "Donec diam neque, vestibulum eget, vulputate ut, ultrices vel, augue. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae", getBookByTitle("The Missionaries")),
                new Comment(10L, "Duis bibendum. Morbi non quam nec dui luctus rutrum. Nulla tellus.", getBookByTitle("9 Star Hotel (Malon 9 Kochavim)")),
                new Comment(11L, "Vestibulum quam sapien, varius ut, blandit non, interdum in, ante. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Duis faucibus accumsan odio. Curabitur convallis.", getBookByTitle("9 Star Hotel (Malon 9 Kochavim)")),
                new Comment(12L, "Maecenas tristique, est et tempus semper, est quam pharetra magna, ac consequat metus sapien ut nunc. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae", getBookByTitle("Street Kings")),
                new Comment(13L, "Nam ultrices, libero non mattis pulvinar, nulla pede ullamcorper augue, a suscipit nulla elit ac nulla. Sed vel enim sit amet nunc viverra dapibus. Nulla suscipit ligula in lacus.", getBookByTitle("Street Kings")),
                new Comment(14L, "Quisque porta volutpat erat. Quisque erat eros, viverra eget, congue eget, semper rutrum, nulla. Nunc purus.", getBookByTitle("Squeeze")),
                new Comment(15L, "Phasellus sit amet erat. Nulla tempus. Vivamus in felis eu sapien cursus vestibulum.", getBookByTitle("Squeeze")),
                new Comment(16L, "Proin eu mi. Nulla ac enim. In tempor, turpis nec euismod scelerisque, quam turpis adipiscing lorem, vitae mattis nibh ligula nec sem.", getBookByTitle("Old San Francisco")),
                new Comment(17L, "Duis aliquam convallis nunc. Proin at turpis a pede posuere nonummy. Integer non velit.", getBookByTitle("Old San Francisco")),
                new Comment(18L, "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Proin risus. Praesent lectus.", getBookByTitle("Pauly Shore Is Dead")),
                new Comment(19L, "Vestibulum quam sapien, varius ut, blandit non, interdum in, ante. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Duis faucibus accumsan odio. Curabitur convallis.", getBookByTitle("Pauly Shore Is Dead")),
                new Comment(20L, "sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Vivamus vestibulum sagittis sapien. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.", getBookByTitle("Wayward Bus")),
                new Comment(21L, "Etiam vel augue. Vestibulum rutrum rutrum neque. Aenean auctor gravida sem.", getBookByTitle("Wayward Bus")),
                new Comment(22L, "Quisque porta volutpat erat. Quisque erat eros, viverra eget, congue eget, semper rutrum, nulla. Nunc purus.", getBookByTitle("Brimstone and Treacle")),
                new Comment(23L, "Curabitur in libero ut massa volutpat convallis. Morbi odio odio, elementum eu, interdum eu, tincidunt in, leo. Maecenas pulvinar lobortis est.", getBookByTitle("Brimstone and Treacle")),
                new Comment(24L, "Phasellus sit amet erat. Nulla tempus. Vivamus in felis eu sapien cursus vestibulum.", getBookByTitle("The Walking Stick")),
                new Comment(25L, "Duis bibendum, felis sed interdum venenatis, turpis enim blandit mi, in porttitor pede justo eu massa. Donec dapibus. Duis at velit eu est congue elementum.", getBookByTitle("The Walking Stick")),
                new Comment(26L, "Maecenas leo odio, condimentum id, luctus nec, molestie sed, justo. Pellentesque viverra pede ac diam. Cras pellentesque volutpat dui.", getBookByTitle("Eight Below")),
                new Comment(27L, "Mauris viverra diam vitae quam. Suspendisse potenti.", getBookByTitle("Eight Below")),
                new Comment(28L, "Maecenas tristique, est et tempus semper, est quam pharetra magna, ac consequat metus sapien ut nunc. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae;", getBookByTitle("Eight Below")),
                new Comment(29L, "Nullam porttitor lacus at turpis. Donec posuere metus vitae ipsum. Aliquam non mauris.", getBookByTitle("Riders of the Purple Sage")),
                new Comment(30L, "In sagittis dui vel nisl. Duis ac nibh. Fusce lacus purus, aliquet at, feugiat non, pretium quis, lectus.", getBookByTitle("Harry and Tonto")),
                new Comment(31L, "Suspendisse potenti. In eleifend quam a odio. In hac habitasse platea dictumst.", getBookByTitle("Harry and Tonto")),
                new Comment(32L, "Aliquam quis turpis eget elit sodales scelerisque. Mauris sit amet eros. Suspendisse accumsan tortor quis turpis.", getBookByTitle("Harry and Tonto"))
        );
    }

    Book getBookByTitle(String title) {
        TypedQuery<Book> query = testEntityManager.getEntityManager().createQuery("select new Book(b.id, b.title) from Book b where b.title = :title", Book.class);
        query.setParameter("title", title);
        return query.getSingleResult();
    }
}