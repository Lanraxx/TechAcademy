package practica.academia;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AcademyController {
    private List<User> userList = new ArrayList<>();
    private Map<Integer, Course> courses = new HashMap<>();

    public AcademyController () {
        courses.put(1, new Course("Introducción a Java", 0, 3, 10, false));
    }

    @GetMapping ("/")
    public String mainPage (Model model) {
        model.addAttribute("courses", courses);
        return "pagina_inicio";
    }
    @PostMapping ("/registro")
    public String nuevoRegistro (Model model, User user) {
        userList.add(user);
        return "pagina_inicio";
    }
    @GetMapping ("/inicioSesion")
    public String inicioSesion (Model model) {
        return "pagina_inicio";
    }

    @GetMapping ("/curso/{courseId}")
    public String verCurso (Model model, @PathVariable int courseId) {
        Course course = courses.get(courseId);
        model.addAttribute("course", course);
        return "curso";
    }
}
