package com.system.controller;

import com.system.dto.ServiciosProveedorDTO;
import com.system.entity.id.ServiciosProveedorId;
import com.system.exception.ServiceException;
import com.system.service.ServiciosProveedorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servicios-proveedor")
@CrossOrigin(origins = "*")
@Slf4j
public class ServiciosProveedorController {

    @Autowired
    private ServiciosProveedorService serviciosProveedorService;

    @GetMapping
    public ResponseEntity<List<ServiciosProveedorDTO>> getAll() {
        try {
            log.info("Listando todos los ServiciosProveedor");
            List<ServiciosProveedorDTO> list = serviciosProveedorService.findAll();
            return ResponseEntity.ok(list);
        } catch (ServiceException e) {
            log.error("Error al listar ServiciosProveedor", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<ServiciosProveedorDTO> create(@RequestBody ServiciosProveedorDTO dto) {
        try {
            log.info("Creando ServiciosProveedor: {}", dto);
            ServiciosProveedorDTO created = serviciosProveedorService.save(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (ServiceException e) {
            log.error("Error al crear ServiciosProveedor", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{codCia}/{codServicio}/{codProveedor}")
    public ResponseEntity<ServiciosProveedorDTO> getById(@PathVariable Long codCia,
                                                        @PathVariable Long codServicio,
                                                        @PathVariable Long codProveedor) {
        try {
            log.info("Buscando ServiciosProveedor con id: codCia={}, codServicio={}, codProveedor={}", codCia, codServicio, codProveedor);
            ServiciosProveedorId id = new ServiciosProveedorId(codCia, codServicio, codProveedor);
            ServiciosProveedorDTO dto = serviciosProveedorService.findById(id);
            return ResponseEntity.ok(dto);
        } catch (ServiceException e) {
            log.error("Error al buscar ServiciosProveedor", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{codCia}/{codServicio}/{codProveedor}")
    public ResponseEntity<ServiciosProveedorDTO> update(@PathVariable Long codCia,
                                                      @PathVariable Long codServicio,
                                                      @PathVariable Long codProveedor,
                                                      @RequestBody ServiciosProveedorDTO dto) {
        try {
            log.info("Actualizando ServiciosProveedor con id: codCia={}, codServicio={}, codProveedor={}", codCia, codServicio, codProveedor);
            // Asegurarse de que el DTO tenga los valores correctos de ID
            dto.setCodCia(codCia);
            dto.setCodServicio(codServicio);
            dto.setCodProveedor(codProveedor);
            ServiciosProveedorDTO updated = serviciosProveedorService.update(dto);
            return ResponseEntity.ok(updated);
        } catch (ServiceException e) {
            log.error("Error al actualizar ServiciosProveedor", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{codCia}/{codServicio}/{codProveedor}")
    public ResponseEntity<Void> delete(@PathVariable Long codCia,
                                       @PathVariable Long codServicio,
                                       @PathVariable Long codProveedor) {
        try {
            log.info("Eliminando ServiciosProveedor con id: codCia={}, codServicio={}, codProveedor={}", codCia, codServicio, codProveedor);
            ServiciosProveedorId id = new ServiciosProveedorId(codCia, codServicio, codProveedor);
            serviciosProveedorService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (ServiceException e) {
            log.error("Error al eliminar ServiciosProveedor", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}