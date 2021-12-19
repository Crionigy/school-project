package br.com.alura.school.user;

import br.com.alura.school.enrollment.Enrollment;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "enrollments"})
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_user")
    private Long id;

    @Size(max=20)
    @NotBlank
    @Column(nullable = false, unique = true)
    private String username;

    @NotBlank
    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @Column(name="id_enrollment")
    private Set<Enrollment> enrollments;

    @Deprecated
    protected User() {}

    public User(Long id) {
        this.id = id;
    }

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public Long getId() {
        return id;
    }
}
