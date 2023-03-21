package com.indra.chat.service;

import com.indra.chat.dto.request.SignupRequest;
import com.indra.chat.entity.User;

import java.util.List;

public interface UserService {

    User save(User user);

    List<User> findAll();

    User findByUsername(String username);

    User findByEmail(String email);

    User findById(Long id);

    void delete(User user);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    User getUserById(Long id);

    void registerUser(SignupRequest signUpRequest);

    void updateUser(User user);

}
