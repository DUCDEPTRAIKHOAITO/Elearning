package org.elearning.service;

import org.elearning.dto.elearning.LessonDTO;
import org.elearning.model.Lesson;
import org.elearning.respository.LessonRepository;
import org.elearning.respository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LessonService {

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private CourseRepository courseRepository;

    // Get all lessons
    public List<LessonDTO> getAllLessons() {
        List<Lesson> lessons = lessonRepository.findAll();
        return lessons.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Get lesson by ID
    public LessonDTO getLessonById(UUID id) {
        Optional<Lesson> lesson = lessonRepository.findById(id);
        return lesson.map(this::convertToDTO).orElse(null);
    }

    // Create new lesson
    public LessonDTO createLesson(LessonDTO lessonDTO) {
        Lesson lesson = new Lesson();
        lesson.setName(lessonDTO.getName());
        lesson.setDescription(lessonDTO.getDescription());
        lesson.setCourse(courseRepository.findById(UUID.fromString(lessonDTO.getCourseId())).orElse(null));
        lesson = lessonRepository.save(lesson);
        return convertToDTO(lesson);
    }

    // Update lesson
    public LessonDTO updateLesson(UUID id, LessonDTO lessonDTO) {
        Optional<Lesson> existingLesson = lessonRepository.findById(id);
        if (existingLesson.isPresent()) {
            Lesson lesson = existingLesson.get();
            lesson.setName(lessonDTO.getName());
            lesson.setDescription(lessonDTO.getDescription());
            lesson.setCourse(courseRepository.findById(UUID.fromString(lessonDTO.getCourseId())).orElse(null));
            lesson = lessonRepository.save(lesson);
            return convertToDTO(lesson);
        }
        return null;
    }

    // Delete lesson
    public void deleteLesson(UUID id) {
        lessonRepository.deleteById(id);
    }

    // Convert Lesson to LessonDTO
    private LessonDTO convertToDTO(Lesson lesson) {
        LessonDTO dto = new LessonDTO();
        dto.setId(lesson.getId().toString());
        dto.setName(lesson.getName());
        dto.setDescription(lesson.getDescription());
        dto.setCourseId(lesson.getCourse().getId().toString());
        return dto;
    }
}
