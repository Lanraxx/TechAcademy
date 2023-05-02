package es.ssdd.academia.services;

import es.ssdd.academia.entities.Review;
import es.ssdd.academia.entities.Forum;
import es.ssdd.academia.repositories.ForumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ForumService {

    @Autowired
    ReviewService reviewService;

    @Autowired
    ForumRepository forumRepository;


    public Forum createForum(Forum forum){
        forumRepository.save(forum);
        return forum;
    }

    public Collection<Forum> getAll() {
        return forumRepository.findAll();
    }

    public Forum getOne(long id) {
        Optional<Forum> findForum = forumRepository.findById(id);
        if (findForum.isPresent()) {
            Forum forum = findForum.get();
            return forum;
        } else {
            return null;
        }

    }

    public Collection<Review> getComments (Forum forum) {
        return forum.getReviewList();
    }

    public Forum deleteAllComments(long idForum) {
        List<Review> reviews = forumRepository.findById(idForum).get().getReviewList();
        for (int i = reviews.size(); i > 0; i--) {
            reviewService.deleteComment(reviews.remove(i-1).getId());
        }
        return forumRepository.findById(idForum).get();
    }
}