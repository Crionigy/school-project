package br.com.alura.school.course;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void should_retrieve_course_by_code() {
        String code = "java-1";
        Optional<Course> user =  courseRepository.findByCode(code);
        assertNotNull(user);
        assertEquals(code, user.get().getCode());
    }

}
