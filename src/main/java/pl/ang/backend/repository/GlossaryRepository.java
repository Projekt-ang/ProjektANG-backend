package pl.ang.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.ang.backend.model.Glossary;

public interface GlossaryRepository extends JpaRepository<Glossary, Long> {
}
