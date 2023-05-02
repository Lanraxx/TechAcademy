package es.ssdd.academia.restControllers;

import com.fasterxml.jackson.annotation.JsonView;
import es.ssdd.academia.entities.Review;
import es.ssdd.academia.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/comments")
public class ReviewRestController {

    @Autowired
    ReviewService reviewService;

    @JsonView(Review.BasicReview.class)
    @GetMapping("/")
    public ResponseEntity getAllComments() {
        return new ResponseEntity<>(reviewService.getAll(), HttpStatus.OK);
    }


    @JsonView(Review.BasicReview.class)
    @GetMapping("/{id}/")
    public ResponseEntity<Review> getComment(@PathVariable long id) {
        Review tempReview = reviewService.getOne(id);
        if (tempReview != null) {
            return new ResponseEntity<>(tempReview, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public Review addComment(@RequestBody Review review){
        reviewService.createComment(review);
        return review;
    }

    @PutMapping("/{id}/edit/")
    public ResponseEntity<Review> editComment(@RequestBody Review review, @PathVariable long id) {
        Review tempReview = reviewService.getOne(id);
        if (tempReview != null) {
            reviewService.modifyComment(id, review);
            return new ResponseEntity<>(tempReview, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}/delete/")
    public ResponseEntity<Review> deleteComment(@PathVariable long id) {
        Review tem = reviewService.getOne(id);
        if (tem != null) {
            reviewService.deleteComment(id);
            return new ResponseEntity<>(tem, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
