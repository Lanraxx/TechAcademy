package es.ssdd.academia.entities;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    public interface BasicCourse{}

    public interface Users{}

    @JsonView(BasicCourse.class)
    private long id;
    @JsonView(BasicCourse.class)
    private long fk_forum;
    @JsonView(BasicCourse.class)
    private String title;
    @JsonView(BasicCourse.class)
    private String price;
    //private boolean certificate;         //Si ofrece un certificado o no
    @JsonView(BasicCourse.class)
    private int duration;
    @JsonView(BasicCourse.class)
    private String description;
    @JsonView(BasicCourse.class)
    private String urlImage;
    @JsonView(Users.class)
    private List<User> userList = new ArrayList<>();


    public Course(String title, String price, int duration, String description, String urlImage){
        this.title = title;
        this.price = price;
        this.duration = duration;
        this.description = description;
        this.urlImage = urlImage;
    }

}