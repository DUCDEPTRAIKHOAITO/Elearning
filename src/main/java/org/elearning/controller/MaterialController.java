package org.elearning.controller;

import lombok.RequiredArgsConstructor;
import org.elearning.dto.elearning.MaterialDTO;
import org.elearning.service.MaterialService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/materials")
@RequiredArgsConstructor
public class MaterialController {

    private final MaterialService materialService;

    // Lấy tất cả tài liệu
    @GetMapping
    public ResponseEntity<List<MaterialDTO>> getAllMaterials() {
        return ResponseEntity.ok(materialService.getAllMaterials());
    }

    // Lấy tài liệu theo ID
    @GetMapping("/{id}")
    public ResponseEntity<MaterialDTO> getMaterialById(@PathVariable UUID id) {
        MaterialDTO material = materialService.getMaterialById(id);
        return material != null ? ResponseEntity.ok(material) : ResponseEntity.notFound().build();
    }

    // Tạo tài liệu mới
    @PostMapping
    public ResponseEntity<MaterialDTO> createMaterial(@RequestBody MaterialDTO materialDTO) {
        return ResponseEntity.ok(materialService.createMaterial(materialDTO));
    }

    // Xóa tài liệu
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaterial(@PathVariable UUID id) {
        materialService.deleteMaterial(id);
        return ResponseEntity.noContent().build();
    }
}
