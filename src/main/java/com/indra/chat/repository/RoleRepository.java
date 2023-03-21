package com.indra.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.indra.chat.entity.Role;
import com.indra.chat.model.RoleName;


public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(RoleName roleName);
}
