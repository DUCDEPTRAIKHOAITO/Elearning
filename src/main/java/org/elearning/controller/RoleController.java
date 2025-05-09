package org.elearning.controller;

import lombok.RequiredArgsConstructor;
import org.elearning.dto.elearning.RoleDTO;
import org.elearning.service.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    // Lấy tất cả role
    @GetMapping
    public ResponseEntity<List<RoleDTO>> getAllRoles() {
        return ResponseEntity.ok(roleService.getAllRoles());
    }

    // Lấy role theo ID
    @GetMapping("/{id}")
    public ResponseEntity<RoleDTO> getRoleById(@PathVariable String id) {
        RoleDTO role = roleService.getRoleById(id);
        return role != null ? ResponseEntity.ok(role) : ResponseEntity.notFound().build();
    }

    // Tạo role mới
    @PostMapping
    public ResponseEntity<RoleDTO> createRole(@RequestBody RoleDTO roleDTO) {
        return ResponseEntity.ok(roleService.createRole(roleDTO));
    }

    // Cập nhật role
    @PutMapping("/{id}")
    public ResponseEntity<RoleDTO> updateRole(@PathVariable String id, @RequestBody RoleDTO roleDTO) {
        RoleDTO updated = roleService.updateRole(id, roleDTO);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    // Xóa role
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable String id) {
        roleService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }
}
