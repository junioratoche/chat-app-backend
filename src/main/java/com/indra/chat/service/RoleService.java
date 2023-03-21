package com.indra.chat.service;

import java.util.List;

import com.indra.chat.entity.Role;
import com.indra.chat.model.RoleName;

public interface RoleService {
    List<Role> getAllRoles();
    Role findByName(RoleName roleName);
}
