package pl.ang.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import pl.ang.backend.model.ReadingVideoTest;
import pl.ang.backend.model.Role;

import java.util.Set;
import java.util.List;

public interface ReadingVideoTestRepository extends JpaRepository<ReadingVideoTest, Long> {
    List<ReadingVideoTest> findByName(@Param("name") String name);
    List<ReadingVideoTest> findByRoles(@Param("roles") Set<Role> roles);
}
