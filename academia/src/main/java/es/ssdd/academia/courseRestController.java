package es.ssdd.academia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api")
public class courseRestController {

    private courseService myMapClass = new courseService();

    @Autowired
    courseService courseService;

    @PostMapping("/cursos")
    public Course createCourse(@RequestBody Course course){
        courseService.createCourse(course);
        return course;
    }

    @GetMapping("/cursos")
    public Map<Integer, Course> getCourses() {
        Map<Integer, Course> map = myMapClass.getMap();
        System.out.print("Hola");
        return map;
    }
}
