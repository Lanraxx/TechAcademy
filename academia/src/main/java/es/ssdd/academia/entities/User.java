package es.ssdd.academia.entities;

import com.fasterxml.jackson.annotation.JsonView;
import es.ssdd.academia.entities.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    public interface BasicUser{}
    public interface Courses{}

    @JsonView(BasicUser.class)
    private long id;
    @JsonView(BasicUser.class)
    private String username;
    @JsonView(BasicUser.class)
    private String email;
    @JsonView(BasicUser.class)
    private String password;
    @JsonView(Courses.class)
    private List<Course> enrolledCourses = new ArrayList<>();

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}