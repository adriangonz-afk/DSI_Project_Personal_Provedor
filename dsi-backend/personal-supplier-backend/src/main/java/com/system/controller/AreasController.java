package com.system.controller;

import com.system.dto.AreasDTO;
import com.system.entity.id.AreasId;
import com.system.service.AreasService;
import com.system.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/areas")
@CrossOrigin(origins = "*")
public class AreasController {

    @Autowired
    private AreasService areasService;

    @GetMapping
    public ResponseEntity<List<AreasDTO>> getAll() {
        try {
            log.info("Listando todas las áreas");
            List<AreasDTO> areas = areasService.findAll();
            return ResponseEntity.ok(areas);
        } catch (ServiceException e) {
            log.error("Error al listar áreas", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<AreasDTO> create(@RequestBody AreasDTO dto) {
        try {
            log.info("Creando área: {}", dto);
            AreasDTO saved = areasService.save(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (ServiceException e) {
            log.error("Error al crear área", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/{codCia}/{codArea}")
    public ResponseEntity<AreasDTO> getById(@PathVariable Long codCia, @PathVariable Long codArea) {
        try {
            log.info("Buscando área con codCia={} y codArea={}", codCia, codArea);
            AreasId id = new AreasId(codCia, codArea);
            AreasDTO dto = areasService.findById(id);
            return ResponseEntity.ok(dto);
        } catch (ServiceException e) {
            log.error("Área no encontrada: codCia={}, codArea={}", codCia, codArea);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{codCia}/{codArea}")
    public ResponseEntity<AreasDTO> update(@PathVariable Long codCia, @PathVariable Long codArea, @RequestBody AreasDTO dto) {
        try {
            log.info("Actualizando área codCia={}, codArea={}", codCia, codArea);
            dto.setCodCia(codCia);
            dto.setCodArea(codArea);
            AreasDTO updated = areasService.update(dto);
            return ResponseEntity.ok(updated);
        } catch (ServiceException e) {
            log.error("Error al actualizar área codCia={}, codArea={}", codCia, codArea);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{codCia}/{codArea}")
    public ResponseEntity<Void> delete(@PathVariable Long codCia, @PathVariable Long codArea) {
        try {
            log.info("Eliminando área codCia={}, codArea={}", codCia, codArea);
            AreasId id = new AreasId(codCia, codArea);
            areasService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (ServiceException e) {
            log.error("Error al eliminar área codCia={}, codArea={}", codCia, codArea);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
