package org.elearning.service;

import org.elearning.dto.elearning.UserDTO;
import org.elearning.model.User;
import org.elearning.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Lấy tất cả người dùng
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> convertToDTO(user))
                .collect(Collectors.toList());
    }

    // Lấy người dùng theo ID
    public UserDTO getUserById(UUID id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(this::convertToDTO).orElse(null);
    }

    // Tạo mới người dùng
    public UserDTO createUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setStatus(userDTO.getStatus());
        // Có thể thêm logic thêm role cho người dùng
        user = userRepository.save(user);
        return convertToDTO(user);
    }

    // Cập nhật người dùng
    public UserDTO updateUser(UUID id, UserDTO userDTO) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setName(userDTO.getName());
            user.setEmail(userDTO.getEmail());
            user.setPassword(userDTO.getPassword());
            user.setStatus(userDTO.getStatus());
            // Cập nhật các thông tin khác nếu cần
            user = userRepository.save(user);
            return convertToDTO(user);
        }
        return null;
    }

    // Xóa người dùng
    public void deleteUser(UUID id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        }
    }

    // Chuyển đổi User thành UserDTO
    private UserDTO convertToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setStatus(user.getStatus());
        return dto;
    }
}