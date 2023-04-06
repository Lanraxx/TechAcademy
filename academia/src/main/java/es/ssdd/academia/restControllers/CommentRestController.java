package es.ssdd.academia.restControllers;

import es.ssdd.academia.entities.Comment;
import es.ssdd.academia.entities.Course;
import es.ssdd.academia.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentRestController {
    @Autowired
    CommentService commentService;

    @GetMapping("/comments/")
    public ResponseEntity getAllComments() {
        return new ResponseEntity<>(commentService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/comments/{id}/")
    public ResponseEntity<Comment> getComment(@PathVariable long id) {
        Comment tempComment = commentService.getOne(id);
        if (tempComment != null) {
            return new ResponseEntity<>(tempComment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/comments/")
    public Comment addComment(@RequestBody Comment comment){
        commentService.createComment(comment);
        return comment;
    }

    @DeleteMapping("/comments/{id}/delete/")
    public ResponseEntity<Comment> deleteComment(@PathVariable long id) {
        Comment tem = commentService.deleteComment(id);
        return new ResponseEntity<>(tem, HttpStatus.OK);
    }
}
