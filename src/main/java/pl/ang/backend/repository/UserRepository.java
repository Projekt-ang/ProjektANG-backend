package pl.ang.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.ang.backend.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}