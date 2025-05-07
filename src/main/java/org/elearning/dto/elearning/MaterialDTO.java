package org.elearning.dto.elearning;

import lombok.Data;

@Data
public class MaterialDTO {
    private String id;
    private String lessonId; // Liên kết với Lesson
    private String referenceLink;
    private String title; // Added field for title
    private String url; // Added field for URL
}
