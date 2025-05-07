package org.elearning.service;

import org.elearning.dto.elearning.SubmissionDTO;
import org.elearning.model.Assignment;
import org.elearning.model.Learner;
import org.elearning.model.Submission;
import org.elearning.respository.AssignmentRepository;
import org.elearning.respository.LearnerRepository;
import org.elearning.respository.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SubmissionService {

    @Autowired
    private SubmissionRepository submissionRepository;

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private LearnerRepository learnerRepository;

    // Lấy tất cả submission
    public List<SubmissionDTO> getAllSubmissions() {
        return submissionRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Lấy submission theo ID
    public SubmissionDTO getSubmissionById(UUID id) {
        Optional<Submission> sub = submissionRepository.findById(id);
        return sub.map(this::convertToDTO).orElse(null);
    }

    // Tạo mới submission
    public SubmissionDTO createSubmission(SubmissionDTO dto) {
        Submission submission = new Submission();

        // Thiết lập quan hệ Assignment
        Assignment assignment = assignmentRepository
                .findById(UUID.fromString(dto.getAssignmentId()))
                .orElseThrow(() -> new IllegalArgumentException("Assignment not found: " + dto.getAssignmentId()));
        submission.setAssignment(assignment);

        // Thiết lập quan hệ Learner
        Learner learner = learnerRepository
                .findById(UUID.fromString(dto.getLearnerId()))
                .orElseThrow(() -> new IllegalArgumentException("Learner not found: " + dto.getLearnerId()));
        submission.setLearner(learner);

        // Ngày nộp và điểm
        submission.setDate(dto.getSubmissionDate());
        submission.setGrade(dto.getGrade());

        submission = submissionRepository.save(submission);
        return convertToDTO(submission);
    }

    // Cập nhật submission
    public SubmissionDTO updateSubmission(UUID id, SubmissionDTO dto) {
        Optional<Submission> existing = submissionRepository.findById(id);
        if (existing.isPresent()) {
            Submission submission = existing.get();

            // Nếu thay đổi assignment
            if (dto.getAssignmentId() != null) {
                Assignment assignment = assignmentRepository
                        .findById(UUID.fromString(dto.getAssignmentId()))
                        .orElseThrow(() -> new IllegalArgumentException("Assignment not found: " + dto.getAssignmentId()));
                submission.setAssignment(assignment);
            }

            // Nếu thay đổi learner
            if (dto.getLearnerId() != null) {
                Learner learner = learnerRepository
                        .findById(UUID.fromString(dto.getLearnerId()))
                        .orElseThrow(() -> new IllegalArgumentException("Learner not found: " + dto.getLearnerId()));
                submission.setLearner(learner);
            }

            // Ngày nộp & điểm
            if (dto.getSubmissionDate() != null) {
                submission.setDate(dto.getSubmissionDate());
            }
            if (dto.getGrade() != null) {
                submission.setGrade(dto.getGrade());
            }

            submission = submissionRepository.save(submission);
            return convertToDTO(submission);
        }
        return null;
    }

    // Xóa submission
    public void deleteSubmission(UUID id) {
        submissionRepository.deleteById(id);
    }

    // Chuyển Submission => SubmissionDTO
    private SubmissionDTO convertToDTO(Submission submission) {
        SubmissionDTO dto = new SubmissionDTO();
        dto.setId(submission.getId().toString());
        dto.setAssignmentId(submission.getAssignment().getId().toString());
        dto.setLearnerId(submission.getLearner().getId().toString());
        dto.setSubmissionDate(submission.getDate());
        dto.setGrade(submission.getGrade());
        return dto;
    }
}
