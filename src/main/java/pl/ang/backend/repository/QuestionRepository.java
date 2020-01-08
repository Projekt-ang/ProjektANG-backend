package pl.ang.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.ang.backend.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
