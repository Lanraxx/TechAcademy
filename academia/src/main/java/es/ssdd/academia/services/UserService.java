package es.ssdd.academia.services;

import es.ssdd.academia.entities.User;
import es.ssdd.academia.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    public User createUser(User user){
        userRepository.save(user);
        return user;
    }

    public Collection<User> getAll() {
        return userRepository.findAll();
    }

    public User getOne(long id) {
        Optional<User> findUser = userRepository.findById(id);
        if (findUser.isPresent()) {
            User user = findUser.get();
            return user;
        }
        return null;
    }


    public List<User> getUserListOfACourse(long id) {
        List<User> userList = new ArrayList<>();
        List<User> userRepo = userRepository.findAll();
        for (User u: userRepo) {
            for (int i = 0; i < userRepo.size(); i++) {
                if (id == u.getEnrolledCourses().get(i).getId()) {
                    userList.add(u);
                }
            }
        }
        return userList;
    }

    public User deleteUser(long id) {
        Optional<User> findUser = userRepository.findById(id);
        if (findUser.isPresent()) {
            User user = findUser.get();
            userRepository.delete(user);
            return user;
        }
        return null;
    }

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
