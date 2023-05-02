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
@RequestMapping("/api/users")
public class UserRestController {

    interface DetailsUsers extends User.BasicUser, User.Courses, Course.BasicCourse { }

    @Autowired
    UserService userService;

    @Autowired
    CourseService courseService;


    @JsonView(User.BasicUser.class)
    @GetMapping("/")
    public ResponseEntity getAll() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @JsonView(DetailsUsers.class)
    @GetMapping("/{id}/")
    public ResponseEntity getUser(@PathVariable long id) {
        User temUser = userService.getOne(id);
        if (temUser != null) {
            return new ResponseEntity<>(temUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @JsonView(User.BasicUser.class)
    @DeleteMapping("/{id}/delete/")
    public ResponseEntity<User> deleteUser(@PathVariable long id){
        User tem = userService.getOne(id);
        if (tem != null) {
            userService.deleteUser(id);
            courseService.deleteUser(id);
            return new ResponseEntity<>(tem, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    }

    @PostMapping("/addUser/")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User tem = userService.createUser(user);
        return new ResponseEntity<>(tem, HttpStatus.OK);
    }

    @JsonView(User.BasicUser.class)
    @PutMapping("/{id}/editUser/")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable long id){
        User tempUser = userService.getOne(id);
        if (tempUser != null) {
            userService.modifyUser(id, user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

