package es.ssdd.academia.controllers;

import es.ssdd.academia.entities.Comment;
import es.ssdd.academia.entities.Course;
import es.ssdd.academia.services.CommentService;
import es.ssdd.academia.services.CourseService;
import es.ssdd.academia.services.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@Order(2)
@Controller
@RequestMapping("/courses")
public class CommentController {
    @Autowired
    CommentService commentService;
    @Autowired
    CourseService courseService;
    @Autowired
    ForumService forumService;


    @PostConstruct
    public void CommentController () {
        Comment comment1 = new Comment("Muy buen curso.", "Nico");
        comment1.setForum(forumService.getOne(1));
        commentService.createComment(comment1);
        //forumService.getOne(1).getCommentList().add(comment1);

        Comment comment2 = new Comment("No me ha gustado, muy mal explicado.", "Maria Antonieta");
        comment2.setForum(forumService.getOne(2));
        commentService.createComment(comment2);
        //forumService.getOne(2).getCommentList().add(comment2);

        Comment comment3 = new Comment("Curso súper útil y muy detallado, esencial para los principiantes.", "Sebastian");
        comment3.setForum(forumService.getOne(3));
        commentService.createComment(comment3);
        //forumService.getOne(3).getCommentList().add(comment3);

        Comment comment4 = new Comment("Como introducción no está mal pero podría mejorarse.", "Esther");
        comment4.setForum(forumService.getOne(1));
        commentService.createComment(comment4);
        //forumService.getOne(1).getCommentList().add(comment4);
    }

    @GetMapping("/{id}/addComment/")
    public String addComment(@PathVariable long id) {
        return "newComment";
    }

    @PostMapping("/{id}/addComment/")
    public String addComment(@PathVariable long id, @RequestParam String newComment, @RequestParam String author) {
        Course c = courseService.getOne(id);
        Comment comment = new Comment(newComment, author);
        //comment.setFk_forum(c.getFk_forum());
        comment.setForum(c.getForum());
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
