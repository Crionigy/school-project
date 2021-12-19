package br.com.alura.school.enrollment;

import br.com.alura.school.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class EnrollmentRepositoryTest {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Test
    void should_find_by_user(){
        User user = new User(1L);
        List<Enrollment> enrollments = enrollmentRepository.findByUser(user);
        assertNotNull(enrollments);
        assertEquals(enrollments.get(0).getUser().getId(), user.getId());
    }

    @Test
    void should_count_by_user(){
        User user = new User(1L);
        Long numberOfEnrollments = enrollmentRepository.countByUser(user);
        assertNotNull(numberOfEnrollments);
        assertEquals(2L, numberOfEnrollments);
    }
}
