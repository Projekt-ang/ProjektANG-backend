package pl.ang.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import pl.ang.backend.model.Glossary;

public interface GlossaryRepository extends JpaRepository<Glossary, Long> {
    Glossary findByWord(@Param("word") String word);
}
