package es.ssdd.academia.services;

import es.ssdd.academia.entities.Course;
import es.ssdd.academia.entities.Forum;
import es.ssdd.academia.entities.User;
import es.ssdd.academia.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CourseService {

    @Autowired
    UserService userService;
    @Autowired
    ForumService forumService;

    @Autowired
    CourseRepository courseRepository;

    public Collection<Course> getAll() {
        return courseRepository.findAll();
    }

    public Course createCourse(Course course){
        Forum forum = new Forum();
        course.setForum(forum);
        courseRepository.save(course);
        return course;
    }

    public Course getOne(long id){
        Optional<Course> findCourse = courseRepository.findById(id);
        if(findCourse.isPresent()) {
            Course course = findCourse.get();
            return course;
        }
        return null;
    }

    public Course modifyCourse (long id, Course newCourse) {
        Optional<Course> findCourse = courseRepository.findById(id);
        if(findCourse.isPresent()) {
            newCourse.setForum(findCourse.get().getForum());
            newCourse.setId(id);
            courseRepository.save(newCourse);
            return newCourse;
        }
        return null;
    }

    public Course deleteCourse(long id) {
        Optional<Course> findCourse = courseRepository.findById(id);
        if (findCourse.isPresent()) {
            Course c = findCourse.get();

            Collection<User> users = userService.getAll();
            Iterator<User> iterator = users.iterator();
            while ((iterator.hasNext())){
                User u = iterator.next();
                if(u.getEnrolledCourses().contains(c)) {
                    u.getEnrolledCourses().remove(c);
                }
            }
            courseRepository.delete(c);
            return c;
        }
        return null;
    }

    public void deleteUser(long id) {
        List<Course> listCourses = courseRepository.findAll();
        for (Course c: listCourses) {
            List<User> users = c.getUserList();
            Iterator<User> iterator = users.iterator();
            while (iterator.hasNext()) {
                User u = iterator.next();
                if (u.getId() == id) {
                    iterator.remove();
                }
            }
        }
    }

    public List<Course> filterCourses(String price) {
        return courseRepository.findByPrice(price);
    }
}
