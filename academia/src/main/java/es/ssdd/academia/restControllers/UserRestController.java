package es.ssdd.academia.restControllers;

import es.ssdd.academia.entities.User;
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

    @GetMapping("/users/")
    public ResponseEntity getAll() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}/delete/")
    public ResponseEntity<User> deleteUser(@PathVariable int id){
        User tem = userService.deleteUser(id);
        return new ResponseEntity<>(tem, HttpStatus.OK);
    }
}
