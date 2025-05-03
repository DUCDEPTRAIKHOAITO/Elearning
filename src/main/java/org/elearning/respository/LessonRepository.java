package org.elearning.respository;

import org.elearning.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, UUID> {
    // Tìm Lesson theo CourseId
    List<Lesson> findByCourseId(UUID courseId);
}