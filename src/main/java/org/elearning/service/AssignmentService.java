package org.elearning.service;

import org.elearning.dto.elearning.AssignmentDTO;

import java.util.List;
import java.util.UUID;

public interface AssignmentService {
    List<AssignmentDTO> getAllAssignments();
    AssignmentDTO getAssignmentById(UUID id);
    AssignmentDTO createAssignment(AssignmentDTO assignmentDTO);
    AssignmentDTO updateAssignment(UUID id, AssignmentDTO assignmentDTO);
    void deleteAssignment(UUID id);
}
