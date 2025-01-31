package com.mascot.medlink.service;

import com.mascot.medlink.exception.NotFoundException;
import com.mascot.medlink.model.entity.Role;
import com.mascot.medlink.model.entity.User;
import com.mascot.medlink.model.enums.UserRole;
import com.mascot.medlink.payload.request.SignupRequest;
import com.mascot.medlink.payload.response.MessageResponse;
import com.mascot.medlink.repository.RoleRepository;
import com.mascot.medlink.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
public class SignupService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final RoleRepository roleRepository;

    public SignupService(UserRepository userRepository, PasswordEncoder encoder, RoleRepository roleRepository){
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.roleRepository = roleRepository;
    }

    //Check if the User already exist
    public MessageResponse registerUser(SignupRequest signupRequest){
        if(userRepository.existsByEmail(signupRequest.getEmail())){
            return  new MessageResponse("Error: Email already exist!");
        }

        if(userRepository.existsByUserName(signupRequest.getUserName())){
            return new MessageResponse("Error: UserName already exist!");
        }

        //Create a New User's account
        User user = User.builder()
                .firstName(signupRequest.getFirstName())
                .lastName(signupRequest.getLastName())
                .userName(signupRequest.getUserName())
                .email(signupRequest.getEmail())
                .password(encoder.encode(signupRequest.getPassword()))
                .contact(signupRequest.getContact())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Set<String> strRole = signupRequest.getRoles();
        Set<Role> roles = new HashSet<>();
        if(strRole == null){
            Role userRole = roleRepository.findByName(UserRole.PATIENT)
                            .orElseThrow(() -> new NotFoundException("Role not Found"));
            roles.add(userRole);
        }else{
            strRole.forEach(role -> {
                switch (role){
                    case "admin":
                        Role adminRole = roleRepository.findByName(UserRole.ADMIN)
                                .orElseThrow(()->new NotFoundException("Admin not Found!"));
                        roles.add(adminRole);
                        break;
                    case "pat":
                        Role patRole = roleRepository.findByName(UserRole.PATIENT)
                                .orElseThrow(()-> new NotFoundException("Patient Not Found!"));
                        roles.add(patRole);
                        break;
                    case "doc":
                        Role docRole = roleRepository.findByName(UserRole.DOCTOR)
                                .orElseThrow(()->new NotFoundException("Doctor not Found"));
                        roles.add(docRole);
                    default:
                        Role userRole = roleRepository.findByName(UserRole.PATIENT)
                                .orElseThrow(()->new NotFoundException("Patient not Found"));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);
        return  new MessageResponse("User Registration successfully");
    }
}
