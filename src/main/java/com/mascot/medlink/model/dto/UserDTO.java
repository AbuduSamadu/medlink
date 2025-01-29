package com.mascot.medlink.model.dto;

import com.mascot.medlink.model.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long userId;
    private String email;
    private String contact;
    private String password;
    private Set<UserRole> roles = new HashSet<>();
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
