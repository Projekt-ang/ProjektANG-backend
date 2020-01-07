package pl.ang.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.ang.backend.model.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    @Query(value = "SELECT question_id FROM answer a WHERE a.answer_id = :id",
            nativeQuery = true)
    Long findQuestionIdByAnswerIdNative(
            @Param("id") Long id);
}
