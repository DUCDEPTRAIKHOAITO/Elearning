package org.elearning.dto.elearning;

import lombok.Data;

@Data
public class SubmissionDTO {
    private String id;
    private String assignmentId;  // Liên kết với Assignment
    private String learnerId;    // Liên kết với Learner
    private String submissionDate;
    private String grade;
}