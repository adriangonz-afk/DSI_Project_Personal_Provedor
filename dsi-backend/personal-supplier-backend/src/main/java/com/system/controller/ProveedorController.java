package com.system.controller;

import com.system.dto.ProveedorDTO;
import com.system.dto.ProveedorCompletoDTO;
import com.system.entity.id.ProveedorId;
import com.system.service.ProveedorService;
import com.system.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/proveedores")
@CrossOrigin(origins = "*")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @GetMapping
    public ResponseEntity<List<ProveedorDTO>> getAll() {
        try {
            log.info("Listando todos los proveedores");
            List<ProveedorDTO> proveedores = proveedorService.findAll();
            return ResponseEntity.ok(proveedores);
        } catch (ServiceException e) {
            log.error("Error al listar proveedores", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<ProveedorDTO> create(@RequestBody ProveedorDTO dto) {
        try {
            log.info("Creando proveedor: {}", dto);
            ProveedorDTO saved = proveedorService.save(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (ServiceException e) {
            log.error("Error al crear proveedor", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/{codCia}/{codProveedor}")
    public ResponseEntity<ProveedorDTO> getById(@PathVariable Long codCia, @PathVariable Long codProveedor) {
        try {
            log.info("Buscando proveedor con codCia={} y codProveedor={}", codCia, codProveedor);
            ProveedorId id = new ProveedorId(codCia, codProveedor);
            ProveedorDTO dto = proveedorService.findById(id);
            return ResponseEntity.ok(dto);
        } catch (ServiceException e) {
            log.error("Proveedor no encontrado: codCia={}, codProveedor={}", codCia, codProveedor);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{codCia}/{codProveedor}")
    public ResponseEntity<ProveedorDTO> update(@PathVariable Long codCia, @PathVariable Long codProveedor, @RequestBody ProveedorDTO dto) {
        try {
            log.info("Actualizando proveedor codCia={}, codProveedor={}", codCia, codProveedor);
            dto.setCodCia(codCia);
            dto.setCodProveedor(codProveedor);
            ProveedorDTO updated = proveedorService.update(dto);
            return ResponseEntity.ok(updated);
        } catch (ServiceException e) {
            log.error("Error al actualizar proveedor codCia={}, codProveedor={}", codCia, codProveedor);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{codCia}/{codProveedor}")
    public ResponseEntity<Void> delete(@PathVariable Long codCia, @PathVariable Long codProveedor) {
        try {
            log.info("Eliminando proveedor codCia={}, codProveedor={}", codCia, codProveedor);
            ProveedorId id = new ProveedorId(codCia, codProveedor);
            proveedorService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (ServiceException e) {
            log.error("Error al eliminar proveedor codCia={}, codProveedor={}", codCia, codProveedor);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{codCia}/{codProveedor}/completo")
    public ResponseEntity<ProveedorCompletoDTO> getByIdCompleto(@PathVariable Long codCia, @PathVariable Long codProveedor) {
        try {
            log.info("Buscando información completa del proveedor con codCia={} y codProveedor={}", codCia, codProveedor);
            ProveedorId id = new ProveedorId(codCia, codProveedor);
            ProveedorCompletoDTO dto = proveedorService.findByIdCompleto(id);
            return ResponseEntity.ok(dto);
        } catch (ServiceException e) {
            log.error("Proveedor no encontrado: codCia={}, codProveedor={}", codCia, codProveedor);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/completos")
    public ResponseEntity<List<ProveedorCompletoDTO>> getAllCompletos() {
        try {
            log.info("Listando todos los proveedores completos");
            List<ProveedorCompletoDTO> proveedores = proveedorService.findAllCompletos();
            return ResponseEntity.ok(proveedores);
        } catch (ServiceException e) {
            log.error("Error al listar proveedores completos", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
