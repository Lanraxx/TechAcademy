package es.ssdd.academia.controllers;

import es.ssdd.academia.entities.Review;
import es.ssdd.academia.entities.Course;
import es.ssdd.academia.services.ReviewService;
import es.ssdd.academia.services.CourseService;
import es.ssdd.academia.services.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@Controller
@RequestMapping("/courses")
public class ReviewController {
    @Autowired
    ReviewService reviewService;
    @Autowired
    CourseService courseService;
    @Autowired
    ForumService forumService;


    @PostConstruct
    public void CommentController () {
        Review review1 = new Review("Muy buen curso.", "Nico");
        review1.setForum(forumService.getOne(1));
        reviewService.createComment(review1);
        //forumService.getOne(1).getCommentList().add(comment1);

        Review review2 = new Review("No me ha gustado, muy mal explicado.", "Maria Antonieta");
        review2.setForum(forumService.getOne(2));
        reviewService.createComment(review2);
        //forumService.getOne(2).getCommentList().add(comment2);

        Review review3 = new Review("Curso súper útil y muy detallado, esencial para los principiantes.", "Sebastian");
        review3.setForum(forumService.getOne(3));
        reviewService.createComment(review3);
        //forumService.getOne(3).getCommentList().add(comment3);

        Review review4 = new Review("Como introducción no está mal pero podría mejorarse.", "Esther");
        review4.setForum(forumService.getOne(1));
        reviewService.createComment(review4);
        //forumService.getOne(1).getCommentList().add(comment4);
    }

    @GetMapping("/{id}/addComment/")
    public String addComment(@PathVariable long id) {
        return "newComment";
    }

    @PostMapping("/{id}/addComment/")
    public String addComment(@PathVariable long id, @RequestParam String newComment, @RequestParam String author) {
        Course c = courseService.getOne(id);
        Review review = new Review(newComment, author);
        //comment.setFk_forum(c.getFk_forum());
        review.setForum(c.getForum());
        reviewService.createComment(review);
        return "redirect:/courses/{id}/";
    }

    @GetMapping("/{id}/editComment/{idC}/")
    public String editComment(Model model, @PathVariable long id, @PathVariable long idC) {
        model.addAttribute("course", courseService.getOne(id));
        model.addAttribute("comment", reviewService.getOne(idC));
        return "updateComment";
    }

    @PostMapping("/{id}/editComment/{idC}/")
    public String editComment(@PathVariable long id, @PathVariable long idC, @RequestParam String newComment, @RequestParam String author) {
        Review com = new Review(newComment, author);
        reviewService.modifyComment(idC, com);
        return "redirect:/courses/{id}/";
    }

    @GetMapping("/{id}/deleteComment/{idC}/")    //Delete comment
    public String deleteComment(@PathVariable long id, @PathVariable long idC) {
        reviewService.deleteComment(idC);
        return "redirect:/courses/{id}/";
    }

}
