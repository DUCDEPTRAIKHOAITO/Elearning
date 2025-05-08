package org.elearning.service;

import org.elearning.dto.elearning.RoleDTO;

import java.util.List;

public interface RoleService {
    List<RoleDTO> getAllRoles();
    RoleDTO getRoleById(String id);
    RoleDTO createRole(RoleDTO roleDTO);
    RoleDTO updateRole(String id, RoleDTO roleDTO);
    void deleteRole(String id);
}
