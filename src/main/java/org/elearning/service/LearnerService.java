package org.elearning.service;

import org.elearning.dto.elearning.LearnerDTO;
import org.elearning.model.Learner;
import org.elearning.respository.LearnerRepository;
import org.elearning.respository.UserRepository;
import org.elearning.enums.LearnerStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LearnerService {

    @Autowired
    private LearnerRepository learnerRepository;

    @Autowired
    private UserRepository userRepository;

    // Convert Learner entity to LearnerDTO
    private LearnerDTO convertToDTO(Learner learner) {
        LearnerDTO dto = new LearnerDTO();
        dto.setId(learner.getId().toString());  // Convert UUID to String
        dto.setUserId(learner.getUser().getId().toString());  // Convert UUID to String
        dto.setLearnerStatus(learner.getStatus().toString());  // Convert enum to string
        return dto;
    }

    // Create a new learner
    public LearnerDTO createLearner(LearnerDTO learnerDTO) {
        Learner learner = new Learner();
        learner.setStatus(LearnerStatus.valueOf(learnerDTO.getLearnerStatus()));  // Convert string to enum
        learner.setUser(userRepository.findById(learnerDTO.getUserId()).orElse(null));  // Convert String to UUID
        learner = learnerRepository.save(learner);
        return convertToDTO(learner);
    }

    // Update learner
    public LearnerDTO updateLearner(UUID id, LearnerDTO learnerDTO) {
        Optional<Learner> existingLearner = learnerRepository.findById(id);
        if (existingLearner.isPresent()) {
            Learner learner = existingLearner.get();
            learner.setStatus(LearnerStatus.valueOf(learnerDTO.getLearnerStatus()));  // Convert string to enum
            learner.setUser(userRepository.findById(learnerDTO.getUserId()).orElse(null));  // Convert String to UUID
            learner = learnerRepository.save(learner);
            return convertToDTO(learner);
        }
        return null;
    }

    // Get all learners
    public List<LearnerDTO> getAllLearners() {
        List<Learner> learners = learnerRepository.findAll();
        return learners.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Get learner by ID
    public LearnerDTO getLearnerById(UUID id) {
        Optional<Learner> learner = learnerRepository.findById(id);
        return learner.map(this::convertToDTO).orElse(null);
    }

    // Delete learner
    public void deleteLearner(UUID id) {
        learnerRepository.deleteById(id);
    }
}
