package org.elearning.respository;

import org.elearning.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
    // Tìm Category theo tên
    Optional<Category> findByCategoryName(String categoryName);
}
