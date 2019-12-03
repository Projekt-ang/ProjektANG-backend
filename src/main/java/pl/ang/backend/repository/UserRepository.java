package pl.ang.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.repository.query.Param;
import pl.ang.backend.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(@Param("username") String username);
    User findByEmail(@Param("email") String email);
}