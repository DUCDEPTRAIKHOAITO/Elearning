package org.elearning.service.impl;

import lombok.RequiredArgsConstructor;
import org.elearning.dto.elearning.RoleDTO;
import org.elearning.model.Role;
import org.elearning.respository.RoleRepository;
import org.elearning.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    // Lấy danh sách tất cả role
    public List<RoleDTO> getAllRoles() {
        return roleRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    // Lấy 1 role theo ID
    public RoleDTO getRoleById(String id) {
        Optional<Role> role = roleRepository.findById(id);
        return role.map(this::convertToDTO).orElse(null);
    }

    @Override
    // Tạo mới role
    public RoleDTO createRole(RoleDTO roleDTO) {
        Role role = new Role();
        // Dùng đúng tên trường trong RoleDTO
        role.setName(roleDTO.getName());
        role.setDescription(roleDTO.getDescription());
        role = roleRepository.save(role);
        return convertToDTO(role);
    }

    @Override
    // Cập nhật role
    public RoleDTO updateRole(String id, RoleDTO roleDTO) {
        Optional<Role> existing = roleRepository.findById(id);
        if (existing.isPresent()) {
            Role role = existing.get();
            role.setName(roleDTO.getName());
            role.setDescription(roleDTO.getDescription());
            role = roleRepository.save(role);
            return convertToDTO(role);
        }
        return null;
    }

    @Override
    // Xóa role
    public void deleteRole(String id) {
        roleRepository.deleteById(id);
    }

    // Chuyển Role → RoleDTO
    private RoleDTO convertToDTO(Role role) {
        RoleDTO dto = new RoleDTO();
        dto.setId(role.getId().toString());
        dto.setName(role.getName());
        dto.setDescription(role.getDescription());
        return dto;
    }
}
