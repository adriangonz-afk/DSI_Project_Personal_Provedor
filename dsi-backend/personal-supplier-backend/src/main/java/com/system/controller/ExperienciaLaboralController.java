package com.system.controller;

import com.system.dto.ExperienciaLaboralDTO;
import com.system.entity.id.ExperienciaLaboralId;
import com.system.service.ExperienciaLaboralService;
import com.system.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/experiencia-laboral")
@CrossOrigin(origins = "*")
@Slf4j
public class ExperienciaLaboralController {

    @Autowired
    private ExperienciaLaboralService experienciaLaboralService;

    @GetMapping
    public ResponseEntity<List<ExperienciaLaboralDTO>> getAll() {
        try {
            log.info("Obteniendo todas las experiencias laborales");
            List<ExperienciaLaboralDTO> list = experienciaLaboralService.findAll();
            return ResponseEntity.ok(list);
        } catch (ServiceException e) {
            log.error("Error al obtener todas las experiencias laborales", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<ExperienciaLaboralDTO> create(@RequestBody ExperienciaLaboralDTO dto) {
        try {
            log.info("Creando experiencia laboral: {}", dto);
            ExperienciaLaboralDTO created = experienciaLaboralService.save(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (ServiceException e) {
            log.error("Error al crear experiencia laboral", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{codCia}/{codExperiencia}/{codEmpleado}")
    public ResponseEntity<ExperienciaLaboralDTO> getById(@PathVariable Long codCia,
                                                        @PathVariable Long codExperiencia,
                                                        @PathVariable Long codEmpleado) {
        try {
            log.info("Buscando experiencia laboral por id: codCia={}, codExperiencia={}, codEmpleado={}", codCia, codExperiencia, codEmpleado);
            ExperienciaLaboralId id = new ExperienciaLaboralId(codCia, codExperiencia, codEmpleado);
            ExperienciaLaboralDTO dto = experienciaLaboralService.findById(id);
            return ResponseEntity.ok(dto);
        } catch (ServiceException e) {
            log.error("Error al buscar experiencia laboral por id", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{codCia}/{codExperiencia}/{codEmpleado}")
    public ResponseEntity<ExperienciaLaboralDTO> update(@PathVariable Long codCia,
                                                       @PathVariable Long codExperiencia,
                                                       @PathVariable Long codEmpleado,
                                                       @RequestBody ExperienciaLaboralDTO dto) {
        try {
            log.info("Actualizando experiencia laboral: codCia={}, codExperiencia={}, codEmpleado={}", codCia, codExperiencia, codEmpleado);
            dto.setCodCia(codCia);
            dto.setCodExperiencia(codExperiencia);
            dto.setCodEmpleado(codEmpleado);
            ExperienciaLaboralDTO updated = experienciaLaboralService.update(dto);
            return ResponseEntity.ok(updated);
        } catch (ServiceException e) {
            log.error("Error al actualizar experiencia laboral", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{codCia}/{codExperiencia}/{codEmpleado}")
    public ResponseEntity<Void> delete(@PathVariable Long codCia,
                                       @PathVariable Long codExperiencia,
                                       @PathVariable Long codEmpleado) {
        try {
            log.info("Eliminando experiencia laboral: codCia={}, codExperiencia={}, codEmpleado={}", codCia, codExperiencia, codEmpleado);
            ExperienciaLaboralId id = new ExperienciaLaboralId(codCia, codExperiencia, codEmpleado);
            experienciaLaboralService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (ServiceException e) {
            log.error("Error al eliminar experiencia laboral", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
