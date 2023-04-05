package es.ssdd.academia.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Forum {
    private long id;
    private long fk_course;

    public Forum (long fk_course) {
        this.fk_course = fk_course;
    }
}
