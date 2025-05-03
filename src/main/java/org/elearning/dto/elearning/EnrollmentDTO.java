package org.elearning.dto.elearning;

import lombok.Data;

@Data
public class EnrollmentDTO {
    private String id;
    private String learnerId; // Liên kết với Learner
    private String courseId;  // Liên kết với Course
    private String enrollmentDate;
    private String enrollmentStatus;
}