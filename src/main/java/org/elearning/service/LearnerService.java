package org.elearning.service;

import org.elearning.dto.elearning.LearnerDTO;

import java.util.List;
import java.util.UUID;

public interface LearnerService {
    List<LearnerDTO> getAllLearners();
    LearnerDTO createLearner(LearnerDTO learnerDTO);
    LearnerDTO updateLearner(UUID id, LearnerDTO learnerDTO);
    LearnerDTO getLearnerById(UUID id);
    void deleteLearner(UUID id);
}
