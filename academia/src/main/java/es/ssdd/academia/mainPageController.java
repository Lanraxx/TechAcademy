package es.ssdd.academia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class mainPageController {

    @Autowired
    courseService courseService;

    @GetMapping ("/")
    public String paginaPrincipal (Model model) {
        Course c1 = new Course("Introducción a la programacion", "5", true, 20);
        courseService.createCourse(c1);
        return "Pagina_inicio";
    }

    @GetMapping("/inicio_sesion")
    public String startSession(Model model) {
        return "inicio_sesion";
    }

    @GetMapping("/registro")
    public String register(Model model){
        return "register";
    }


}