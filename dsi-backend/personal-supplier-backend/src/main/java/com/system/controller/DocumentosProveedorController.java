package com.system.controller;

import com.system.dto.DocumentosProveedorDTO;
import com.system.entity.id.DocumentosProveedorId;
import com.system.exception.ServiceException;
import com.system.service.DocumentosProveedorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/documentos-proveedor")
@CrossOrigin(origins = "*")
public class DocumentosProveedorController {

    @Autowired
    private DocumentosProveedorService documentosProveedorService;

    @GetMapping
    public ResponseEntity<List<DocumentosProveedorDTO>> getAll() {
        try {
            log.info("Listando todos los DocumentosProveedor");
            List<DocumentosProveedorDTO> lista = documentosProveedorService.findAll();
            return ResponseEntity.ok(lista);
        } catch (ServiceException e) {
            log.error("Error al listar DocumentosProveedor", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<DocumentosProveedorDTO> create(@RequestBody DocumentosProveedorDTO dto) {
        try {
            log.info("Creando DocumentosProveedor: {}", dto);
            DocumentosProveedorDTO creado = documentosProveedorService.save(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(creado);
        } catch (ServiceException e) {
            log.error("Error al crear DocumentosProveedor", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{codCia}/{codDocumento}/{codProveedor}")
    public ResponseEntity<DocumentosProveedorDTO> getById(
            @PathVariable Long codCia,
            @PathVariable Long codDocumento,
            @PathVariable Long codProveedor) {
        try {
            DocumentosProveedorId id = new DocumentosProveedorId(codCia, codDocumento, codProveedor);
            log.info("Buscando DocumentosProveedor con id: {}", id);
            DocumentosProveedorDTO dto = documentosProveedorService.findById(id);
            return ResponseEntity.ok(dto);
        } catch (ServiceException e) {
            log.error("Error al buscar DocumentosProveedor", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{codCia}/{codDocumento}/{codProveedor}")
    public ResponseEntity<DocumentosProveedorDTO> update(
            @PathVariable Long codCia,
            @PathVariable Long codDocumento,
            @PathVariable Long codProveedor,
            @RequestBody DocumentosProveedorDTO dto) {
        try {
            DocumentosProveedorId id = new DocumentosProveedorId(codCia, codDocumento, codProveedor);
            log.info("Actualizando DocumentosProveedor con id: {}", id);
            // Aseguramos que el DTO tenga el ID correcto
            dto.setCodCia(codCia);
            dto.setCodDocumento(codDocumento);
            dto.setCodProveedor(codProveedor);
            DocumentosProveedorDTO actualizado = documentosProveedorService.update(dto);
            return ResponseEntity.ok(actualizado);
        } catch (ServiceException e) {
            log.error("Error al actualizar DocumentosProveedor", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{codCia}/{codDocumento}/{codProveedor}")
    public ResponseEntity<Void> delete(
            @PathVariable Long codCia,
            @PathVariable Long codDocumento,
            @PathVariable Long codProveedor) {
        try {
            DocumentosProveedorId id = new DocumentosProveedorId(codCia, codDocumento, codProveedor);
            log.info("Eliminando DocumentosProveedor con id: {}", id);
            documentosProveedorService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (ServiceException e) {
            log.error("Error al eliminar DocumentosProveedor", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
