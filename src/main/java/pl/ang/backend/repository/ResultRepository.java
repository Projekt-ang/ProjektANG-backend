package pl.ang.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.ang.backend.model.Result;

public interface ResultRepository extends JpaRepository<Result, Long> {
}
