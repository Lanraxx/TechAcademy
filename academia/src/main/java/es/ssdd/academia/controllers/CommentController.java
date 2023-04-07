package es.ssdd.academia.controllers;

import es.ssdd.academia.entities.Comment;
import es.ssdd.academia.entities.Course;
import es.ssdd.academia.entities.User;
import es.ssdd.academia.services.CommentService;
import es.ssdd.academia.services.CourseService;
import es.ssdd.academia.services.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;

@Controller
public class CommentController {
    @Autowired
    CommentService commentService;
    @Autowired
    CourseService courseService;        //ESTO TENDRÍA QUE RELACIONARSE CON EL FORO EN VEZ DE CON EL CURSO


    @PostConstruct
    public void CommentController () {
        Comment comment1 = new Comment("Muy buen curso.", new User("Álvaro", "alpasc8@gmail.com", "ckdsn"));
        comment1.setFk_forum(3);
        commentService.createComment(comment1);

        Comment comment2 = new Comment("No me ha gustado, muy mal explicado.", new User("María", "mscv78@gmail.com", "mmmm"));
        comment2.setFk_forum(4);
        commentService.createComment(comment2);
    }

    @GetMapping("/courses/{id}/addComment/")
    public String addComment(@PathVariable long id) {
        return "newComment";
    }

    @PostMapping("/courses/{id}/addComment/")
    public String addComment(@PathVariable long id, @RequestParam Comment comment) {
        Course c = courseService.getOne(id);
        comment.setFk_forum(c.getFk_forum());
        commentService.createComment(comment);
        return "redirect:/courses/{id}/";
    }

}
