package es.ssdd.academia.repositories;

import es.ssdd.academia.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
