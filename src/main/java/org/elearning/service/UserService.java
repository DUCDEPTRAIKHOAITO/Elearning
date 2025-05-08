package org.elearning.service;

import org.elearning.dto.elearning.UserDTO;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<UserDTO> getAllUsers();
    UserDTO getUserById(UUID id);
    UserDTO createUser(UserDTO userDTO);
    UserDTO updateUser(UUID id, UserDTO userDTO);
    void deleteUser(UUID id);
}
