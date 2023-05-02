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

    public interface BasicReview{}

    public interface Forums{}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(BasicReview.class)
    private long id;
    @JsonView(BasicReview.class)
    private String review;
    @JsonView(BasicReview.class)
    private String author;
    @ManyToOne
    @JsonView(Forums.class)
    private Forum forum;

    public Review(String review, String author) {
        this.author = author;
        this.review = review;
    }
}
