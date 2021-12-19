package br.com.alura.school.enrollment;

import br.com.alura.school.course.Course;
import br.com.alura.school.course.CourseService;
import br.com.alura.school.enrollment.DTO.NewEnrollmentRequest;
import br.com.alura.school.user.User;
import br.com.alura.school.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final CourseService courseService;
    private final UserService userService;

    EnrollmentService(EnrollmentRepository enrollmentRepository, CourseService courseService, UserService userService) {
        this.enrollmentRepository = enrollmentRepository;
        this.courseService = courseService;
        this.userService = userService;
    }

    Enrollment addNewEnrollment(NewEnrollmentRequest newEnrollment) {
        User user = userService.findByUsername(newEnrollment.getUsername());
        if(!validateIfEnrollmentExists(user.getId(), newEnrollment.getCode())) {
            Course course = courseService.findByCode(newEnrollment.getCode());
            Enrollment enrollment = enrollmentRepository.save(new Enrollment(user, course));

            return enrollment;
        } else {
            throw new ResponseStatusException(BAD_REQUEST, "User is already enrolled in the informed course");
        }
    }

    private boolean validateIfEnrollmentExists(Long id, String code) {
        List<Enrollment> enrollments = new ArrayList<Enrollment>();
        enrollments.addAll(enrollmentRepository.findByUser(new User(id)));
        return enrollments.stream().anyMatch(e -> e.getCourse().getCode().equals(code));
    }


}
