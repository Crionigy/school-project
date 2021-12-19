package br.com.alura.school.course;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
public class CourseServiceTest {

    @Autowired
    private CourseService courseService;

    @Test
    void should_retrieve_course_by_code() throws Exception {
        String code = "java-1";
        Course courseFound = courseService.findByCode(code);

        assertThat(code).isEqualTo(courseFound.getCode());
    }

    @Test
    void should_throw_exception_retrieve_course_by_code() throws Exception {
        String code = "java-5";
        assertThrows(ResponseStatusException.class,
                () -> courseService.findByCode(code));
    }

}
