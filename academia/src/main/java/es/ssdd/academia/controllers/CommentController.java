package es.ssdd.academia.controllers;

import es.ssdd.academia.entities.Comment;
import es.ssdd.academia.entities.Course;
import es.ssdd.academia.services.CommentService;
import es.ssdd.academia.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@Controller
@RequestMapping("/courses")
public class CommentController {
    @Autowired
    CommentService commentService;
    @Autowired
    CourseService courseService;


    @PostConstruct
    public void CommentController () {
        Comment comment1 = new Comment("Muy buen curso.", "Nico");
        comment1.setFk_forum(1);
        commentService.createComment(comment1);

        Comment comment2 = new Comment("No me ha gustado, muy mal explicado.", "Maria Antonieta");
        comment2.setFk_forum(2);
        commentService.createComment(comment2);
    }

    @GetMapping("/{id}/addComment/")
    public String addComment(@PathVariable long id) {
        return "newComment";
    }

    @PostMapping("/{id}/addComment/")
    public String addComment(@PathVariable long id, @RequestParam String newComment, @RequestParam String author) {
        Course c = courseService.getOne(id);
        Comment comment = new Comment(newComment, author);
        comment.setFk_forum(c.getFk_forum());
        commentService.createComment(comment);
        return "redirect:/courses/{id}/";
    }

    @GetMapping("/{id}/editComment/{idC}/")
    public String editComment(Model model, @PathVariable long id, @PathVariable long idC) {
        model.addAttribute("course", courseService.getOne(id));
        model.addAttribute("comment", commentService.getOne(idC));
        return "updateComment";
    }

    @PostMapping("/{id}/editComment/{idC}/")
    public String editComment(@PathVariable long id, @PathVariable long idC, @RequestParam String newComment, @RequestParam String author) {
        Comment com = new Comment(newComment, author);
        commentService.modifyComment(idC, com);
        return "redirect:/courses/{id}/";
    }

    @GetMapping("/{id}/deleteComment/{idC}/")    //Delete comment
    public String deleteComment(@PathVariable long id, @PathVariable long idC) {
        commentService.deleteComment(idC);
        return "redirect:/courses/{id}/";
    }

}
