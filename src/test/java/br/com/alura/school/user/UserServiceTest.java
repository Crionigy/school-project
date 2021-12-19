package br.com.alura.school.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class UserServiceTest {
    
    @Autowired
    private UserService userService;
    
    private List<User> listOfUsers;

    @BeforeEach
    void setUp() {
        listOfUsers = userService.findAll();
    }

    @Test
    void should_retrieve_all_users() throws Exception {
        List<User> listOfUsersFound = userService.findAll();
        assertEquals(this.listOfUsers, listOfUsersFound);
    }

    @Test
    void should_retrieve_user_by_username() throws Exception {
        String username = "ana";
        User userFound = userService.findByUsername(username);

        assertThat(username).isEqualTo(userFound.getUsername());
    }

    @Test
    void should_throw_exception_retrieve_user_by_username() throws Exception {
        String username = "fulano";
        assertThrows(ResponseStatusException.class,
                () -> userService.findByUsername(username));
    }
}
