package es.ssdd.academia.services;

import es.ssdd.academia.entities.User;
import es.ssdd.academia.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    /*private Map<Long, User> usersMap = new ConcurrentHashMap<>();
    private AtomicLong id = new AtomicLong();*/

    @Autowired
    UserRepository userRepository;


    public User createUser(User user){
        /*long tem = id.incrementAndGet();
        user.setId(tem);
        usersMap.put(tem, user);*/
        userRepository.save(user);
        return user;
    }

    public Collection<User> getAll() {
        //return usersMap.values();
        return userRepository.findAll();
    }

    public User getOne(long id) {
        //return usersMap.get(id);
        Optional<User> findUser = userRepository.findById(id);
        if (findUser.isPresent()) {
            User user = findUser.get();
            return user;
        }
        return null;
    }

    /*public List<User> getUserListOfACourse(long id) {
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
    }*/

    public List<User> getUserListOfACourse(long id) {
        List<User> userList = new ArrayList<>();
        List<User> userRepo = userRepository.findAll();
        for (User u: userRepo) {
            for (int i = 0; i < 0; i++) {
                if (id == u.getEnrolledCourses().get(i).getId()) {
                    userList.add(u);
                }
            }
        }
        return userList;
    }

    /*public User deleteUser(long id) {
        for (Map.Entry<Long, User> entry : usersMap.entrySet()) {
            User u = entry.getValue();
            if (id == u.getId()){
                usersMap.remove(id);
                return u;
            }
        }
        return null;
    }*/

    public User deleteUser(long id) {
        Optional<User> findUser = userRepository.findById(id);
        if (findUser.isPresent()) {
            User user = findUser.get();
            userRepository.delete(user);                    //En lugar detodo eso se podría hacer directamente con deleteById
            return user;
        }
        return null;
    }

    /*public User modifyUser (long id, User newUser) {
        User user = usersMap.get(id);

        user.setUsername(newUser.getUsername());
        user.setEmail(newUser.getEmail());
        user.setPassword(newUser.getPassword());
        user.setEnrolledCourses(newUser.getEnrolledCourses());

        return user;
    }*/

    public User modifyUser(long id, User newUser) {
        Optional<User> findUser = userRepository.findById(id);
        if(findUser.isPresent()) {
            newUser.setId(id);
            userRepository.save(newUser);
            return newUser;
        }
        return null;
    }

}
