package pl.ang.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.ang.backend.model.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {
}