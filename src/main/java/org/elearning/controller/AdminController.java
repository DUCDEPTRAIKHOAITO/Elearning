package org.elearning.controller;

import lombok.RequiredArgsConstructor;
import org.elearning.dto.elearning.AdminDTO;
import org.elearning.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/admins")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    /**
     * Lấy danh sách tất cả Admin
     */
    @GetMapping
    public List<AdminDTO> getAllAdmins() {
        return adminService.getAllAdmins();
    }

    /**
     * Lấy thông tin 1 Admin theo ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<AdminDTO> getAdminById(@PathVariable UUID id) {
        AdminDTO dto = adminService.getAdminById(id);
        if (dto != null) {
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Tạo mới Admin
     */
    @PostMapping
    public ResponseEntity<AdminDTO> createAdmin(@RequestBody AdminDTO adminDTO) {
        AdminDTO created = adminService.createAdmin(adminDTO);
        // trả về 201 Created, với body là đối tượng vừa tạo
        return ResponseEntity
                .status(201)
                .body(created);
    }

    /**
     * Cập nhật Admin
     */
    @PutMapping("/{id}")
    public ResponseEntity<AdminDTO> updateAdmin(
            @PathVariable UUID id,
            @RequestBody AdminDTO adminDTO
    ) {
        AdminDTO updated = adminService.updateAdmin(id, adminDTO);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Xóa Admin
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable UUID id) {
        adminService.deleteAdmin(id);
        // dù id có tồn tại hay không, chúng ta vẫn trả về 204 No Content
        return ResponseEntity.noContent().build();
    }
}
