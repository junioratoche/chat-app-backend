package com.indra.chat.repository;

import java.util.Optional;

import com.indra.chat.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Optional<User> findById(Long id);

    User findByEmail(String email);
    
    User save(User user);
    
    void delete(User user);
    
}
