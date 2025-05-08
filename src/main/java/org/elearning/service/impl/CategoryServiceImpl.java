package org.elearning.service.impl;

import lombok.RequiredArgsConstructor;
import org.elearning.dto.elearning.CategoryDTO;
import org.elearning.model.Category;
import org.elearning.respository.CategoryRepository;
import org.elearning.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {


    private final   CategoryRepository categoryRepository;
    @Override
    // Get all categories
    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    // Get category by ID
    public CategoryDTO getCategoryById(UUID id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category.map(this::convertToDTO).orElse(null);
    }

    @Override
    // Create new category
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setCategoryName(categoryDTO.getCategoryName());
        category.setDescription(categoryDTO.getDescription());
        category = categoryRepository.save(category);
        return convertToDTO(category);
    }

    @Override
    // Update category
    public CategoryDTO updateCategory(UUID id, CategoryDTO categoryDTO) {
        Optional<Category> existingCategory = categoryRepository.findById(id);
        if (existingCategory.isPresent()) {
            Category category = existingCategory.get();
            category.setCategoryName(categoryDTO.getCategoryName());
            category.setDescription(categoryDTO.getDescription());
            category = categoryRepository.save(category);
            return convertToDTO(category);
        }
        return null;
    }

    @Override
    // Delete category
    public void deleteCategory(UUID id) {
        categoryRepository.deleteById(id);
    }

    // Convert Category to CategoryDTO
    private CategoryDTO convertToDTO(Category category) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId().toString());
        dto.setCategoryName(category.getCategoryName());
        dto.setDescription(category.getDescription());
        return dto;
    }
}
