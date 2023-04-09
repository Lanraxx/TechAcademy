package es.ssdd.academia.services;

import es.ssdd.academia.entities.Course;
import es.ssdd.academia.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserService {

    private Map<Long, User> usersMap = new ConcurrentHashMap<>();
    private AtomicLong id = new AtomicLong();

    public User createUser(User user){
        long tem = id.incrementAndGet();
        user.setId(tem);
        usersMap.put(tem, user);
        return user;
    }

    public Collection<User> getAll() {
        return usersMap.values();
    }
    public User getOne(long id) {return usersMap.get(id);}

    public List<User> getUserListOfACourse(long id) {
        List<User> userList = new ArrayList<>();
        for (Map.Entry<Long, User> entry : usersMap.entrySet()) {
            User user = entry.getValue();
            for(int i = 0; i < 0; i++) {
                if (id == user.getEnrolledCourses().get(i).getId()) {
                    userList.add(user);
                }
            }
        }
        return userList;
    }

    public User deleteUser(long id) {
        for (Map.Entry<Long, User> entry : usersMap.entrySet()) {
            User u = entry.getValue();
            if (id == u.getId()){
                usersMap.remove(id);
                return u;
            }
        }
        return null;
    }
}
