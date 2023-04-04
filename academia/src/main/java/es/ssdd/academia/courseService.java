package es.ssdd.academia;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class courseService {

    private Map<Integer, Course> mapCourses = new HashMap<>();
    private static int id = 0;

    public Course createCourse(Course course){
        id++;
        int key = id;
        course.setId(key);
        mapCourses.put(key, course);
        return course;
    }

    /*public List<Course> getAll() {
        List<Course> list = new ArrayList<>();
        for (int i = 0; i < id; i++) {
            Course c = mapCourses.get(i);
            list.add(c);
        }
        return list;
    }*/
    public Map<Integer, Course> getMap(){
        return mapCourses;
    }
}
