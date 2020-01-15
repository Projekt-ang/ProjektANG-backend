package pl.ang.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import pl.ang.backend.model.Sentence;

public interface SentenceRepository extends JpaRepository<Sentence, Long> {
    Sentence findByPolishSentence(@Param("polishSentence") String polishSentence);
}
