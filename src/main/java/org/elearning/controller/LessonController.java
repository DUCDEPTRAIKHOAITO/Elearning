package org.elearning.controller;

import lombok.RequiredArgsConstructor;
import org.elearning.dto.elearning.LessonDTO;
import org.elearning.service.LessonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/lessons")
@RequiredArgsConstructor
public class LessonController {

    private final LessonService lessonService;

    // Lấy tất cả bài học
    @GetMapping
    public ResponseEntity<List<LessonDTO>> getAllLessons() {
        return ResponseEntity.ok(lessonService.getAllLessons());
    }

    // Lấy bài học theo ID
    @GetMapping("/{id}")
    public ResponseEntity<LessonDTO> getLessonById(@PathVariable UUID id) {
        LessonDTO lesson = lessonService.getLessonById(id);
        return lesson != null ? ResponseEntity.ok(lesson) : ResponseEntity.notFound().build();
    }

    // Tạo bài học mới
    @PostMapping
    public ResponseEntity<LessonDTO> createLesson(@RequestBody LessonDTO lessonDTO) {
        return ResponseEntity.ok(lessonService.createLesson(lessonDTO));
    }

    // Cập nhật bài học
    @PutMapping("/{id}")
    public ResponseEntity<LessonDTO> updateLesson(@PathVariable UUID id, @RequestBody LessonDTO lessonDTO) {
        LessonDTO updated = lessonService.updateLesson(id, lessonDTO);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    // Xóa bài học
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLesson(@PathVariable UUID id) {
        lessonService.deleteLesson(id);
        return ResponseEntity.noContent().build();
    }
}
