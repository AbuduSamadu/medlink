package com.mascot.medlink.payload.request;

import com.mascot.medlink.model.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.Set;


@Data
@SuperBuilder
@NoArgsConstructor
public class SignupRequest {
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(min=3, max = 20)
    private String firstName;

    @NotBlank
    @Size(min = 3, max = 20)
    private String lastName;

    @NotBlank
    @Size(min = 3, max = 20)
    private String userName;


    @NotBlank
    @Size(min = 6, max = 56)
    private String password;

    @NotBlank
    @Size(max = 17)
    private String contact;

    private Set<String> roles;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
