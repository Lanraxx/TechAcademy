package es.ssdd.academia.repositories;

import es.ssdd.academia.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByPrice(String price);

}
