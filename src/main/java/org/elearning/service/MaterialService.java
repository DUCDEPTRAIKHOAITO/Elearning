package org.elearning.service;

import org.elearning.dto.elearning.MaterialDTO;

import java.util.List;
import java.util.UUID;

public interface MaterialService {
    List<MaterialDTO> getAllMaterials();
    MaterialDTO getMaterialById(UUID id);
    MaterialDTO createMaterial(MaterialDTO materialDTO);
    void deleteMaterial(UUID id);
}
