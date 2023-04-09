package es.ssdd.academia.restControllers;

import com.fasterxml.jackson.annotation.JsonView;
import es.ssdd.academia.entities.Course;
import es.ssdd.academia.entities.User;
import es.ssdd.academia.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
    interface DetailsCourse extends Course.BasicCourse, Course.Users, User.BasicUser {}

    @Autowired
    UserService userService;

    @JsonView(User.BasicUser.class)
    @GetMapping("/")
    public ResponseEntity getAll() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @JsonView(UserRestController.DetailsCourse.class)
    @GetMapping("/{id}/")
    public ResponseEntity<User> getUser(@PathVariable long id) {
        User tempUser = userService.getOne(id);
        if (tempUser != null) {
            return new ResponseEntity<>(tempUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @JsonView(User.BasicUser.class)
    @DeleteMapping("/{id}/delete/")
    public ResponseEntity<User> deleteUser(@PathVariable int id){
        User tem = userService.deleteUser(id);
        return new ResponseEntity<>(tem, HttpStatus.OK);
    }

    @JsonView(User.BasicUser.class)
    @PostMapping("/addUser/")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User tem = userService.createUser(user);
        return new ResponseEntity<>(tem, HttpStatus.OK);
    }
}
