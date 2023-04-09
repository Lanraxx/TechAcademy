package es.ssdd.academia.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    //public interface BasicComment{}
    private long id;
    private String comment;
    private String author;
    private long fk_forum;

    public Comment(String comment, String author) {
        this.author = author;
        this.comment = comment;
    }
}
