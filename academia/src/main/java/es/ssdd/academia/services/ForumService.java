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
    /*private Map<Long, Forum> forumMap = new ConcurrentHashMap<>();
    private AtomicLong id = new AtomicLong();*/


    public Forum createForum(Forum forum){
        //long tem = id.incrementAndGet();
        //forum.setId(tem);
        //forumMap.put(tem, forum);
        forumRepository.save(forum);
        return forum;
    }

    public Collection<Forum> getAll() {
        //return forumMap.values();
        return forumRepository.findAll();
    }

    public Forum getOne(long id) {
        //return forumMap.get(id);
        Optional<Forum> findForum = forumRepository.findById(id);
        if (findForum.isPresent()) {
            Forum forum = findForum.get();
            return forum;
        } else {
            return null;
        }
    }

    /*public Forum deleteForum(long id) {
        this.deleteAllComments(id);
        return forumMap.remove(id);
    }*/

    public Collection<Review> getComments (Forum forum) {
        /*List<Comment> commentList = new ArrayList<>();
        for (Map.Entry<Long, Comment> entry : commentService.getMap().entrySet()) {
            Comment comment = entry.getValue();
            if (comment.getFk_forum() == forum.getId())
                commentList.add(comment);
        }
        return commentList;*/

        return forum.getReviewList();

    }

    public Forum deleteAllComments(long idForum) {
        //commentService.deleteCommentsOfAForum(idForum);
        //return this.getOne(idForum);

        List<Review> reviews = forumRepository.findById(idForum).get().getReviewList();
        for (int i = reviews.size(); i > 0; i--) {
            reviewService.deleteComment(reviews.remove(i-1).getId());
        }
        return forumRepository.findById(idForum).get();
    }
}