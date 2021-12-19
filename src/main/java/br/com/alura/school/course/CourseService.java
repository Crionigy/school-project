package br.com.alura.school.course;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course findByCode(String code){
        try {
            Optional<Course> course = courseRepository.findByCode(code);
            return course.get();
        } catch (Exception e){
            throw new ResponseStatusException(NOT_FOUND, format("Invalid code"));
        }

    }
}
