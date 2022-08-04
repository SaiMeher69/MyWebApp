package com.company.mywebapp;

import com.company.mywebapp.user.User;
import com.company.mywebapp.user.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
    @Autowired private UserRepository repo;

    @Test
    public void testAddNew(){
        User user = new User();
        user.setEmail("hello@gmail.com");
        user.setFirstName("hiiii");
        user.setLastName("hiiiiiiiii");
        user.setPassword("htdsrxmbh");
        user.setEnabled(true);
        User saveduser = repo.save(user);

        Assertions.assertThat(saveduser).isNotNull();
        Assertions.assertThat(saveduser.getId()).isGreaterThan(0);
    }
    @Test
    public void testListAll(){
        Iterable<User> users = repo.findAll();
        Assertions.assertThat(users).hasSizeGreaterThan(0);
        for (User user : users) {
            System.out.println(user);
        }
    }
    
    @Test
    public void testUpdateUser() {
        int userId = 1;
        Optional<User> optionalUser = repo.findById(userId);
        User user = optionalUser.get();
        user.setEmail("ravi.kumar@gmail.com");
        user.setPassword("qwertgfdszxcvbhg432");
        repo.save(user);

        User updatedUser = repo.findById(userId).get();
        Assertions.assertThat(updatedUser.getEmail()).isEqualTo("ravi.kumar@gmail.com");
        Assertions.assertThat(updatedUser.getPassword()).isEqualTo("qwertgfdszxcvbhg432");
    }

    @Test
    public void getUser() {
        Integer userId = 2;
        Optional<User> optionalUser = repo.findById(userId);
        Assertions.assertThat(optionalUser).isPresent();
        System.out.println(optionalUser.get());
    }

    @Test
    public void testDelete() {
        Integer userId = 2;
        repo.deleteById(userId);
        Optional<User> optionalUser = repo.findById(userId);
        Assertions.assertThat(optionalUser).isNotPresent();
    }
}
