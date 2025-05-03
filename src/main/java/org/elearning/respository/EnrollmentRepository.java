package org.elearning.respository;

import org.elearning.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, UUID> {
    // Tìm Enrollment theo Learner và Course
    List<Enrollment> findByLearnerIdAndCourseId(UUID learnerId, UUID courseId);

    // Tìm Enrollment theo CourseId
    List<Enrollment> findByCourseId(UUID courseId);
}
