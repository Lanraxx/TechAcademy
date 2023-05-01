package es.ssdd.academia.repositories;

import es.ssdd.academia.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByPrice(String price);

}
