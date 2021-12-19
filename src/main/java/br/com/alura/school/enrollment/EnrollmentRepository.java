package br.com.alura.school.enrollment;

import org.springframework.data.jpa.repository.JpaRepository;

interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
}
