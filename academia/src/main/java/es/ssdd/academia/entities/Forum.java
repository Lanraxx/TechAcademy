package es.ssdd.academia.entities;

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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "forum")
    List<Comment> commentList = new ArrayList<>();

    //@OneToOne(cascade = CascadeType.ALL)
    /*private long fk_course;

    public Forum (long fk_course) {
        this.fk_course = fk_course;
    }*/
}
