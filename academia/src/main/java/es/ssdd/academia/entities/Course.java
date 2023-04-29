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
public class Course {
    public interface BasicCourse{}
    public interface Users{}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(BasicCourse.class)
    private long id;
    @OneToOne(cascade=CascadeType.ALL)
    @JsonView(BasicCourse.class)
    private Forum forum;
    @JsonView(BasicCourse.class)
    private String title;
    @JsonView(BasicCourse.class)
    private String price;
    @JsonView(BasicCourse.class)
    private String duration;
    @Column(length = 10000)
    @JsonView(BasicCourse.class)
    private String description;
    @JsonView(BasicCourse.class)
    private String urlImage;
    @ManyToMany(mappedBy = "enrolledCourses", cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JsonView(Users.class)
    private List<User> userList = new ArrayList<>();


    public Course(String title, String price, String duration, String description, String urlImage){
        this.title = title;
        this.price = price;
        this.duration = duration;
        this.description = description;
        this.urlImage = urlImage;
    }

}
