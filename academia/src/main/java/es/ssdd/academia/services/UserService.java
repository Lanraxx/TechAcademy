package es.ssdd.academia.services;

import es.ssdd.academia.entities.User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserService {
    private Map<Long, User> usersMap = new ConcurrentHashMap<>();
    private AtomicLong id = new AtomicLong();


    public UserService () {
        this.createUser(new User("Pepe", "pepejaun@gmail.com", "xxxxx"));
        this.createUser(new User("Pepa", "junjwenc@yahoo.es", "12234"));
    }

    public User createUser(User user){
        long tem = id.incrementAndGet();
        user.setId(tem);
        usersMap.put(tem, user);
        return user;
    }
}
