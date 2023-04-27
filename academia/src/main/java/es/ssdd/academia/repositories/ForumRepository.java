package es.ssdd.academia.repositories;

import es.ssdd.academia.entities.Forum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForumRepository extends JpaRepository<Forum, Long> {
}
