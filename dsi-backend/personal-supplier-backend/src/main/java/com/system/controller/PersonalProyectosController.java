package com.system.controller;

import com.system.dto.PersonalProyectosDTO;
import com.system.entity.id.PersonalProyectosId;
import com.system.service.PersonalProyectosService;
import com.system.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personal-proyectos")
@CrossOrigin(origins = "*")
@Slf4j
public class PersonalProyectosController {

    @Autowired
    private PersonalProyectosService personalProyectosService;

    @GetMapping
    public ResponseEntity<List<PersonalProyectosDTO>> getAll() {
        try {
            log.info("Obteniendo todos los personal-proyectos");
            List<PersonalProyectosDTO> list = personalProyectosService.findAll();
            return ResponseEntity.ok(list);
        } catch (ServiceException e) {
            log.error("Error al obtener todos los personal-proyectos", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<PersonalProyectosDTO> create(@RequestBody PersonalProyectosDTO dto) {
        try {
            log.info("Creando personal-proyecto: {}", dto);
            PersonalProyectosDTO created = personalProyectosService.save(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (ServiceException e) {
            log.error("Error al crear personal-proyecto", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{codCia}/{codPersonalProyecto}/{codEmpleado}")
    public ResponseEntity<PersonalProyectosDTO> getById(@PathVariable Long codCia,
                                                       @PathVariable Long codPersonalProyecto,
                                                       @PathVariable Long codEmpleado) {
        try {
            log.info("Buscando personal-proyecto por id: codCia={}, codPersonalProyecto={}, codEmpleado={}", codCia, codPersonalProyecto, codEmpleado);
            PersonalProyectosId id = new PersonalProyectosId(codCia, codPersonalProyecto, codEmpleado);
            PersonalProyectosDTO dto = personalProyectosService.findById(id);
            return ResponseEntity.ok(dto);
        } catch (ServiceException e) {
            log.error("Error al buscar personal-proyecto por id", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{codCia}/{codPersonalProyecto}/{codEmpleado}")
    public ResponseEntity<PersonalProyectosDTO> update(@PathVariable Long codCia,
                                                      @PathVariable Long codPersonalProyecto,
                                                      @PathVariable Long codEmpleado,
                                                      @RequestBody PersonalProyectosDTO dto) {
        try {
            log.info("Actualizando personal-proyecto: codCia={}, codPersonalProyecto={}, codEmpleado={}", codCia, codPersonalProyecto, codEmpleado);
            dto.setCodCia(codCia);
            dto.setCodPersonalProyecto(codPersonalProyecto);
            dto.setCodEmpleado(codEmpleado);
            PersonalProyectosDTO updated = personalProyectosService.update(dto);
            return ResponseEntity.ok(updated);
        } catch (ServiceException e) {
            log.error("Error al actualizar personal-proyecto", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{codCia}/{codPersonalProyecto}/{codEmpleado}")
    public ResponseEntity<Void> delete(@PathVariable Long codCia,
                                       @PathVariable Long codPersonalProyecto,
                                       @PathVariable Long codEmpleado) {
        try {
            log.info("Eliminando personal-proyecto: codCia={}, codPersonalProyecto={}, codEmpleado={}", codCia, codPersonalProyecto, codEmpleado);
            PersonalProyectosId id = new PersonalProyectosId(codCia, codPersonalProyecto, codEmpleado);
            personalProyectosService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (ServiceException e) {
            log.error("Error al eliminar personal-proyecto", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
