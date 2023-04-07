package es.ssdd.academia.services;

import es.ssdd.academia.entities.Course;
import es.ssdd.academia.entities.Forum;
import es.ssdd.academia.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class CourseService {

    @Autowired
    UserService userService;
    @Autowired
    ForumService forumService;

    private Map<Long, Course> mapCourses = new ConcurrentHashMap<>();
    private AtomicLong id = new AtomicLong();

    public Collection<Course> getAll() {
        return mapCourses.values();
    }

    public Course createCourse(Course course){
        long tem = id.incrementAndGet();
        course.setId(tem);
        mapCourses.put(tem, course);
        Forum forum = new Forum(course.getId());
        forumService.createForum(forum);
        course.setFk_forum(forum.getId());
        return course;
    }

    public Course getOne(long id) {
        return mapCourses.get(id);
    }

    public Course modifyCourse (long id, Course newCourse) {
        Course course = mapCourses.get(id);

        course.setTitle(newCourse.getTitle());
        course.setPrice(newCourse.getPrice());
        course.setDuration(newCourse.getDuration());
        course.setDescription(newCourse.getDescription());
        course.setUrlImage(newCourse.getUrlImage());

        mapCourses.put(id, course);
        return newCourse;
    }

    public Course deleteCourse(long id) {
        for (Map.Entry<Long, Course> entry : mapCourses.entrySet()) {
            Course c = entry.getValue();
            if (id == c.getId()){
                mapCourses.remove(id);
                return c;
            }
        }
        return null;
    }

    public void deleteUser(long id) {
        for (Map.Entry<Long, Course> entry : mapCourses.entrySet()) {
            Course c = entry.getValue();
            List<User> users = c.getUserList();
            Iterator<User> iterator = users.iterator();
            while (iterator.hasNext()) {
                User u = iterator.next();
                if (u.getId() == id) {
                    iterator.remove();
                }
            }
            /*for (User u : users) {
                if (u.getId() == id) {
                    c.getUserList().remove(u);
                }
            }*/
        }
    }
}
