package pl.ang.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.ang.backend.model.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
