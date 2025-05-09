package org.elearning.controller;

import lombok.RequiredArgsConstructor;
import org.elearning.dto.elearning.LearnerDTO;
import org.elearning.service.LearnerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/learners")
@RequiredArgsConstructor
public class LearnerController {

    private final LearnerService learnerService;

    // Lấy tất cả learner
    @GetMapping
    public ResponseEntity<List<LearnerDTO>> getAllLearners() {
        return ResponseEntity.ok(learnerService.getAllLearners());
    }

    // Lấy learner theo ID
    @GetMapping("/{id}")
    public ResponseEntity<LearnerDTO> getLearnerById(@PathVariable UUID id) {
        LearnerDTO learner = learnerService.getLearnerById(id);
        return learner != null ? ResponseEntity.ok(learner) : ResponseEntity.notFound().build();
    }

    // Tạo learner mới
    @PostMapping
    public ResponseEntity<LearnerDTO> createLearner(@RequestBody LearnerDTO learnerDTO) {
        return ResponseEntity.ok(learnerService.createLearner(learnerDTO));
    }

    // Cập nhật learner
    @PutMapping("/{id}")
    public ResponseEntity<LearnerDTO> updateLearner(@PathVariable UUID id, @RequestBody LearnerDTO learnerDTO) {
        LearnerDTO updated = learnerService.updateLearner(id, learnerDTO);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    // Xóa learner
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLearner(@PathVariable UUID id) {
        learnerService.deleteLearner(id);
        return ResponseEntity.noContent().build();
    }
}
