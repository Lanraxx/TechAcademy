package es.ssdd.academia.restControllers;

import es.ssdd.academia.services.CourseService;
import es.ssdd.academia.entities.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class CourseRestController {
    @Autowired
    CourseService courseService;

    @GetMapping("/courses/")
    public ResponseEntity getAll() {
        return new ResponseEntity<>(courseService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/courses/{id}/")
    public ResponseEntity<Course> getCourse(@PathVariable long id) {
        Course tempCourse = courseService.getOne(id);
        if (tempCourse != null) {
            return new ResponseEntity<>(tempCourse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/courses/")
    public Course addCourse(@RequestBody Course course){
        courseService.createCourse(course);
        return course;
    }

    @DeleteMapping("/courses/{id}/delete/")
    public ResponseEntity<Course> deleteCourse(@PathVariable long id) {
        Course tem = courseService.deleteCourse(id);
        return new ResponseEntity<>(tem, HttpStatus.OK);
    }

    @PutMapping("/courses/{id}/edit/")
    public ResponseEntity<Course> updateCourse(@RequestBody Course course, @PathVariable long id) {
        Course tempCourse = courseService.getOne(id);
        if (tempCourse != null) {
            courseService.modifyCourse(id, course);
            return new ResponseEntity<>(course, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}