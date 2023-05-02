package es.ssdd.academia.entities;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Forum {
    public interface BasicForum{}
    public interface Comments{}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(BasicForum.class)
    private long id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "forum")
    @JsonView(Comments.class)
    List<Review> reviewList = new ArrayList<>();

    //@OneToOne(cascade = CascadeType.ALL)
    /*private long fk_course;

    public Forum (long fk_course) {
        this.fk_course = fk_course;
    }*/
}
