package es.ssdd.academia.restControllers;

import com.fasterxml.jackson.annotation.JsonView;
import es.ssdd.academia.entities.Course;
import es.ssdd.academia.entities.User;
import es.ssdd.academia.services.CourseService;
import es.ssdd.academia.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserRestController {

    @Autowired
    UserService userService;

    @Autowired
    CourseService courseService;

    @JsonView(User.BasicUser.class)
    @GetMapping("/users/")
    public ResponseEntity getAll() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @JsonView(User.BasicUser.class)
    @DeleteMapping("/users/{id}/delete/")
    public ResponseEntity<User> deleteUser(@PathVariable int id){
        User tem = userService.deleteUser(id);
        courseService.deleteUser(id);
        return new ResponseEntity<>(tem, HttpStatus.OK);
    }

    @PostMapping("/addUser/")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User tem = userService.createUser(user);
        return new ResponseEntity<>(tem, HttpStatus.OK);
    }
}
