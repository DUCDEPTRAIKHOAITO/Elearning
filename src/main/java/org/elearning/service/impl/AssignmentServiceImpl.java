package org.elearning.service.impl;

import lombok.RequiredArgsConstructor;
import org.elearning.dto.elearning.AssignmentDTO;
import org.elearning.model.Assignment;
import org.elearning.respository.AssignmentRepository;
import org.elearning.respository.CourseRepository;
import org.elearning.service.AssignmentService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class AssignmentServiceImpl implements AssignmentService {


    private final AssignmentRepository assignmentRepository;


    private final CourseRepository courseRepository;

    @Override


    // Get all assignments
    public List<AssignmentDTO> getAllAssignments() {
        List<Assignment> assignments = assignmentRepository.findAll();
        return assignments.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    // Get assignment by ID
    public AssignmentDTO getAssignmentById(UUID id) {
        Optional<Assignment> assignment = assignmentRepository.findById(id);
        return assignment.map(this::convertToDTO).orElse(null);
    }

    @Override
    // Create new assignment
    public AssignmentDTO createAssignment(AssignmentDTO assignmentDTO) {
        Assignment assignment = new Assignment();
        assignment.setName(assignmentDTO.getName());
        assignment.setDescription(assignmentDTO.getDescription());

        // Set the assignment date (ensure it's an Instant type)
        assignment.setDate(Instant.parse(assignmentDTO.getAssignmentDate()));

        // Set course by its ID
        assignment.setCourse(courseRepository.findById(UUID.fromString(assignmentDTO.getCourseId())).orElse(null));

        assignment = assignmentRepository.save(assignment);
        return convertToDTO(assignment);
    }

    @Override
    // Update assignment
    public AssignmentDTO updateAssignment(UUID id, AssignmentDTO assignmentDTO) {
        Optional<Assignment> existingAssignment = assignmentRepository.findById(id);
        if (existingAssignment.isPresent()) {
            Assignment assignment = existingAssignment.get();
            assignment.setName(assignmentDTO.getName());
            assignment.setDescription(assignmentDTO.getDescription());

            // Update the assignment date
            assignment.setDate(Instant.parse(assignmentDTO.getAssignmentDate()));

            // Update course if required
            assignment.setCourse(courseRepository.findById(UUID.fromString(assignmentDTO.getCourseId())).orElse(null));

            assignment = assignmentRepository.save(assignment);
            return convertToDTO(assignment);
        }
        return null;
    }

    @Override
    // Delete assignment by ID
    public void deleteAssignment(UUID id) {
        assignmentRepository.deleteById(id);
    }

    // Convert Assignment to AssignmentDTO
    private AssignmentDTO convertToDTO(Assignment assignment) {
        AssignmentDTO dto = new AssignmentDTO();
        dto.setId(assignment.getId().toString());
        dto.setName(assignment.getName());
        dto.setDescription(assignment.getDescription());
        dto.setAssignmentDate(assignment.getDate().toString());  // Convert Instant to String
        dto.setCourseId(assignment.getCourse().getId().toString());
        return dto;
    }
}
