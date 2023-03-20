package com.indra.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.indra.chat.model.ERole;
import com.indra.chat.entity.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(ERole roleName);
}
