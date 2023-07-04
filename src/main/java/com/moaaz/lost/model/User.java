package com.moaaz.lost.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Entity

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties(value = {"password"}, allowSetters = true)
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotEmpty(message = "Name Must Not Be Empty")
    @NotNull(message = "Name Must Not Be Null")
    private String name;
    @NotEmpty(message = "Phone Must Not Be Empty...")
    @NotNull(message = "Phone Must Not Be Empty...")
    private String phone;
    @NotEmpty(message = "Phone Must Not Be Empty...")
    @NotNull(message = "Phone Must Not Be Empty...")
    @Email(message = "Invalid email address")
    @Pattern(regexp = ".+@gmail\\.com$", message = "Email address must end with @gmail.com")
    @Column(unique = true)
    private String email;

    @NotEmpty(message = "Password Must Not Be Empty...")
    @NotNull(message = "Password Must Not Be Empty...")
    @Size(min = 8, max = 15, message = "Password Must Not Be Greater Than 8 And Less Than 15...")
    private String password;


    @Column(columnDefinition = "longtext")// Image Url.
    private String picture;// Image Url.

    // Json Ignore
    @JsonIgnore
    private String otp;

    @JsonIgnore
    private boolean checkOtpVerify;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE,
                    CascadeType.PERSIST, CascadeType.REFRESH},
            mappedBy = "poster")
    @JsonIgnore
    private List<Post> posts;

    public boolean getCheckOtpVerify() {
        return this.checkOtpVerify;
    }

}
