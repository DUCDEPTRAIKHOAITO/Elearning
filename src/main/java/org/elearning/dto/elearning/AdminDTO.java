package org.elearning.dto.elearning;

import lombok.Data;

@Data
public class AdminDTO {
    private String id;
    private String departmentName;
    private String userName;  // Liên kết với User
}