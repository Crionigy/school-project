package br.com.alura.school.enrollment;

import javax.persistence.*;

import br.com.alura.school.course.Course;
import br.com.alura.school.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Calendar;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "user", "course"})
public class Enrollment {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_enrollment", nullable = false, updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", nullable = false, updatable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_course", nullable = false, updatable = false)
    private Course course;

    @Column(name = "dt_enrollment", nullable = false, updatable = false)
    private Calendar dtEnrollment;

    public Enrollment() {
    }

    public Enrollment(User user, Course course) {
        this.user = user;
        this.course = course;
        this.dtEnrollment = Calendar.getInstance();
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Course getCourse() {
        return course;
    }

    public Calendar getDtEnrollment() {
        return dtEnrollment;
    }

}
