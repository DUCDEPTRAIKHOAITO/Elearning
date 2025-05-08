package org.elearning.service.impl;


import lombok.RequiredArgsConstructor;
import org.elearning.dto.elearning.AdminDTO;
import org.elearning.model.Admin;
import org.elearning.respository.AdminRepository;
import org.elearning.service.AdminService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    @Override
    // Lấy tất cả Admins
    public List<AdminDTO> getAllAdmins() {
        List<Admin> admins = adminRepository.findAll();
        return admins.stream()
                .map(admin -> convertToDTO(admin))
                .collect(Collectors.toList());
    }
    @Override
    // Lấy Admin theo ID
    public AdminDTO getAdminById(UUID id) {
        Optional<Admin> admin = adminRepository.findById(id);
        return admin.map(this::convertToDTO).orElse(null);
    }
    @Override
    // Tạo mới Admin
    public AdminDTO createAdmin(AdminDTO adminDTO) {
        Admin admin = new Admin();
        admin.setDepartmentName(adminDTO.getDepartmentName());
        // Logic lưu Admin vào CSDL
        admin = adminRepository.save(admin);
        return convertToDTO(admin);
    }
    @Override
    // Cập nhật Admin
    public AdminDTO updateAdmin(UUID id, AdminDTO adminDTO) {
        Optional<Admin> existingAdmin = adminRepository.findById(id);
        if (existingAdmin.isPresent()) {
            Admin admin = existingAdmin.get();
            admin.setDepartmentName(adminDTO.getDepartmentName());
            // Cập nhật thông tin Admin
            admin = adminRepository.save(admin);
            return convertToDTO(admin);
        }
        return null;
    }
    @Override
    // Xóa Admin

    public void deleteAdmin(UUID id) {
        if (adminRepository.existsById(id)) {
            adminRepository.deleteById(id);
        }
    }

    // Chuyển đổi Admin thành AdminDTO
    private AdminDTO convertToDTO(Admin admin) {
        AdminDTO dto = new AdminDTO();
        dto.setId(admin.getId().toString());
        dto.setDepartmentName(admin.getDepartmentName());
        return dto;
    }
    //abcd
    //hasjdhasaa
}