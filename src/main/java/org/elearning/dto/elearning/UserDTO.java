package org.elearning.dto.elearning;

import lombok.Data;

@Data
public class UserDTO {
    private String id;
    private String name;
    private String email;
    private String password;
    private String status;
    private String roleId;  // Liên kết với Role
}