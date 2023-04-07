package es.ssdd.academia.controllers;

import es.ssdd.academia.entities.Comment;
import es.ssdd.academia.entities.Course;
import es.ssdd.academia.entities.Forum;
import es.ssdd.academia.entities.User;
import es.ssdd.academia.services.CourseService;
import es.ssdd.academia.services.ForumService;
import es.ssdd.academia.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.Collection;


@Controller
public class CourseController {

    @Autowired
    CourseService courseService;
    @Autowired
    ForumService forumService;
    @Autowired
    UserService userService;

    @PostConstruct
    public void courseController() {
        //Create courses
        Course course1 = new Course("Introducción a Java", "Gratis", 10,
                "Java es un lenguaje de programación orientado a objetos y, al mismo tiempo, una plataforma informática "
                        + "para el diseño y desarrollo de aplicaciones para distintos dispositivos."
                        + "Como curiosidad, te podemos contar que se trata de un sistema que se creó en 1995 y que tenía como "
                        + "objetivo ser un lenguaje de programación de estructura sencilla y que pudiese ser ejecutado en todos "
                        + "los sistemas operativos posibles. Java sigue siendo, tras más de 20 años en el mercado, "
                        + "uno de los lenguajes de programación más usados del mundo."
                        + "Son muchas las empresas que lo emplean para el desarrollo de tecnologías y aplicaciones en todo "
                        + "tipo de sistemas y dispositivos. Sin embargo, la cantidad de profesionales especializados en Java "
                        + "no ha crecido al mismo ritmo que su uso, por lo que los programadores y desarrolladores Java son "
                        + "perfiles muy cotizados.",
                "https://www.oracle.com/oce/press/assets/CONT2F6AE229113D42EC9C59FAED5BAA0380/native/og-social-java-logo.gif");

        Course course2 = new Course("C2", "30", 4, "bueno",
                "/images/twitter_2.png");

        course1.setUserList(userService.getUserListOfACourse(1));
        course2.setUserList(userService.getUserListOfACourse(2));
        //course1.getUserList().add(new User("JJ", "mcsiud", "jsney"));

        courseService.createCourse(course1);
        courseService.createCourse(course2);

        //Create forum
        Forum forum1 = new Forum(course1.getId());
        forumService.createForum(forum1);
        course1.setFk_forum(forum1.getId());

        Forum forum2 = new Forum(course2.getId());
        forumService.createForum(forum2);
        course2.setFk_forum(forum2.getId());
    }

    @GetMapping("/courses/")                        //courses list
    public String getAll(Model model) {
        model.addAttribute("courses", courseService.getAll());
        return "listCourses";
    }

    @GetMapping("/addCourse/")                      //Show form of add course
    public String addCourse(Model model) {
        model.addAttribute("title", "FORMULARIO NUEVO CURSO");
        return "newCourse";
    }

    @PostMapping("/courses/")                       //Save course
    public String createCourses(Model model, @RequestParam String title, @RequestParam String price,
                               @RequestParam String description, @RequestParam int duration, @RequestParam String image) {
        courseService.createCourse(new Course(title, price, duration, description, image));
        model.addAttribute("courses", courseService.getAll());
        return "listCourses";
    }

    @GetMapping("/courses/{id}/")                   //just one course
    public String course(Model model, @PathVariable int id) {
        Course course = courseService.getOne(id);
        model.addAttribute("course", course);
        Collection<Comment> commentList = forumService.getComments(forumService.getOne(course.getFk_forum()));
        model.addAttribute("comments", commentList);
        return "course";
    }

    @GetMapping("/courses/{id}/edit/")          //Put course
    public String updateCourse(Model model, @PathVariable long id) {
        model.addAttribute("course", courseService.getOne(id));
        return "updateCourse";
    }

    @PostMapping("/courses/{id}/edit/")                  //Save modify course
    public String updateCourse(Model model, @PathVariable long id, @RequestParam String title, @RequestParam String price,
                               @RequestParam String description, @RequestParam int duration, @RequestParam String image) {

        Course courseModified = new Course(title, price, duration, description, image);
        courseService.modifyCourse(id, courseModified);
        return "redirect:/courses/{id}/";
    }

    @GetMapping("/courses/{id}/delete/")            //Delete course
    public String deleteCourse(Model model, @PathVariable long id) {
        courseService.deleteCourse(id);
        return "redirect:/courses/";
    }

}
