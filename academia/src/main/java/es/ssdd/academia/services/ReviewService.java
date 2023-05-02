package es.ssdd.academia.services;

import es.ssdd.academia.entities.Review;
import es.ssdd.academia.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    public Review createComment(Review review){
        reviewRepository.save(review);
        return review;
    }

    public Collection<Review> getAll() {
        return reviewRepository.findAll();
    }

    public Review getOne(long id) {
        Optional<Review> findComment = reviewRepository.findById(id);
        if (findComment.isPresent()) {
            Review review = findComment.get();
            return review;
        }
        return null;
    }

    public Review deleteComment(long id) {
        Optional<Review> findComment = reviewRepository.findById(id);
        if (findComment.isPresent()) {
            Review c = findComment.get();
            reviewRepository.delete(c);
            return c;
        }
        return null;
    }

    public Review modifyComment(long id, Review newReview) {
        Optional<Review> findComment = reviewRepository.findById(id);
        if (findComment.isPresent()) {
            newReview.setId(id);
            newReview.setForum(findComment.get().getForum());
            reviewRepository.save(newReview);
            return newReview;
        }
        return null;
    }
}
