package es.ssdd.academia.repositories;

import es.ssdd.academia.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
