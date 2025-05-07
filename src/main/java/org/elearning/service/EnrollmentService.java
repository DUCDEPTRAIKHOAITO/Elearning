package org.elearning.service;

import org.elearning.dto.elearning.EnrollmentDTO;
import org.elearning.model.Enrollment;
import org.elearning.respository.EnrollmentRepository;
import org.elearning.respository.CourseRepository;
import org.elearning.respository.LearnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private LearnerRepository learnerRepository;

    // Get all enrollments
    public List<EnrollmentDTO> getAllEnrollments() {
        List<Enrollment> enrollments = enrollmentRepository.findAll();
        return enrollments.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Get enrollment by ID
    public EnrollmentDTO getEnrollmentById(UUID id) {
        Optional<Enrollment> enrollment = enrollmentRepository.findById(id);
        return enrollment.map(this::convertToDTO).orElse(null);
    }

    // Create new enrollment
    public EnrollmentDTO createEnrollment(EnrollmentDTO enrollmentDTO) {
        Enrollment enrollment = new Enrollment();
        enrollment.setCourse(courseRepository.findById(UUID.fromString(enrollmentDTO.getCourseId())).orElse(null));
        enrollment.setLearner(learnerRepository.findById(UUID.fromString(enrollmentDTO.getLearnerId())).orElse(null));
        enrollment = enrollmentRepository.save(enrollment);
        return convertToDTO(enrollment);
    }

    // Update enrollment
    public EnrollmentDTO updateEnrollment(UUID id, EnrollmentDTO enrollmentDTO) {
        Optional<Enrollment> existingEnrollment = enrollmentRepository.findById(id);
        if (existingEnrollment.isPresent()) {
            Enrollment enrollment = existingEnrollment.get();
            enrollment.setCourse(courseRepository.findById(UUID.fromString(enrollmentDTO.getCourseId())).orElse(null));
            enrollment.setLearner(learnerRepository.findById(UUID.fromString(enrollmentDTO.getLearnerId())).orElse(null));
            enrollment = enrollmentRepository.save(enrollment);
            return convertToDTO(enrollment);
        }
        return null;
    }

    // Delete enrollment
    public void deleteEnrollment(UUID id) {
        enrollmentRepository.deleteById(id);
    }

    // Convert Enrollment to EnrollmentDTO
    private EnrollmentDTO convertToDTO(Enrollment enrollment) {
        EnrollmentDTO dto = new EnrollmentDTO();
        dto.setId(enrollment.getId().toString());
        dto.setCourseId(enrollment.getCourse().getId().toString());
        dto.setLearnerId(enrollment.getLearner().getId().toString());
        return dto;
    }
}
