package com.indra.chat.service;

import com.indra.chat.entity.Role;
import com.indra.chat.entity.RoleName;
import com.indra.chat.exception.ResourceNotFoundException;
import com.indra.chat.model.ERole;
import com.indra.chat.repository.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role findByName(RoleName roleName) {
        ERole eRole = null;
        if (roleName == RoleName.ROLE_USER) {
            eRole = ERole.ROLE_USER;
        } else if (roleName == RoleName.ROLE_ADMIN) {
            eRole = ERole.ROLE_ADMIN;
        } else {
            throw new IllegalArgumentException("Invalid role name: " + roleName);
        }
        Role role = roleRepository.findByName(eRole);
        if (role == null) {
            throw new ResourceNotFoundException("Role", "name", roleName.name());
        }
        return role;
    }
}
