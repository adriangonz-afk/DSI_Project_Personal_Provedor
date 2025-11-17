package com.system.controller;

import com.system.dto.EspecialidadesPersonalDTO;
import com.system.entity.id.EspecialidadesPersonalId;
import com.system.service.EspecialidadesPersonalService;
import com.system.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/especialidades-personal")
@CrossOrigin(origins = "*")
@Slf4j
public class EspecialidadesPersonalController {

    @Autowired
    private EspecialidadesPersonalService especialidadesPersonalService;

    @GetMapping
    public ResponseEntity<List<EspecialidadesPersonalDTO>> getAll() {
        try {
            log.info("Obteniendo todas las especialidades de personal");
            List<EspecialidadesPersonalDTO> list = especialidadesPersonalService.findAll();
            return ResponseEntity.ok(list);
        } catch (ServiceException e) {
            log.error("Error al obtener todas las especialidades de personal", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<EspecialidadesPersonalDTO> create(@RequestBody EspecialidadesPersonalDTO dto) {
        try {
            log.info("Creando especialidad de personal: {}", dto);
            EspecialidadesPersonalDTO created = especialidadesPersonalService.save(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (ServiceException e) {
            log.error("Error al crear especialidad de personal", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{codCia}/{codEspecialidad}/{codEmpleado}")
    public ResponseEntity<EspecialidadesPersonalDTO> getById(@PathVariable Long codCia,
                                                            @PathVariable Long codEspecialidad,
                                                            @PathVariable Long codEmpleado) {
        try {
            log.info("Buscando especialidad de personal por id: codCia={}, codEspecialidad={}, codEmpleado={}", codCia, codEspecialidad, codEmpleado);
            EspecialidadesPersonalId id = new EspecialidadesPersonalId(codCia, codEspecialidad, codEmpleado);
            EspecialidadesPersonalDTO dto = especialidadesPersonalService.findById(id);
            return ResponseEntity.ok(dto);
        } catch (ServiceException e) {
            log.error("Error al buscar especialidad de personal por id", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{codCia}/{codEspecialidad}/{codEmpleado}")
    public ResponseEntity<EspecialidadesPersonalDTO> update(@PathVariable Long codCia,
                                                           @PathVariable Long codEspecialidad,
                                                           @PathVariable Long codEmpleado,
                                                           @RequestBody EspecialidadesPersonalDTO dto) {
        try {
            log.info("Actualizando especialidad de personal: codCia={}, codEspecialidad={}, codEmpleado={}", codCia, codEspecialidad, codEmpleado);
            // Aseguramos que el DTO tenga los valores correctos de ID
            dto.setCodCia(codCia);
            dto.setCodEspecialidad(codEspecialidad);
            dto.setCodEmpleado(codEmpleado);
            EspecialidadesPersonalDTO updated = especialidadesPersonalService.update(dto);
            return ResponseEntity.ok(updated);
        } catch (ServiceException e) {
            log.error("Error al actualizar especialidad de personal", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{codCia}/{codEspecialidad}/{codEmpleado}")
    public ResponseEntity<Void> delete(@PathVariable Long codCia,
                                       @PathVariable Long codEspecialidad,
                                       @PathVariable Long codEmpleado) {
        try {
            log.info("Eliminando especialidad de personal: codCia={}, codEspecialidad={}, codEmpleado={}", codCia, codEspecialidad, codEmpleado);
            EspecialidadesPersonalId id = new EspecialidadesPersonalId(codCia, codEspecialidad, codEmpleado);
            especialidadesPersonalService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (ServiceException e) {
            log.error("Error al eliminar especialidad de personal", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
