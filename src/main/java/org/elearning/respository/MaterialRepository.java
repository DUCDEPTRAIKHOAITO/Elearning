package org.elearning.respository;

import org.elearning.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MaterialRepository extends JpaRepository<Material, UUID> {
    // Tìm Material theo LessonId
    List<Material> findByLessonId(UUID lessonId);
}