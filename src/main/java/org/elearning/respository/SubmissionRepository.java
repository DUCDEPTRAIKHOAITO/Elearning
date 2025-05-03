package org.elearning.respository;

import org.elearning.model.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, UUID> {
    // Tìm Submission theo Assignment và Learner
    List<Submission> findByAssignmentIdAndLearnerId(UUID assignmentId, UUID learnerId);
}