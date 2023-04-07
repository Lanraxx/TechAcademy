package es.ssdd.academia.restControllers;

import com.fasterxml.jackson.annotation.JsonView;
import es.ssdd.academia.entities.Course;
import es.ssdd.academia.entities.User;
import es.ssdd.academia.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class CourseRestController {

    @Autowired
    CourseService courseService;

    @JsonView(Course.BasicCourse.class)
    @GetMapping("/courses/")
    public ResponseEntity getAll() {
        return new ResponseEntity<>(courseService.getAll(), HttpStatus.OK);
    }


    interface DetailsCourse extends Course.BasicCourse, Course.Users, User.BasicUser { }
    @JsonView(DetailsCourse.class)
    @GetMapping("/courses/{id}/")
    public ResponseEntity<Course> viewCourse(@PathVariable long id) {
        Course tempCourse = courseService.getOne(id);
        if (tempCourse != null) {
            return new ResponseEntity<>(tempCourse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @JsonView(Course.BasicCourse.class)
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/courses/")
    public Course addCourse(@RequestBody Course course){
        courseService.createCourse(course);
        return course;
    }

    @JsonView(Course.BasicCourse.class)
    @DeleteMapping("/courses/{id}/delete/")
    public ResponseEntity<Course> deleteCourse(@PathVariable int id){
        Course tem = courseService.deleteCourse(id);
        return new ResponseEntity<>(tem, HttpStatus.OK);
    }

    @JsonView(Course.BasicCourse.class)
    @PutMapping("/courses/{id}/edit")
    public ResponseEntity<Course> updateCourse(@RequestBody Course course, @PathVariable long id){
        Course tempCourse = courseService.getOne(id);
        if (tempCourse != null) {
            courseService.modifyCourse(id, course);
            return new ResponseEntity<>(course, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}