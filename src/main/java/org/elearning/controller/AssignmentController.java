package org.elearning.controller;

import lombok.RequiredArgsConstructor;
import org.elearning.dto.elearning.AssignmentDTO;
import org.elearning.service.AssignmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/assignments")
@RequiredArgsConstructor
public class AssignmentController {

    private final AssignmentService assignmentService;

    @GetMapping
    public ResponseEntity<List<AssignmentDTO>> getAllAssignments() {
        return ResponseEntity.ok(assignmentService.getAllAssignments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssignmentDTO> getAssignmentById(@PathVariable UUID id) {
        AssignmentDTO assignment = assignmentService.getAssignmentById(id);
        return assignment != null ? ResponseEntity.ok(assignment) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<AssignmentDTO> createAssignment(@RequestBody AssignmentDTO assignmentDTO) {
        return ResponseEntity.ok(assignmentService.createAssignment(assignmentDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssignmentDTO> updateAssignment(
            @PathVariable UUID id,
            @RequestBody AssignmentDTO assignmentDTO
    ) {
        AssignmentDTO updated = assignmentService.updateAssignment(id, assignmentDTO);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAssignment(@PathVariable UUID id) {
        assignmentService.deleteAssignment(id);
        return ResponseEntity.noContent().build();
    }
}
