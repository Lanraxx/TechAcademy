package es.ssdd.academia.restControllers;

import com.fasterxml.jackson.annotation.JsonView;
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

    @Autowired
    UserService userService;

    @Autowired
    CourseService courseService;

    @JsonView(User.BasicUser.class)
    @GetMapping("/")
    public ResponseEntity getAll() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @JsonView(User.BasicUser.class)
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
        User tem = userService.deleteUser(id);
        courseService.deleteUser(id);
        return new ResponseEntity<>(tem, HttpStatus.OK);
    }

    @PostMapping("/addUser/")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User tem = userService.createUser(user);
        return new ResponseEntity<>(tem, HttpStatus.OK);
    }

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

