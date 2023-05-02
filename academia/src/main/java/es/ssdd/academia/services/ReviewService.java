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

    /*private Map<Long, Comment> mapComments = new ConcurrentHashMap<>();
    private AtomicLong id = new AtomicLong();*/

    public Review createComment(Review review){
        //long tem = id.incrementAndGet();
        //comment.setId(tem);
        //mapComments.put(tem, comment);
        reviewRepository.save(review);
        return review;
    }

    public Collection<Review> getAll() {
        //return mapComments.values();
        return reviewRepository.findAll();
    }

    public Review getOne(long id) {
        //return mapComments.get(id);
        Optional<Review> findComment = reviewRepository.findById(id);
        if (findComment.isPresent()) {
            Review review = findComment.get();
            return review;
        }
        return null;
    }
    /*public Map<Long, Comment> getMap() {
        return mapComments;
    }*/

    /*public Comment deleteComment(long id) {
        for (Map.Entry<Long, Comment> entry : mapComments.entrySet()) {
            Comment c = entry.getValue();
            if (id == c.getId()){
                mapComments.remove(id);
                return c;
            }
        }
        return null;
    }*/

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
        //Comment comment = mapComments.get(id);
        Optional<Review> findComment = reviewRepository.findById(id);
        if (findComment.isPresent()) {
            newReview.setId(id);
            newReview.setForum(findComment.get().getForum());
            reviewRepository.save(newReview);
            return newReview;
        }
        /*
        comment.setComment(newComment.getComment());
        comment.setAuthor(newComment.getAuthor());
        return comment;
         */
        return null;
    }

    /*public void deleteCommentsOfAForum(long idForum) {
        for (Map.Entry<Long, Comment> entry : mapComments.entrySet()) {
            Comment c = entry.getValue();
            if (idForum == c.getFk_forum()){
                mapComments.remove(c.getId());
            }
        }
    }*/
}
