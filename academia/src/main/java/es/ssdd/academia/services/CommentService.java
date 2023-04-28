package es.ssdd.academia.services;

import es.ssdd.academia.entities.Comment;
import es.ssdd.academia.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    /*private Map<Long, Comment> mapComments = new ConcurrentHashMap<>();
    private AtomicLong id = new AtomicLong();*/

    public Comment createComment(Comment comment){
        //long tem = id.incrementAndGet();
        //comment.setId(tem);
        //mapComments.put(tem, comment);
        commentRepository.save(comment);
        return comment;
    }

    public Collection<Comment> getAll() {
        //return mapComments.values();
        return commentRepository.findAll();
    }

    public Comment getOne(long id) {
        //return mapComments.get(id);
        Optional<Comment> findComment = commentRepository.findById(id);
        if (findComment.isPresent()) {
            Comment comment = findComment.get();
            return comment;
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

    public Comment deleteComment(long id) {
        Optional<Comment> findComment = commentRepository.findById(id);
        if (findComment.isPresent()) {
            Comment c = findComment.get();
            commentRepository.delete(c);
            return c;
        }
        return null;
    }

    public Comment modifyComment(long id, Comment newComment) {
        //Comment comment = mapComments.get(id);
        Optional<Comment> findComment = commentRepository.findById(id);
        if (findComment.isPresent()) {
            Comment comment = findComment.get();
            comment.setComment(newComment.getComment());
            comment.setAuthor(newComment.getAuthor());
            return comment;
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
