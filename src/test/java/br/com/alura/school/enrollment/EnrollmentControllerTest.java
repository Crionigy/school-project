package br.com.alura.school.enrollment;

import br.com.alura.school.enrollment.DTO.NewEnrollmentRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class EnrollmentControllerTest {

    private final ObjectMapper jsonMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private EnrollmentService enrollmentService;

    @Test
    void should_retrieve_enrollment_by_id() throws Exception {
        mockMvc.perform(get("/enrollments/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.course_id", is(1)))
                .andExpect(jsonPath("$.user_id", is(1)));
    }

    @Test
    void should_throw_exception_retrieve_enrollment_by_id() throws Exception {
        mockMvc.perform(get("/enrollments/99")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void should_add_new_enrollment() throws Exception {
        NewEnrollmentRequest newEnrollmentRequest = new NewEnrollmentRequest("arthur-1", "arthur");

        mockMvc.perform(post("/enrollments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(newEnrollmentRequest)))
        .andExpect(status().isCreated())
        .andExpect(header().string("Location", "/enrollments/5"));

    }

    @Test
    void should_throw_exception_add_new_enrollment() throws Exception {
        NewEnrollmentRequest newEnrollmentRequest = new NewEnrollmentRequest("java-1", "ana");

        mockMvc.perform(post("/enrollments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMapper.writeValueAsString(newEnrollmentRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(status().reason("User is already enrolled in the informed course"));

    }

    @Test
    void should_retrieve_enrollment_report() throws Exception {

        mockMvc.perform(get("/enrollments/report")
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$[*]").isArray())
                    .andExpect(jsonPath("$[*].email", contains("alex@email.com", "ana@email.com")))
                    .andExpect(jsonPath("$[*].enrollmentsAmount", contains(2,2)));
    }
}
