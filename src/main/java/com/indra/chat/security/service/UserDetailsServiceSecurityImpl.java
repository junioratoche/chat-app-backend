package com.indra.chat.security.service;

import com.indra.chat.security.model.UserPrincipal;

import com.indra.chat.entity.User;
import com.indra.chat.exception.UserNotFoundException;
import com.indra.chat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceSecurityImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(usernameOrEmail);
        if (user == null) {
            user = userRepository.findByEmail(usernameOrEmail);
            if (user == null) {
                throw new UsernameNotFoundException("User not found with username or email : " + usernameOrEmail);
            }
        }
        return UserPrincipal.create(user);
    }

    public UserDetails loadUserById(Long id) throws UserNotFoundException {
        User user = userRepository.findById(id);
        if (user == null) {
            throw new UserNotFoundException("User not found with id : " + id);
        }
        return UserPrincipal.create(user);
    }
}
