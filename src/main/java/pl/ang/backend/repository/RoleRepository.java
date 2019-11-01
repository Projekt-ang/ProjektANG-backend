package pl.ang.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import pl.ang.backend.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(@Param("name") String name);
}