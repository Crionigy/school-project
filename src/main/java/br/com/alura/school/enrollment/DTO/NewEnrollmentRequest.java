package br.com.alura.school.enrollment.DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class NewEnrollmentRequest {

    @Size(max=10)
    @NotBlank
    private String code;

    @Size(max=20)
    @NotBlank
    private String username;

    public NewEnrollmentRequest(String code, String username) {
        this.code = code;
        this.username = username;
    }

    public String getCode() {
        return code;
    }

    public String getUsername() {
        return username;
    }
}
