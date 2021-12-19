package br.com.alura.school.enrollment;

import br.com.alura.school.course.Course;
import br.com.alura.school.course.CourseService;
import br.com.alura.school.enrollment.DTO.NewEnrollmentRequest;
import br.com.alura.school.user.User;
import br.com.alura.school.user.UserService;
import org.springframework.stereotype.Service;

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
        Course course = courseService.findByCode(newEnrollment.getCode());
        Enrollment enrollment = enrollmentRepository.save(new Enrollment(user, course));

        return enrollment;
    }

}
