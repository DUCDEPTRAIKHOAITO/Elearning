package org.elearning.service;

import org.elearning.dto.elearning.SubmissionDTO;

import java.util.List;
import java.util.UUID;

public interface SubmissionService {
    List<SubmissionDTO> getAllSubmissions();
    public SubmissionDTO getSubmissionById(UUID id);
    public SubmissionDTO createSubmission(SubmissionDTO dto);
    SubmissionDTO updateSubmission(UUID id, SubmissionDTO dto);
    void deleteSubmission(UUID id);
}
