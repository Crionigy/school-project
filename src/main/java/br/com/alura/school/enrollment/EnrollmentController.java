package br.com.alura.school.enrollment;

import br.com.alura.school.enrollment.DTO.EnrollmentReport;
import br.com.alura.school.enrollment.DTO.EnrollmentResponse;
import br.com.alura.school.enrollment.DTO.NewEnrollmentRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.*;

@RestController
class EnrollmentController {

    private final EnrollmentService enrollmentService;

    private final EnrollmentRepository enrollmentRepository;

    EnrollmentController(EnrollmentService enrollmentService, EnrollmentRepository enrollmentRepository) {
        this.enrollmentService = enrollmentService;
        this.enrollmentRepository = enrollmentRepository;
    }

    @GetMapping("/enrollments/report")
    ResponseEntity<List<EnrollmentReport>> enrollmentReport() {
        try {
            List<EnrollmentReport> reports = enrollmentService.enrollmentReport();
            return ResponseEntity.ok(reports);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(NO_CONTENT, e.getReason());
        }
    }

    @GetMapping("/enrollments/{id}")
    ResponseEntity<EnrollmentResponse> enrollmentById(@PathVariable("id") Long id) {
        Enrollment enrollment = (enrollmentRepository.findById(id)).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, format("Enrollment with id %s not found", id)));
        return ResponseEntity.ok(new EnrollmentResponse(enrollment));
    }

    @PostMapping("/enrollments")
    ResponseEntity<Void> newEnrollment(@RequestBody @Valid NewEnrollmentRequest newEnrollmentRequest) {
        try {
            Enrollment enrollment = enrollmentService.addNewEnrollment(newEnrollmentRequest);
            URI location = URI.create(format("/enrollments/%s", enrollment.getId()));
            return ResponseEntity.created(location).build();
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(BAD_REQUEST, e.getReason());
        }
    }

}
