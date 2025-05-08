package org.elearning.service.impl;

import lombok.RequiredArgsConstructor;
import org.elearning.dto.elearning.LessonDTO;
import org.elearning.model.Lesson;
import org.elearning.respository.LessonRepository;
import org.elearning.respository.CourseRepository;
import org.elearning.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;


    private final CourseRepository courseRepository;

    @Override
    // Get all lessons
    public List<LessonDTO> getAllLessons() {
        List<Lesson> lessons = lessonRepository.findAll();
        return lessons.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    // Get lesson by ID
    public LessonDTO getLessonById(UUID id) {
        Optional<Lesson> lesson = lessonRepository.findById(id);
        return lesson.map(this::convertToDTO).orElse(null);
    }

    @Override
    // Create new lesson
    public LessonDTO createLesson(LessonDTO lessonDTO) {
        Lesson lesson = new Lesson();
        lesson.setName(lessonDTO.getName());
        lesson.setDescription(lessonDTO.getDescription());
        lesson.setCourse(courseRepository.findById(UUID.fromString(lessonDTO.getCourseId())).orElse(null));
        lesson = lessonRepository.save(lesson);
        return convertToDTO(lesson);
    }

    @Override
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

    @Override
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
