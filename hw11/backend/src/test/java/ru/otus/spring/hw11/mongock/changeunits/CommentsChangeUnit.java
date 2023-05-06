package ru.otus.spring.hw11.mongock.changeunits;

import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import lombok.RequiredArgsConstructor;
import ru.otus.spring.hw11.domain.Comment;
import ru.otus.spring.hw11.repository.CommentRepository;

import static java.util.List.of;

@RequiredArgsConstructor
@ChangeUnit(id = "CommentsChangeUnit", order = "5")
public class CommentsChangeUnit {

    private final CommentRepository commentRepository;

    @Execution
    public void saveComments() {
        commentRepository.saveAll(of(
                new Comment("Cras non velit nec nisi vulputate nonummy. Maecenas tincidunt lacus at velit. Vivamus vel nulla eget eros elementum pellentesque."),
                new Comment("Suspendisse potenti. In eleifend quam a odio. In hac habitasse platea dictumst."),
                new Comment("Fusce posuere felis sed lacus. Morbi sem mauris, laoreet ut, rhoncus aliquet, pulvinar sed, nisl. Nunc rhoncus dui vel sem.")
        )).blockLast();
    }

    @RollbackExecution
    public void rollback() {
    }
}
