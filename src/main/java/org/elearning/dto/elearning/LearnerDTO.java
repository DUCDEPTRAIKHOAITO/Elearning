package org.elearning.dto.elearning;


import lombok.Data;

@Data
public class LearnerDTO {
    private String id;
    private String userId;  // Liên kết với User
    private String learnerStatus;
}