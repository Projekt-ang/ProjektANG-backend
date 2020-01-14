package pl.ang.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.ang.backend.model.Sentence;

public interface SentenceRepository extends JpaRepository<Sentence, Long> {
}
