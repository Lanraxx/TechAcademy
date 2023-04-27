package es.ssdd.academia.restControllers;

import es.ssdd.academia.entities.Comment;
import es.ssdd.academia.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/comments")
public class CommentRestController {

    @Autowired
    CommentService commentService;

    @GetMapping("/")
    public ResponseEntity getAllComments() {
        return new ResponseEntity<>(commentService.getAll(), HttpStatus.OK);
    }


    @GetMapping("/{id}/")
    public ResponseEntity<Comment> getComment(@PathVariable long id) {
        Comment tempComment = commentService.getOne(id);
        if (tempComment != null) {
            return new ResponseEntity<>(tempComment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public Comment addComment(@RequestBody Comment comment){
        commentService.createComment(comment);
        return comment;
    }

    @PutMapping("/{id}/edit/")
    public ResponseEntity<Comment> editComment(@RequestBody Comment comment, @PathVariable long id) {
        Comment tempComment = commentService.getOne(id);
        if (tempComment != null) {
            commentService.modifyComment(id, comment);
            return new ResponseEntity<>(tempComment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}/delete/")
    public ResponseEntity<Comment> deleteComment(@PathVariable long id) {
        Comment tem = commentService.deleteComment(id);
        return new ResponseEntity<>(tem, HttpStatus.OK);
    }
}
