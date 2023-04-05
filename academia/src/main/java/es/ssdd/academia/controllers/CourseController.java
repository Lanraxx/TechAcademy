package es.ssdd.academia.controllers;

import es.ssdd.academia.entities.Comment;
import es.ssdd.academia.entities.Forum;
import es.ssdd.academia.entities.User;
import es.ssdd.academia.services.CommentService;
import es.ssdd.academia.services.CourseService;
import es.ssdd.academia.entities.Course;
import es.ssdd.academia.services.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;


@Controller
public class CourseController {

    @Autowired
    CourseService courseService;
    @Autowired
    ForumService forumService;

    @PostConstruct
    public void CourseController () {
        User u1 = new User("Pepe", "pepejaun@gmail.com", "xxxxx");
        User u2 = new User("Pepa", "junjwenc@yahoo.es", "12234");
        ArrayList<User> list = new ArrayList<User>();
        list.add(u1);
        list.add(u2);
        Course course1 = new Course("CURSO PREDEFINIDO", "GRATUITO", 1, "bueno", list, "https://www.oracle.com/oce/press/assets/CONT2F6AE229113D42EC9C59FAED5BAA0380/native/og-social-java-logo.gif");
        Course course2 = new Course("C2", "30", 4, "bueno", list, "/images/twitter_2.png");
        courseService.createCourse(course1);
        courseService.createCourse(course2);

        Forum forum1 = new Forum(course1.getId());
        forumService.createForum(forum1);
        course1.setFk_forum(forum1.getId());

        Forum forum2 = new Forum(course2.getId());
        forumService.createForum(forum2);
        course2.setFk_forum(forum2.getId());
    }

    @GetMapping("/courses/")            //courses list
    public String getAll(Model model) {
        model.addAttribute("courses", courseService.getAll());
        return "listCourses";
    }
    @GetMapping("/addCourse/")                      //Mostrar formulario añadir curso
    public String addCourse(Model model) {
        model.addAttribute("title", "FORMULARIO NUEVO CURSO");
        return "newCourse";
    }
    @PostMapping("/courses/")                  //Guardar un curso
    public String createCourse(Model model, @RequestParam String title, @RequestParam String price,
                               @RequestParam String description, @RequestParam int duration, @RequestParam String image) {
        courseService.createCourse(new Course(title, price, duration, description, null, image));
        model.addAttribute("courses", courseService.getAll());
        return "listCourses";
    }

    @GetMapping("/courses/{id}/")        //just one course
    public String getOne(Model model, @PathVariable long id) {
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

    @PostMapping("/courses/{id}/edit/")                  //Guardar un curso modificado
    public String updateCourse(Model model, @PathVariable long id, @RequestParam String title, @RequestParam String price,
                                @RequestParam String description, @RequestParam int duration, @RequestParam String image) {

        Course courseModified = new Course(title, price, duration, description, null, image);
        courseService.modifyCourse(id, courseModified);
        return "redirect:/courses/{id}/";
    }

    @GetMapping("/courses/{id}/delete/")            //Borrar curso
    public String deleteCourse(Model model, @PathVariable int id) {
        courseService.deleteCourse(id);
        return "redirect:/courses/";
    }


}
