package es.ssdd.academia.controllers;

import es.ssdd.academia.entities.Comment;
import es.ssdd.academia.entities.Course;
import es.ssdd.academia.services.CommentService;
import es.ssdd.academia.services.CourseService;
import es.ssdd.academia.services.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;

@RequestMapping("/courses")
@Controller
public class ForumController {
    @Autowired
    CommentService commentService;
    @Autowired
    CourseService courseService;

    @GetMapping("/{id}/deleteComments/")    //Delete all comments
    public String deleteComments(@PathVariable int id) {
        Course c = courseService.getOne(id);
        commentService.deleteAllComments(c.getFk_forum());
        return "redirect:/courses/{id}/";
    }

}
