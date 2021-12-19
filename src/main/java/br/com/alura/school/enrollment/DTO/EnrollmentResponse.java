package br.com.alura.school.enrollment.DTO;

import br.com.alura.school.course.Course;
import br.com.alura.school.enrollment.Enrollment;
import br.com.alura.school.user.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Calendar;
import java.util.Optional;

public class EnrollmentResponse {

    @JsonProperty
    private final Long course_id;

    @JsonProperty
    private final Long user_id;

    @JsonProperty
    private final Calendar dtEnrollment;


    public EnrollmentResponse(Enrollment enrollment) {
        this.course_id = Optional.of(enrollment.getCourse()).map(this::getCourse_id).orElse(null);
        this.user_id = Optional.of(enrollment.getUser()).map(this::getUser_id).orElse(null);
        this.dtEnrollment = enrollment.getDtEnrollment();
    }

    public Long getCourse_id(Course course) {
        return course.getId();
    }

    public Long getUser_id(User user) {
        return user.getId();
    }
}
