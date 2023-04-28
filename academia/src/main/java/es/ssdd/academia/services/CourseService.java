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

    /*private Map<Long, Course> mapCourses = new ConcurrentHashMap<>();
    private AtomicLong id = new AtomicLong();*/

    @Autowired
    CourseRepository courseRepository;

    public Collection<Course> getAll() {
        //return mapCourses.values();
        return courseRepository.findAll();
    }

    public Course createCourse(Course course){
        //long tem = id.incrementAndGet();
        //course.setId(tem);
        //mapCourses.put(tem, course);
        courseRepository.save(course);
        Forum forum = new Forum();
        forumService.createForum(forum);
        course.setForum(forum);
        return course;
    }

    /*public Course getOne(long id) {
        return mapCourses.get(id);
    }*/

    public Course getOne(long id){
        /*UNA OPCION --> valor retorno seria Optional<Course>
        Optional<Course> findCourse = courseRepository.findById(id);
        return findCourse;*/
        Optional<Course> findCourse = courseRepository.findById(id);
        if(findCourse.isPresent()) {
            Course course = findCourse.get();
            return course;
        }
        return null;
    }

    /*public Course getByName(String name) {
        return courseRepository.findByName(name);
    }*/

    public Course modifyCourse (long id, Course newCourse) {
        //Course course = mapCourses.get(id);
        Optional<Course> findCourse = courseRepository.findById(id);
        if(findCourse.isPresent()) {
            Course course = findCourse.get();

            course.setTitle(newCourse.getTitle());
            course.setPrice(newCourse.getPrice());
            course.setDuration(newCourse.getDuration());
            course.setDescription(newCourse.getDescription());
            course.setUrlImage(newCourse.getUrlImage());
        }

        /*course.setTitle(newCourse.getTitle());
        course.setPrice(newCourse.getPrice());
        course.setDuration(newCourse.getDuration());
        course.setDescription(newCourse.getDescription());
        course.setUrlImage(newCourse.getUrlImage());*/

        return newCourse;
    }

    /*public Course deleteCourse(long id) {
        for (Map.Entry<Long, Course> entry : mapCourses.entrySet()) {
            Course c = entry.getValue();
            if (id == c.getId()){
                forumService.deleteForum(mapCourses.get(id).getFk_forum());
                mapCourses.remove(id);
                Collection<User> users = userService.getAll();
                Iterator<User> iterator = users.iterator();
                while (iterator.hasNext()) {
                    User u = iterator.next();
                    if(u.getEnrolledCourses().contains(c)) {
                        u.getEnrolledCourses().remove(c);
                    }
                }
                return c;
            }
        }
        return null;
    }*/

    public Course deleteCourse(long id) {
        Optional<Course> findCourse = courseRepository.findById(id);
        if (findCourse.isPresent()) {
            Course c = findCourse.get();
            courseRepository.delete(c);
            //forumService.deleteForum(c.getFk_forum());
            Collection<User> users = userService.getAll();
            Iterator<User> iterator = users.iterator();
            while ((iterator.hasNext())){
                User u = iterator.next();
                if(u.getEnrolledCourses().contains(c)) {
                    u.getEnrolledCourses().remove(c);
                }
            }
            return c;
        }
        return null;
    }

    /*public void deleteUser(long id) {
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
        }
    }*/

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
}
