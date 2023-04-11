package es.ssdd.academia.controllers;

import es.ssdd.academia.entities.Comment;
import es.ssdd.academia.services.CourseService;
import es.ssdd.academia.entities.Course;
import es.ssdd.academia.services.ForumService;
import es.ssdd.academia.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.Collection;

@RequestMapping("/courses")
@Controller
public class CourseController {

    @Autowired
    CourseService courseService;
    @Autowired
    ForumService forumService;
    @Autowired
    UserService userService;

    @PostConstruct
    public void CourseController () {
        //Create courses
        Course course1 = new Course("Introducción a Java", "Gratis", "5 horas",
                "Java es un lenguaje de programación orientado a objetos y, al mismo tiempo, una plataforma informática "
                + "para el diseño y desarrollo de aplicaciones para distintos dispositivos."
                + "Como curiosidad, te podemos contar que se trata de un sistema que se creó en 1995 y que tenía como "
                + "objetivo ser un lenguaje de programación de estructura sencilla y que pudiese ser ejecutado en todos "
                + "los sistemas operativos posibles. Java sigue siendo, tras más de 20 años en el mercado, "
                + "uno de los lenguajes de programación más usados del mundo.",
                "https://www.oracle.com/oce/press/assets/CONT2F6AE229113D42EC9C59FAED5BAA0380/native/og-social-java-logo.gif");

        Course course2 = new Course("Excel II", "10 euros", "1 hora y 30 minutos",
                "Excel es una herramienta muy eficaz para obtener información con significado a partir de grandes cantidades "
                        + "de datos. También funciona muy bien con cálculos sencillos y para realizar el seguimiento de casi cualquier "
                        + "tipo de información. Tiene una gran capacidad de computación y gráfica ya que es capaz de convertir datos, "
                        + "estadísticas, cálculos, textos, números y archivos ya existentes en reportes fáciles de comprender, utilizando "
                        + "gráficos y fórmulas. A día de hoy, son muchas las empresas que solicitan conocer y usar Excel para acceder a "
                        + "un puesto de trabajo",
                "https://concepto.de/wp-content/uploads/2018/09/excel3-e1537469723415.jpg");

        Course course3 = new Course("Cloud Computing: herramientas en la nube", "5 euros", "2 horas",
                "El Cloud Computing, más conocido como 'la nube', es una tecnología que permite utilizar diferentes servicios "
                        + "como el almacenamiento de archivos, uso de aplicaciones o la conexión de dispositivos, todo ello sin ocupar "
                        + "espacio en el disco duro de nuestro ordenador, está alojado en servidores remotos. Herramientas que se tratan "
                        + "en este curso son: Google Drive, Evernote, Dropbox.",
                "https://ozein.es/wp-content/uploads/2021/03/Cloud-Computing.jpg");

        Course course4 = new Course("Python avanzado", "Gratis", "4 horas y 30 minutos",
                "Python es un lenguaje de alto nivel de programación interpretado cuya filosofía hace hincapié en la "
                        + "legibilidad de su código, se utiliza para desarrollar aplicaciones de todo tipo, ejemplos: Instagram, Netflix, "
                        + "Spotify, Panda3D, entre otros. Los desarrolladores utilizan Python porque es eficiente y fácil de aprender, "
                        + "además de que se puede ejecutar en muchas plataformas diferentes.",
                "https://www.icog.es/cursos/wp-content/uploads/2020/09/phyton.png");

        course1.setUserList(userService.getUserListOfACourse(1));
        course2.setUserList(userService.getUserListOfACourse(2));
        course3.setUserList(userService.getUserListOfACourse(3));
        course4.setUserList(userService.getUserListOfACourse(4));

        //Add new courses in map and create their forum
        courseService.createCourse(course1);
        courseService.createCourse(course2);
        courseService.createCourse(course3);
        courseService.createCourse(course4);
    }

    @GetMapping("/")        //courses list
    public String getAll(Model model) {
        model.addAttribute("courses", courseService.getAll());
        return "listCourses";
    }
    @GetMapping("/addCourse/")      //Show form of add course
    public String addCourse() {
        return "newCourse";
    }
    @PostMapping("/")       //Save course
    public String createCourse(Model model, @RequestParam String title, @RequestParam String price,
                               @RequestParam String description, @RequestParam String duration, @RequestParam String image) {

        courseService.createCourse(new Course(title, price, duration, description, image));
        model.addAttribute("courses", courseService.getAll());
        return "listCourses";
    }

    @GetMapping("/{id}/")       //just one course
    public String getOne(Model model, @PathVariable long id) {
        Course course = courseService.getOne(id);
        model.addAttribute("course", course);
        Collection<Comment> commentList = forumService.getComments(forumService.getOne(course.getFk_forum()));
        model.addAttribute("comments", commentList);
        return "course";
    }

    @GetMapping("/{id}/edit/")      //Put course
    public String updateCourse(Model model, @PathVariable long id) {
        model.addAttribute("course", courseService.getOne(id));
        return "updateCourse";
    }

    @PostMapping("/{id}/edit/")     //Save modified course
    public String updateCourse(Model model, @PathVariable long id, @RequestParam String title, @RequestParam String price,
                                @RequestParam String description, @RequestParam String duration, @RequestParam String image) {

        Course courseModified = new Course(title, price, duration, description, image);
        courseService.modifyCourse(id, courseModified);
        return "redirect:/courses/{id}/";
    }

    @GetMapping("/{id}/delete/")    //Delete course
    public String deleteCourse(@PathVariable long id) {
        courseService.deleteCourse(id);
        return "redirect:/courses/";
    }


}
