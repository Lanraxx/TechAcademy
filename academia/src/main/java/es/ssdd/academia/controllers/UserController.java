package es.ssdd.academia.controllers;

import es.ssdd.academia.entities.Course;
import es.ssdd.academia.entities.User;
import es.ssdd.academia.services.CourseService;
import es.ssdd.academia.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    CourseService courseService;

    @PostConstruct
    public void UserController () {
        List<User> course1List = courseService.getOne(1).getUserList();
        List<User> course2List = courseService.getOne(2).getUserList();
        List<User> course3List = courseService.getOne(3).getUserList();

        User u1 = new User("Nico", "nrodriguezu@gmail.com", "xxxxxx");
        User u2 = new User("Marta", "mmrtta@yahoo.es", "123456");
        User u3 = new User("Álvaro", "alpasc8@gmail.com", "290576");
        User u4 = new User("Jiajie", "djjj@yahoo.es", "jiajie");
        User u5 = new User("Gonzalo", "gonzarico@gmail.com", "909090");
        User u6 = new User("Juan Manuel", "jmanuelc@gmail.com", "jnmnl6");

        //Add users to course1
        course1List.add(u1);
        course1List.add(u2);
        course1List.add(u3);

        //Add users to course2
        course2List.add(u4);
        course2List.add(u5);
        course2List.add(u1);
        course2List.add(u3);

        //Add users to course3
        course3List.add(u6);

        List<Course> enrolledCoursesUser1 = new ArrayList<>();
        List<Course> enrolledCoursesUser2 = new ArrayList<>();
        List<Course> enrolledCoursesUser3 = new ArrayList<>();
        List<Course> enrolledCoursesUser4 = new ArrayList<>();
        List<Course> enrolledCoursesUser5 = new ArrayList<>();
        List<Course> enrolledCoursesUser6 = new ArrayList<>();

        //Add to each user the courses in which they are enrolled
        enrolledCoursesUser1.add(courseService.getOne(1));
        enrolledCoursesUser1.add(courseService.getOne(2));

        enrolledCoursesUser2.add(courseService.getOne(1));

        enrolledCoursesUser3.add(courseService.getOne(1));
        enrolledCoursesUser3.add(courseService.getOne(2));

        enrolledCoursesUser4.add(courseService.getOne(2));

        enrolledCoursesUser5.add(courseService.getOne(2));

        enrolledCoursesUser6.add(courseService.getOne(3));

        u1.setEnrolledCourses(enrolledCoursesUser1);
        u2.setEnrolledCourses(enrolledCoursesUser2);
        u3.setEnrolledCourses(enrolledCoursesUser3);
        u4.setEnrolledCourses(enrolledCoursesUser4);
        u5.setEnrolledCourses(enrolledCoursesUser5);
        u6.setEnrolledCourses(enrolledCoursesUser6);

        //Add users to map
        userService.createUser(u1);
        userService.createUser(u2);
        userService.createUser(u3);
        userService.createUser(u4);
        userService.createUser(u5);
        userService.createUser(u6);
    }

    @GetMapping("/")                          //users list
    public String users(Model model) {
        model.addAttribute("users", userService.getAll());
        return "users";
    }

    @GetMapping("/{id}/delete/")                //Delete one user
    public String deleteUser(Model model, @PathVariable long id) {
        courseService.deleteUser(id);
        userService.deleteUser(id);
        return "redirect:/users/";
    }

    @GetMapping("/addUser/")                      //Show form of add user
    public String addUser() {
        return "register";
    }

    @PostMapping("/")
    public String createUser(Model model, @RequestParam String username, @RequestParam String email, @RequestParam String password) {

        User user = new User(username, email, password);
        userService.createUser(user);
        model.addAttribute("users", userService.getAll());
        return "users";
    }

    @GetMapping("/{id}/editUser/")
    public String updateUser(Model model, @PathVariable long id) {
        model.addAttribute("user", userService.getOne(id));
        model.addAttribute("courses", courseService.getAll());
        return "updateUser";
    }

    @PostMapping("/{id}/editUser")
    public String updateUser(@PathVariable long id, @RequestParam String username, @RequestParam String email,
                             @RequestParam String password, @RequestParam(name="selectedObjects", required = false) List<Long> selectedObjects) {

        List<Course> selectCourses = new ArrayList<>();
        if(selectedObjects != null) {
            for (Long idCheckbox : selectedObjects) {
                selectCourses.add(courseService.getOne(idCheckbox));
                if (!(courseService.getOne(idCheckbox).getUserList().contains(userService.getOne(id)))) {
                    courseService.getOne(idCheckbox).getUserList().add(userService.getOne(id));
                }
            }
        }

        User newUser = new User(username, email, password);
        newUser.setEnrolledCourses(selectCourses);
        userService.modifyUser(id, newUser);

        return "redirect:/users/";
    }
}
