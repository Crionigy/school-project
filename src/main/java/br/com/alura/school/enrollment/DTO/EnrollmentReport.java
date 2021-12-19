package br.com.alura.school.enrollment.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EnrollmentReport {

    @JsonProperty
    private String email;

    @JsonProperty
    private Long enrollmentsAmount;

}
