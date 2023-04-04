package es.ssdd.academia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class courseController {

    @Autowired
    courseService courseService;

    /*@PostMapping("/addCurso")
    public void createCourse(Model model) {

    }*/


    @GetMapping("/cursos")
    public String listCourses(Model model) {
        //model.addAttribute("courses", courseService.getAll());
        return "listCourses";
    }
}
