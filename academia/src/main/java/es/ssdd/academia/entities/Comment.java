package es.ssdd.academia.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private long id;
    private String comment;
    private User author;
    private long fk_forum;

    public Comment(String comment, User author) {
        this.author = author;
        this.comment = comment;
    }
}
