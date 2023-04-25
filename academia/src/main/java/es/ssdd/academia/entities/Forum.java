package es.ssdd.academia.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Forum {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //@OneToOne(cascade = CascadeType.ALL)
    private long fk_course;

    public Forum (long fk_course) {
        this.fk_course = fk_course;
    }
}
