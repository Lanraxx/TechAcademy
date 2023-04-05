package es.ssdd.academia.controllers;

import es.ssdd.academia.entities.User;
import es.ssdd.academia.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @PostConstruct
    public void UserController () {
        ArrayList<User> course1List = new ArrayList<>();
        ArrayList<User> course2List = new ArrayList<>();

        User u1 = new User("Nico", "nrodriguezu@gmail.com", "xxxxx");
        User u2 = new User("Marta", "mmrtta@yahoo.es", "1234");
        User u3 = new User("Álvaro", "alpasc8@gmail.com", "2905");
        User u4 = new User("Jiajie", "djjj@yahoo.es", "jiajie");
        User u5 = new User("Gonzalo", "gonzarico@gamil.com", "9090");

        course1List.add(u1);
        course1List.add(u2);
        course1List.add(u3);

        course2List.add(u4);
        course2List.add(u5);
        course2List.add(u1);
        course2List.add(u3);

        userService.createUser(u1);
        userService.createUser(u2);
        userService.createUser(u3);
        userService.createUser(u4);
        userService.createUser(u5);
    }
}
