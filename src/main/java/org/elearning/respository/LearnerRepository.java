package org.elearning.respository;

import org.elearning.model.Learner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface LearnerRepository extends JpaRepository<Learner, UUID> {
    Optional<Learner> findByUserId(UUID userId);  // Tìm kiếm Learner theo userId
}
