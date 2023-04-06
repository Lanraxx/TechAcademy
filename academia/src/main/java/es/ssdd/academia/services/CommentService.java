package es.ssdd.academia.services;

import es.ssdd.academia.entities.Comment;
import es.ssdd.academia.entities.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class CommentService {

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
    public Comment getOne(long id) {
        return mapComments.get(id);
    }
    public Map<Long, Comment> getMap() {return mapComments;}

    public Comment deleteComment(long id) {
        for (Map.Entry<Long, Comment> entry : mapComments.entrySet()) {
            Comment c = entry.getValue();
            if (id == c.getId()){
                mapComments.remove(id);
                return c;
            }
        }
        return null;
    }
}
