package com.system.controller;

import com.system.dto.EmpleadoDTO;
import com.system.dto.EmpleadoCompletoDTO;
import com.system.entity.id.EmpleadoId;
import com.system.service.EmpleadoService;
import com.system.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/personal")
@CrossOrigin(origins = "*")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping
    public ResponseEntity<List<EmpleadoDTO>> getAll() {
        try {
            log.info("Listando todos los empleados");
            List<EmpleadoDTO> empleados = empleadoService.findAll();
            return ResponseEntity.ok(empleados);
        } catch (ServiceException e) {
            log.error("Error al listar empleados", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<EmpleadoDTO> create(@RequestBody EmpleadoDTO dto) {
        try {
            log.info("Creando empleado: {}", dto);
            EmpleadoDTO saved = empleadoService.save(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (ServiceException e) {
            log.error("Error al crear empleado", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/{codCia}/{codEmpleado}")
    public ResponseEntity<EmpleadoDTO> getById(@PathVariable Long codCia, @PathVariable Long codEmpleado) {
        try {
            log.info("Buscando empleado con codCia={} y codEmpleado={}", codCia, codEmpleado);
            EmpleadoId id = new EmpleadoId(codCia, codEmpleado);
            EmpleadoDTO dto = empleadoService.findById(id);
            return ResponseEntity.ok(dto);
        } catch (ServiceException e) {
            log.error("Empleado no encontrado: codCia={}, codEmpleado={}", codCia, codEmpleado);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{codCia}/{codEmpleado}")
    public ResponseEntity<EmpleadoDTO> update(@PathVariable Long codCia, @PathVariable Long codEmpleado, @RequestBody EmpleadoDTO dto) {
        try {
            log.info("Actualizando empleado codCia={}, codEmpleado={}", codCia, codEmpleado);
            dto.setCodCia(codCia);
            dto.setCodEmpleado(codEmpleado);
            EmpleadoDTO updated = empleadoService.update(dto);
            return ResponseEntity.ok(updated);
        } catch (ServiceException e) {
            log.error("Error al actualizar empleado codCia={}, codEmpleado={}", codCia, codEmpleado);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{codCia}/{codEmpleado}")
    public ResponseEntity<Void> delete(@PathVariable Long codCia, @PathVariable Long codEmpleado) {
        try {
            log.info("Eliminando empleado codCia={}, codEmpleado={}", codCia, codEmpleado);
            EmpleadoId id = new EmpleadoId(codCia, codEmpleado);
            empleadoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (ServiceException e) {
            log.error("Error al eliminar empleado codCia={}, codEmpleado={}", codCia, codEmpleado);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{codCia}/{codEmpleado}/completo")
    public ResponseEntity<EmpleadoCompletoDTO> getByIdCompleto(@PathVariable Long codCia, @PathVariable Long codEmpleado) {
        try {
            log.info("Buscando información completa del empleado con codCia={} y codEmpleado={}", codCia, codEmpleado);
            EmpleadoId id = new EmpleadoId(codCia, codEmpleado);
            EmpleadoCompletoDTO dto = empleadoService.findByIdCompleto(id);
            return ResponseEntity.ok(dto);
        } catch (ServiceException e) {
            log.error("Empleado no encontrado: codCia={}, codEmpleado={}", codCia, codEmpleado);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/completos")
    public ResponseEntity<List<EmpleadoCompletoDTO>> getAllCompletos() {
        try {
            log.info("Listando todos los empleados completos");
            List<EmpleadoCompletoDTO> empleados = empleadoService.findAllCompletos();
            return ResponseEntity.ok(empleados);
        } catch (ServiceException e) {
            log.error("Error al listar empleados completos", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
