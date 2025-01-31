package com.mascot.medlink.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;


@Data
@AllArgsConstructor
public class JwtResponse {
    private String token;
    private  String type = "Bearer";
    private Long id;
    private String username;
    private String email;
    private Set<String> roles;
}
