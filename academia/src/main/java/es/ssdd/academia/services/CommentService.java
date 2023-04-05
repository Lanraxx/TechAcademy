package es.ssdd.academia.services;

import es.ssdd.academia.entities.Comment;
import es.ssdd.academia.entities.Course;
import es.ssdd.academia.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class CommentService {
    @Autowired
    UserService userService;

    private Map<Long, Comment> mapComments = new ConcurrentHashMap<>();
    private AtomicLong id = new AtomicLong();

    public Comment createComment(Comment comment){
        long tem = id.incrementAndGet();
        comment.setId(tem);
        mapComments.put(tem, comment);
        return comment;
    }

    public Collection<Comment> getAll() {
        return mapComments.values();
    }
    public Map<Long, Comment> getMap() {return mapComments;}
}
