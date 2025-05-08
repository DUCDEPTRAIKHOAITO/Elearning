package org.elearning.service;

import org.elearning.dto.elearning.LessonDTO;

import java.util.List;
import java.util.UUID;

public interface LessonService {
    List<LessonDTO> getAllLessons();
    LessonDTO getLessonById(UUID id);
    LessonDTO createLesson(LessonDTO lessonDTO);
    LessonDTO updateLesson(UUID id, LessonDTO lessonDTO);
    void deleteLesson(UUID id);
}
