package com.system.controller;

import com.system.dto.PersonaDTO;
import com.system.entity.id.PersonaId;
import com.system.service.PersonaService;
import com.system.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/personas")
@CrossOrigin(origins = "*")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @GetMapping
    public ResponseEntity<List<PersonaDTO>> getAll() {
        try {
            log.info("Listando todas las personas");
            List<PersonaDTO> personas = personaService.findAll();
            return ResponseEntity.ok(personas);
        } catch (ServiceException e) {
            log.error("Error al listar personas", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<PersonaDTO> create(@RequestBody PersonaDTO dto) {
        try {
            log.info("Creando persona: {}", dto);
            PersonaDTO saved = personaService.save(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (ServiceException e) {
            log.error("Error al crear persona", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/{codCia}/{codPersona}")
    public ResponseEntity<PersonaDTO> getById(@PathVariable Long codCia, @PathVariable Long codPersona) {
        try {
            log.info("Buscando persona con codCia={} y codPersona={}", codCia, codPersona);
            PersonaId id = new PersonaId(codCia, codPersona);
            PersonaDTO dto = personaService.findById(id);
            return ResponseEntity.ok(dto);
        } catch (ServiceException e) {
            log.error("Persona no encontrada: codCia={}, codPersona={}", codCia, codPersona);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{codCia}/{codPersona}")
    public ResponseEntity<PersonaDTO> update(@PathVariable Long codCia, @PathVariable Long codPersona, @RequestBody PersonaDTO dto) {
        try {
            log.info("Actualizando persona codCia={}, codPersona={}", codCia, codPersona);
            dto.setCodCia(codCia);
            dto.setCodPersona(codPersona);
            PersonaDTO updated = personaService.update(dto);
            return ResponseEntity.ok(updated);
        } catch (ServiceException e) {
            log.error("Error al actualizar persona codCia={}, codPersona={}", codCia, codPersona);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{codCia}/{codPersona}")
    public ResponseEntity<Void> delete(@PathVariable Long codCia, @PathVariable Long codPersona) {
        try {
            log.info("Eliminando persona codCia={}, codPersona={}", codCia, codPersona);
            PersonaId id = new PersonaId(codCia, codPersona);
            personaService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (ServiceException e) {
            log.error("Error al eliminar persona codCia={}, codPersona={}", codCia, codPersona);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
