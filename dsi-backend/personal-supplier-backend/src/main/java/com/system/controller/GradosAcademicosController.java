package com.system.controller;

import com.system.dto.GradosAcademicosDTO;
import com.system.entity.id.GradosAcademicosId;
import com.system.service.GradosAcademicosService;
import com.system.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grados-academicos")
@CrossOrigin(origins = "*")
@Slf4j
public class GradosAcademicosController {

    @Autowired
    private GradosAcademicosService gradosAcademicosService;

    @GetMapping
    public ResponseEntity<List<GradosAcademicosDTO>> getAll() {
        try {
            log.info("Obteniendo todos los grados académicos");
            List<GradosAcademicosDTO> list = gradosAcademicosService.findAll();
            return ResponseEntity.ok(list);
        } catch (ServiceException e) {
            log.error("Error al obtener todos los grados académicos", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<GradosAcademicosDTO> create(@RequestBody GradosAcademicosDTO dto) {
        try {
            log.info("Creando grado académico: {}", dto);
            GradosAcademicosDTO created = gradosAcademicosService.save(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (ServiceException e) {
            log.error("Error al crear grado académico", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{codCia}/{codGrado}/{codEmpleado}")
    public ResponseEntity<GradosAcademicosDTO> getById(@PathVariable Long codCia,
                                                      @PathVariable Long codGrado,
                                                      @PathVariable Long codEmpleado) {
        try {
            log.info("Buscando grado académico por id: codCia={}, codGrado={}, codEmpleado={}", codCia, codGrado, codEmpleado);
            GradosAcademicosId id = new GradosAcademicosId(codCia, codGrado, codEmpleado);
            GradosAcademicosDTO dto = gradosAcademicosService.findById(id);
            return ResponseEntity.ok(dto);
        } catch (ServiceException e) {
            log.error("Error al buscar grado académico por id", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{codCia}/{codGrado}/{codEmpleado}")
    public ResponseEntity<GradosAcademicosDTO> update(@PathVariable Long codCia,
                                                     @PathVariable Long codGrado,
                                                     @PathVariable Long codEmpleado,
                                                     @RequestBody GradosAcademicosDTO dto) {
        try {
            log.info("Actualizando grado académico: codCia={}, codGrado={}, codEmpleado={}", codCia, codGrado, codEmpleado);
            dto.setCodCia(codCia);
            dto.setCodGrado(codGrado);
            dto.setCodEmpleado(codEmpleado);
            GradosAcademicosDTO updated = gradosAcademicosService.update(dto);
            return ResponseEntity.ok(updated);
        } catch (ServiceException e) {
            log.error("Error al actualizar grado académico", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{codCia}/{codGrado}/{codEmpleado}")
    public ResponseEntity<Void> delete(@PathVariable Long codCia,
                                       @PathVariable Long codGrado,
                                       @PathVariable Long codEmpleado) {
        try {
            log.info("Eliminando grado académico: codCia={}, codGrado={}, codEmpleado={}", codCia, codGrado, codEmpleado);
            GradosAcademicosId id = new GradosAcademicosId(codCia, codGrado, codEmpleado);
            gradosAcademicosService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (ServiceException e) {
            log.error("Error al eliminar grado académico", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
