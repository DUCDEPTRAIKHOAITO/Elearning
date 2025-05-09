package org.elearning.controller;

import lombok.RequiredArgsConstructor;
import org.elearning.dto.elearning.SubmissionDTO;
import org.elearning.service.SubmissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/submissions")
@RequiredArgsConstructor
public class SubmissionController {

    private final SubmissionService submissionService;

    // Lấy tất cả bài nộp
    @GetMapping
    public ResponseEntity<List<SubmissionDTO>> getAllSubmissions() {
        return ResponseEntity.ok(submissionService.getAllSubmissions());
    }

    // Lấy bài nộp theo ID
    @GetMapping("/{id}")
    public ResponseEntity<SubmissionDTO> getSubmissionById(@PathVariable UUID id) {
        SubmissionDTO submission = submissionService.getSubmissionById(id);
        return submission != null ? ResponseEntity.ok(submission) : ResponseEntity.notFound().build();
    }

    // Tạo bài nộp mới
    @PostMapping
    public ResponseEntity<SubmissionDTO> createSubmission(@RequestBody SubmissionDTO submissionDTO) {
        try {
            return ResponseEntity.ok(submissionService.createSubmission(submissionDTO));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null); // Có thể thêm thông báo chi tiết nếu cần
        }
    }

    // Cập nhật bài nộp
    @PutMapping("/{id}")
    public ResponseEntity<SubmissionDTO> updateSubmission(@PathVariable UUID id, @RequestBody SubmissionDTO submissionDTO) {
        try {
            SubmissionDTO updated = submissionService.updateSubmission(id, submissionDTO);
            return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Xóa bài nộp
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubmission(@PathVariable UUID id) {
        submissionService.deleteSubmission(id);
        return ResponseEntity.noContent().build();
    }
}
