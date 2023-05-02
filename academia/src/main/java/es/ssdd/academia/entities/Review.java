package es.ssdd.academia.entities;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    public interface BasicComment{}

    public interface Forums{}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(BasicComment.class)
    private long id;
    @JsonView(BasicComment.class)
    private String comment;
    @JsonView(BasicComment.class)
    private String author;
    @ManyToOne
    @JsonView(Forums.class)
    private Forum forum;

    public Review(String comment, String author) {
        this.author = author;
        this.comment = comment;
    }
}
