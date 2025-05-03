package org.elearning.respository;

import org.elearning.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AdminRepository extends JpaRepository<Admin, UUID> {
    // Tìm Admin theo UserId
    Optional<Admin> findByUserId(UUID userId);
}