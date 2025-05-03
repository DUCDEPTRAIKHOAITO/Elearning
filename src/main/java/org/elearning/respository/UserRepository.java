package org.elearning.respository;

import org.elearning.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    // Tìm User theo Email
    Optional<User> findByEmail(String email);

    // Tìm User theo Role
    List<User> findByRole_Name(String roleName);
}