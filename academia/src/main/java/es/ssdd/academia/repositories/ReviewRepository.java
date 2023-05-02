package es.ssdd.academia.repositories;

import es.ssdd.academia.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
