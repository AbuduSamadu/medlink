package com.mascot.medlink.service;

import com.mascot.medlink.model.entity.Role;
import com.mascot.medlink.model.entity.User;
import com.mascot.medlink.model.enums.UserRole;
import com.mascot.medlink.payload.request.SignupRequest;
import com.mascot.medlink.payload.response.MessageResponse;
import com.mascot.medlink.repository.RoleRepository;
import com.mascot.medlink.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SignupService {
    private static final Logger logger = LoggerFactory.getLogger(SignupService.class);

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final RoleRepository roleRepository;

    //Preload roles into a map for faster lookup
    private final Map<UserRole, Role> roleCache;


    public SignupService(UserRepository userRepository, PasswordEncoder encoder, RoleRepository roleRepository){
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.roleRepository = roleRepository;

        this.roleCache = loadRoleIntoCache();
    }
    private Map<UserRole, Role> loadRoleIntoCache() {
        return roleRepository.findAll().stream()
                .collect(Collectors.toMap(Role::getName, role->role));
    }

    //Check if the User already exist
    public MessageResponse registerUser(SignupRequest signupRequest){
        if(userRepository.existsByEmail(signupRequest.getEmail())){
            logger.warn("Email already exists: {}", signupRequest.getEmail());
            return  new MessageResponse("Error: Email already exist!");
        }

        if(userRepository.existsByUserName(signupRequest.getUserName())){
            logger.warn("UserName already exists: {}", signupRequest.getUserName());
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

        Set<Role> roles = getRolesFromRequest(signupRequest);
        user.setRoles(roles);

        userRepository.save(user);
        logger.info("User Registered successfully: {} ",signupRequest.getEmail());
        return  new MessageResponse("User Registration successfully");
    }

    private Set<Role> getRolesFromRequest(SignupRequest signupRequest) {
        Set<String> strRole = signupRequest.getRoles();
        Set<Role> roles = new HashSet<>();
        
        if(strRole == null || strRole.isEmpty()){
            roles.add(getRoleOrDefault(UserRole.PATIENT));
        }else {
            strRole.forEach(roleStr ->{
                UserRole roleEnum = UserRole.valueOf(roleStr.toUpperCase());
                roles.add(getRoleOrDefault(roleEnum));
            });
        }
        return roles;
    }

    private Role getRoleOrDefault(UserRole roleName) {
        return roleCache.getOrDefault(roleName, roleCache.get(UserRole.PATIENT));
    }
}
