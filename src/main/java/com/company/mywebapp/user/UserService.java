package com.company.mywebapp.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class UserService {
    @Autowired private UserRepository repo;
    public List<User> listAll() {
        return (List<User>) repo.findAll();
    }

    public void save(User user) {
        repo.save(user);
    }

    public User get(Integer id) throws UserNotFoundExcepion {
        Optional<User> result = repo.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new UserNotFoundExcepion("could not find any users with the ID " + id);
    }

    public void delete(Integer id) throws UserNotFoundExcepion{
        long count = repo.countById(id);
        if(Objects.isNull(count) || count == 0){
            throw new UserNotFoundExcepion("Could not find any users with ID " + id);
        }
        repo.deleteById(id);
    }
}
