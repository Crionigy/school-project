package br.com.alura.school.enrollment.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EnrollmentReport {

    @JsonProperty
    private String email;

    @JsonProperty
    private Long enrollmentsAmount;

    public EnrollmentReport(String email, Long enrollmentsAmount) {
        this.email = email;
        this.enrollmentsAmount = enrollmentsAmount;
    }
}
