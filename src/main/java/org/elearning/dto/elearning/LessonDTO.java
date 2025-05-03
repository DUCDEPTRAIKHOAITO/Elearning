package org.elearning.dto.elearning;

import lombok.Data;

@Data
public class LessonDTO {
    private String id;
    private String courseId;  // Liên kết với Course
    private String referenceLink;
    private String lessonDate;
}