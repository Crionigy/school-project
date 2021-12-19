package br.com.alura.school.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void should_retrieve_user_by_username() {
        String username = "alex";
        Optional<User> user =  userRepository.findByUsername(username);
        assertNotNull(user);
        assertEquals(username, user.get().getUsername());
    }

}
