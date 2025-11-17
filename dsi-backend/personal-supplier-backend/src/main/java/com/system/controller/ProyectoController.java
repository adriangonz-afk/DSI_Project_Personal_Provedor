package com.system.controller;

import com.system.dto.ProyectoDTO;
import com.system.dto.ProyectoCompletoDTO;
import com.system.entity.id.ProyectoId;
import com.system.service.ProyectoService;
import com.system.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/proyectos")
@CrossOrigin(origins = "*")
public class ProyectoController {

    @Autowired
    private ProyectoService proyectoService;

    @GetMapping
    public ResponseEntity<List<ProyectoDTO>> getAll() {
        try {
            List<ProyectoDTO> list = proyectoService.findAll();
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            log.error("Error al listar proyectos", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<ProyectoDTO> create(@RequestBody ProyectoDTO dto) {
        try {
            ProyectoDTO saved = proyectoService.save(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (ServiceException e) {
            log.error("Error al crear proyecto", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            log.error("Error inesperado al crear proyecto", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{codCia}/{codPyto}")
    public ResponseEntity<ProyectoDTO> getById(@PathVariable Long codCia, @PathVariable Long codPyto) {
        try {
            ProyectoId id = new ProyectoId(codCia, codPyto);
            ProyectoDTO dto = proyectoService.findById(id);
            if (dto == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            log.error("Error al obtener proyecto", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{codCia}/{codPyto}")
    public ResponseEntity<ProyectoDTO> update(@PathVariable Long codCia, @PathVariable Long codPyto, @RequestBody ProyectoDTO dto) {
        try {
            dto.setCodCia(codCia);
            dto.setCodPyto(codPyto);
            ProyectoDTO updated = proyectoService.update(dto);
            return ResponseEntity.ok(updated);
        } catch (ServiceException e) {
            log.error("Error al actualizar proyecto", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            log.error("Error inesperado al actualizar proyecto", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{codCia}/{codPyto}")
    public ResponseEntity<Void> delete(@PathVariable Long codCia, @PathVariable Long codPyto) {
        try {
            ProyectoId id = new ProyectoId(codCia, codPyto);
            boolean deleted = proyectoService.delete(id);
            if (!deleted) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            log.error("Error al eliminar proyecto", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Endpoints para la funcionalidad completa
    
    @GetMapping("/completos")
    public ResponseEntity<List<ProyectoCompletoDTO>> getAllCompletos() {
        try {
            List<ProyectoCompletoDTO> list = proyectoService.findAllCompletos();
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            log.error("Error al listar proyectos completos", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{codCia}/{codPyto}/completo")
    public ResponseEntity<ProyectoCompletoDTO> getByIdCompleto(@PathVariable Long codCia, @PathVariable Long codPyto) {
        try {
            ProyectoCompletoDTO dto = proyectoService.findByIdCompleto(codCia, codPyto);
            return ResponseEntity.ok(dto);
        } catch (ServiceException e) {
            log.error("Error al obtener proyecto completo", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            log.error("Error inesperado al obtener proyecto completo", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
