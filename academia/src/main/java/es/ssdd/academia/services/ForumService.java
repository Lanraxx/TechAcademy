package es.ssdd.academia.services;

import es.ssdd.academia.entities.Comment;
import es.ssdd.academia.entities.Forum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ForumService {
    @Autowired
    CommentService commentService;

    private Map<Long, Forum> forumMap = new ConcurrentHashMap<Long, Forum>();
    private AtomicLong id = new AtomicLong();

    public Forum createForum(Forum forum){
        long tem = id.incrementAndGet();
        forum.setId(tem);
        forumMap.put(tem, forum);
        return forum;
    }

    public Collection<Forum> getAll() {
        return forumMap.values();
    }

    public Forum getOne(long id) {
        return forumMap.get(id);
    }

    public Forum deleteForum(long id) {
        this.deleteAllComments(id);
        return forumMap.remove(id);
    }

    public Collection<Comment> getComments (Forum forum) {
        List<Comment> commentList = new ArrayList<>();
        for (Map.Entry<Long, Comment> entry : commentService.getMap().entrySet()) {
            Comment comment = entry.getValue();
            if (comment.getFk_forum() == forum.getId())
                commentList.add(comment);
        }
        return commentList;
    }

    public Forum deleteAllComments(long idForum) {
        commentService.deleteCommentsOfAForum(idForum);
        return this.getOne(idForum);
    }
}