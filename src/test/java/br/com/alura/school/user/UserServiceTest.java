package br.com.alura.school.user;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = "classpath:schema.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    void should_retrieve_user_by_username() throws Exception {
        User userSaved = userRepository.save(new User("ana", "ana@email.com"));
        String username = "ana";
        User userFound = userService.findByUsername(username);

        assertThat(userSaved.getUsername()).isEqualTo(userFound.getUsername());
    }

    @Test
    void should_retrieve_all_courses() throws Exception {
        List<User> usersSaved = new ArrayList<User>();
        usersSaved.add(userRepository.save(new User("alex", "alex@email.com")));
        usersSaved.add(userRepository.save(new User("ana", "ana@email.com")));

        List<User> usersFound = userService.findAll();

        usersFound.forEach(user -> user.setEnrollments(null));

        assertThat(usersFound, contains(hasProperty("id"),
                hasProperty("username"),
                hasProperty("email"),
                hasProperty("enrollments", is(null))));

    }
}
