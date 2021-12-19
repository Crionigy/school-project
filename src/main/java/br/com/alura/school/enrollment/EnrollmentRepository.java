package br.com.alura.school.enrollment;

import br.com.alura.school.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    List<Enrollment> findByUser(User user);

    Long countByUser(User user);

}
