package es.ssdd.academia.services;

import es.ssdd.academia.entities.Comment;
import es.ssdd.academia.entities.Forum;
import es.ssdd.academia.repositories.ForumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ForumService {

    @Autowired
    CommentService commentService;

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

    public Collection<Comment> getComments (Forum forum) {
        /*List<Comment> commentList = new ArrayList<>();
        for (Map.Entry<Long, Comment> entry : commentService.getMap().entrySet()) {
            Comment comment = entry.getValue();
            if (comment.getFk_forum() == forum.getId())
                commentList.add(comment);
        }
        return commentList;*/

        return forum.getCommentList();

    }

    public Forum deleteAllComments(long idForum) {
        //commentService.deleteCommentsOfAForum(idForum);
        //return this.getOne(idForum);

        List<Comment> comments = forumRepository.findById(idForum).get().getCommentList();
        for (int i = comments.size(); i > 0; i--) {
            commentService.deleteComment(comments.remove(i-1).getId());
        }
        return forumRepository.findById(idForum).get();
    }
}