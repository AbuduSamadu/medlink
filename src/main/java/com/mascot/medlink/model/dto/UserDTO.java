package com.mascot.medlink.model.dto;

import com.mascot.medlink.model.entity.Role;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;



@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long userId;
    private String email;
    private String firstName;
    private String lastName;
    private String userName;
    private String contact;
    private Set<Role> roles;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public Set<Role> getRoles() {
        return roles != null ? Collections.unmodifiableSet(roles): Collections.emptySet();
    }
}
