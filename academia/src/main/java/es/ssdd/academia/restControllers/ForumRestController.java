package es.ssdd.academia.restControllers;

import com.fasterxml.jackson.annotation.JsonView;
import es.ssdd.academia.entities.Review;
import es.ssdd.academia.entities.Forum;
import es.ssdd.academia.services.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/forum")
public class ForumRestController {
    interface DetailsForum extends Forum.BasicForum, Forum.Comments, Review.BasicComment {}
    @Autowired
    ForumService forumService;

    @JsonView(Forum.BasicForum.class)
    @GetMapping("/")
    public ResponseEntity getAll() {
        return new ResponseEntity<>(forumService.getAll(), HttpStatus.OK);
    }

    @JsonView(DetailsForum.class)
    @GetMapping("/{id}/")
    public ResponseEntity<Forum> getForum(@PathVariable long id) {
        Forum tempForum = forumService.getOne(id);
        if (tempForum != null) {
            return new ResponseEntity<>(tempForum, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @JsonView(Forum.BasicForum.class)
    @DeleteMapping("/{id}/delete/")
    public ResponseEntity<Forum> deleteForum(@PathVariable long id) {
        Forum tem = forumService.deleteAllComments(id);
        return new ResponseEntity<>(tem, HttpStatus.OK);
    }

}