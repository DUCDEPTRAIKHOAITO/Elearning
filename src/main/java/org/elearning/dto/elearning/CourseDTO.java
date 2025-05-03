package org.elearning.dto.elearning;

import lombok.Data;

@Data
public class CourseDTO {
    private String id;
    private String name;
    private String categoryId; // Liên kết với Category
    private String description;
    private String courseStatus;
}
