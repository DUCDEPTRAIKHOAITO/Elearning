package org.elearning.service.impl;

import lombok.RequiredArgsConstructor;
import org.elearning.dto.elearning.MaterialDTO;
import org.elearning.model.Material;
import org.elearning.respository.MaterialRepository;
import org.elearning.respository.LessonRepository;
import org.elearning.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MaterialServiceImpl implements MaterialService {


    private final MaterialRepository materialRepository;


    private final LessonRepository lessonRepository;

    // Convert Material entity to MaterialDTO
    private MaterialDTO convertToDTO(Material material) {
        MaterialDTO dto = new MaterialDTO();
        dto.setId(material.getId().toString());
        dto.setLessonId(material.getLesson().getId().toString());
        dto.setReferenceLink(material.getReferenceLink()); // Reference link is used
        dto.setTitle(material.getReferenceLink());  // If title is same as referenceLink
        dto.setUrl(material.getReferenceLink()); // Alternatively, if URL is different, adjust accordingly
        return dto;
    }

    @Override
    // Create new material
    public MaterialDTO createMaterial(MaterialDTO materialDTO) {
        Material material = new Material();
        material.setReferenceLink(materialDTO.getReferenceLink());
        material.setLesson(lessonRepository.findById(UUID.fromString(materialDTO.getLessonId())).orElse(null));
        material = materialRepository.save(material);
        return convertToDTO(material);
    }

    @Override
    // Get all materials
    public List<MaterialDTO> getAllMaterials() {
        List<Material> materials = materialRepository.findAll();
        return materials.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    // Get material by ID
    public MaterialDTO getMaterialById(UUID id) {
        Optional<Material> material = materialRepository.findById(id);
        return material.map(this::convertToDTO).orElse(null);
    }

    @Override
    // Delete material
    public void deleteMaterial(UUID id) {
        materialRepository.deleteById(id);
    }
}
