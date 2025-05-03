package org.elearning.dto.elearning;

import lombok.Data;

@Data
public class AssignmentDTO {
    private String id;
    private String name;
    private String description;
    private String courseId; // Liên kết với Course
    private String assignmentDate;
}