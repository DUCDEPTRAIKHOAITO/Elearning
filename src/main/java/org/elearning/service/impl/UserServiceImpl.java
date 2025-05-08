package org.elearning.service.impl;

import lombok.RequiredArgsConstructor;
import org.elearning.dto.elearning.UserDTO;
import org.elearning.enums.UserStatus;
import org.elearning.model.User;
import org.elearning.model.Role;
import org.elearning.respository.UserRepository;
import org.elearning.respository.RoleRepository;
import org.elearning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;


    private final RoleRepository roleRepository;

    @Override
    // Lấy tất cả người dùng
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    // Lấy người dùng theo ID
    public UserDTO getUserById(UUID id) {
        Optional<User> user = userRepository.findById(id.toString());  // Make sure you're passing UUID here
        return user.map(this::convertToDTO).orElse(null);
    }

    @Override
    // Tạo mới người dùng
    public UserDTO createUser(UserDTO userDTO) {
        Role role = roleRepository.findById(userDTO.getRoleId()).orElse(null);  // Look up Role by UUID
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setStatus(UserStatus.valueOf(userDTO.getStatus()));  // Set status from UserDTO
        user.setRole(role);  // Set role
        user = userRepository.save(user);
        return convertToDTO(user);
    }

    @Override
    // Cập nhật người dùng
    public UserDTO updateUser(UUID id, UserDTO userDTO) {
        Optional<User> existingUser = userRepository.findById(String.valueOf(id));
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setName(userDTO.getName());
            user.setEmail(userDTO.getEmail());
            user.setPassword(userDTO.getPassword());
            user.setStatus(UserStatus.valueOf(userDTO.getStatus()));  // Cập nhật status từ DTO
            user.setRole(roleRepository.findById(userDTO.getRoleId()).orElse(null));  // Cập nhật role
            user = userRepository.save(user);
            return convertToDTO(user);
        }
        return null;
    }

    @Override
    // Xóa người dùng
    public void deleteUser(UUID id) {
        userRepository.deleteById(String.valueOf(id));
    }

    // Chuyển đổi User thành UserDTO
    private UserDTO convertToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(String.valueOf(user.getId()));
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setStatus(String.valueOf(user.getStatus()));  // Truyền status dưới dạng UserStatus
        dto.setRoleId(user.getRole() != null ? String.valueOf(user.getRole().getId()) : null);  // Chuyển role thành ID nếu cần
        return dto;
    }
}
