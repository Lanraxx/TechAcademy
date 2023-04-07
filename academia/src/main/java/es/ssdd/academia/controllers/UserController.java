package es.ssdd.academia.controllers;

import es.ssdd.academia.entities.Course;
import es.ssdd.academia.entities.User;
import es.ssdd.academia.services.CourseService;
import es.ssdd.academia.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    CourseService courseService;

    @PostConstruct
    public void UserController () {

        List<User> course1List = courseService.getOne(1).getUserList();
        List<User> course2List = courseService.getOne(2).getUserList();

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

        List<Course> enrolledCoursesUser1 = new ArrayList<>();
        List<Course> enrolledCoursesUser2 = new ArrayList<>();
        List<Course> enrolledCoursesUser3 = new ArrayList<>();
        List<Course> enrolledCoursesUser4 = new ArrayList<>();
        List<Course> enrolledCoursesUser5 = new ArrayList<>();

//        enrolledCoursesUser1.add(courseService.getOne(1));
//        enrolledCoursesUser1.add(courseService.getOne(2));
//
//        enrolledCoursesUser2.add(courseService.getOne(1));
//
//        enrolledCoursesUser3.add(courseService.getOne(1));
//        enrolledCoursesUser3.add(courseService.getOne(2));
//
//        enrolledCoursesUser4.add(courseService.getOne(2));
//
//        enrolledCoursesUser5.add(courseService.getOne(2));

        u1.setEnrolledCourses(enrolledCoursesUser1);
        u2.setEnrolledCourses(enrolledCoursesUser2);
        u3.setEnrolledCourses(enrolledCoursesUser3);
        u4.setEnrolledCourses(enrolledCoursesUser4);
        u5.setEnrolledCourses(enrolledCoursesUser5);

        userService.createUser(u1);
        userService.createUser(u2);
        userService.createUser(u3);
        userService.createUser(u4);
        userService.createUser(u5);
    }

    @GetMapping("/users/")      //users list
    public String users(Model model) {
        model.addAttribute("users", userService.getAll());
        return "users";
    }

    @GetMapping("/users/{id}/delete/")      //delete one user
    public String deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
        return "redirect:/users/";
    }
}
