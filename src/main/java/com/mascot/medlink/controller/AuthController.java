package com.mascot.medlink.controller;

import com.mascot.medlink.model.entity.User;
import com.mascot.medlink.payload.request.SignupRequest;
import com.mascot.medlink.payload.response.MessageResponse;
import com.mascot.medlink.repository.UserRepository;
import com.mascot.medlink.service.SignupService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final SignupService signupService;

    @Autowired
    public AuthController(SignupService signupService){
        this.signupService = signupService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest){
        MessageResponse response = signupService.registerUser(signupRequest);
        if(response.getMessage().startsWith("Error:")){
            return ResponseEntity.badRequest().body(response);
        }
        return  ResponseEntity.ok(response);
    }
}

