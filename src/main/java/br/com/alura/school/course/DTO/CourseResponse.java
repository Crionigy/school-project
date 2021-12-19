package br.com.alura.school.course.DTO;

import br.com.alura.school.course.Course;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseResponse {

    @JsonProperty
    private final String code;

    @JsonProperty
    private final String name;

    @JsonProperty
    private final String shortDescription;

    public CourseResponse(Course course) {
        this.code = course.getCode();
        this.name = course.getName();
        this.shortDescription = Optional.of(course.getDescription()).map(this::abbreviateDescription).orElse("");
    }

    private String abbreviateDescription(String description) {
        if (description.length() <= 13) return description;
        return description.substring(0, 10) + "...";
    }

    public static List<CourseResponse> makeListCourseResponse(List<Course> courses) {
        List<CourseResponse> courseResponses = new ArrayList<CourseResponse>();
        courses.forEach(course -> courseResponses.add(new CourseResponse(course)));
        return courseResponses;
    }

}
