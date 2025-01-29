package com.mascot.medlink.model.dto;

import com.mascot.medlink.model.enums.UserRole;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;



@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long userId;
    private String email;
    private String contact;
    private Set<UserRole> roles;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public Set<UserRole> getRoles() {
        return roles != null ? Collections.unmodifiableSet(roles): Collections.emptySet();
    }
}
