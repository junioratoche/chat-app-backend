package com.indra.chat.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.indra.chat.dto.request.SignupRequest;
import com.indra.chat.dto.response.MessageResponse;
import com.indra.chat.entity.Role;
import com.indra.chat.model.RoleName;
import com.indra.chat.entity.User;
import com.indra.chat.exception.ResourceNotFoundException;
import com.indra.chat.repository.RoleRepository;
import com.indra.chat.repository.UserRepository;

@RestController
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    	
    	System.out.println("Ingresó a REST");

        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(), passwordEncoder.encode(signUpRequest.getPassword()),
                signUpRequest.getEmail());

        Set<RoleName> roleNames = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (roleNames == null || roleNames.isEmpty()) {
            Role userRole = roleRepository.findByName(RoleName.ROLE_USER);
            if(userRole == null){
                throw new ResourceNotFoundException("Role", "name", RoleName.ROLE_USER.name());
            }
            roles.add(userRole);
        } else {
            roleNames.forEach(roleName -> {
                Role role = roleRepository.findByName(roleName);
                if (role == null) {
                    throw new ResourceNotFoundException("Role", "name", roleName.name());
                }
                roles.add(role);
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
