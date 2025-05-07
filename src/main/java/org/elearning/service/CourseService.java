package org.elearning.service;

import org.elearning.dto.elearning.CourseDTO;
import org.elearning.model.Course;
import org.elearning.respository.CourseRepository;
import org.elearning.respository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    // Get all courses
    public List<CourseDTO> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Get course by ID
    public CourseDTO getCourseById(UUID id) {
        Optional<Course> course = courseRepository.findById(id);
        return course.map(this::convertToDTO).orElse(null);
    }

    // Create new course
    public CourseDTO createCourse(CourseDTO courseDTO) {
        Course course = new Course();
        course.setName(courseDTO.getName());
        course.setDescription(courseDTO.getDescription());
        course.setCategory(categoryRepository.findById(UUID.fromString(courseDTO.getCategoryId())).orElse(null));
        course = courseRepository.save(course);
        return convertToDTO(course);
    }

    // Update course
    public CourseDTO updateCourse(UUID id, CourseDTO courseDTO) {
        Optional<Course> existingCourse = courseRepository.findById(id);
        if (existingCourse.isPresent()) {
            Course course = existingCourse.get();
            course.setName(courseDTO.getName());
            course.setDescription(courseDTO.getDescription());
            course.setCategory(categoryRepository.findById(UUID.fromString(courseDTO.getCategoryId())).orElse(null));
            course = courseRepository.save(course);
            return convertToDTO(course);
        }
        return null;
    }

    // Delete course
    public void deleteCourse(UUID id) {
        courseRepository.deleteById(id);
    }

    // Convert Course to CourseDTO
    private CourseDTO convertToDTO(Course course) {
        CourseDTO dto = new CourseDTO();
        dto.setId(course.getId().toString());
        dto.setName(course.getName());
        dto.setDescription(course.getDescription());
        dto.setCategoryId(course.getCategory().getId().toString());
        return dto;
    }
}
