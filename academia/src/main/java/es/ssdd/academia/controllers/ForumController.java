package es.ssdd.academia.controllers;

import es.ssdd.academia.entities.Course;
import es.ssdd.academia.services.CourseService;
import es.ssdd.academia.services.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/courses")
public class ForumController {

    @Autowired
    ForumService forumService;

    @Autowired
    CourseService courseService;

    @GetMapping("/{id}/deleteComments/")    //Delete all comments
    public String deleteComments(@PathVariable long id) {
        Course c = courseService.getOne(id);
        forumService.deleteAllComments(c.getForum().getId());
        return "redirect:/courses/{id}/";
    }

}
