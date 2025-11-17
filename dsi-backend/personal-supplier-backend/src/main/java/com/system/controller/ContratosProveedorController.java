package com.system.controller;

import com.system.dto.ContratosProveedorDTO;
import com.system.entity.id.ContratosProveedorId;
import com.system.exception.ServiceException;
import com.system.service.ContratosProveedorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/contratos-proveedor")
@CrossOrigin(origins = "*")
public class ContratosProveedorController {

    @Autowired
    private ContratosProveedorService contratosProveedorService;

    @GetMapping
    public ResponseEntity<List<ContratosProveedorDTO>> getAll() {
        try {
            log.info("Listando todos los ContratosProveedor");
            List<ContratosProveedorDTO> lista = contratosProveedorService.findAll();
            return ResponseEntity.ok(lista);
        } catch (ServiceException e) {
            log.error("Error al listar ContratosProveedor", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<ContratosProveedorDTO> create(@RequestBody ContratosProveedorDTO dto) {
        try {
            log.info("Creando ContratosProveedor: {}", dto);
            ContratosProveedorDTO creado = contratosProveedorService.save(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(creado);
        } catch (ServiceException e) {
            log.error("Error al crear ContratosProveedor", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{codCia}/{codContrato}/{codProveedor}/{codPyto}")
    public ResponseEntity<ContratosProveedorDTO> getById(
            @PathVariable Long codCia,
            @PathVariable Long codContrato,
            @PathVariable Long codProveedor,
            @PathVariable Long codPyto) {
        try {
            ContratosProveedorId id = new ContratosProveedorId(codCia, codContrato, codProveedor, codPyto);
            log.info("Buscando ContratosProveedor con id: {}", id);
            ContratosProveedorDTO dto = contratosProveedorService.findById(id);
            return ResponseEntity.ok(dto);
        } catch (ServiceException e) {
            log.error("Error al buscar ContratosProveedor", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{codCia}/{codContrato}/{codProveedor}/{codPyto}")
    public ResponseEntity<ContratosProveedorDTO> update(
            @PathVariable Long codCia,
            @PathVariable Long codContrato,
            @PathVariable Long codProveedor,
            @PathVariable Long codPyto,
            @RequestBody ContratosProveedorDTO dto) {
        try {
            ContratosProveedorId id = new ContratosProveedorId(codCia, codContrato, codProveedor, codPyto);
            log.info("Actualizando ContratosProveedor con id: {}", id);
            // Aseguramos que el DTO tenga el ID correcto
            dto.setCodCia(codCia);
            dto.setCodContrato(codContrato);
            dto.setCodProveedor(codProveedor);
            dto.setCodPyto(codPyto);
            ContratosProveedorDTO actualizado = contratosProveedorService.update(dto);
            return ResponseEntity.ok(actualizado);
        } catch (ServiceException e) {
            log.error("Error al actualizar ContratosProveedor", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{codCia}/{codContrato}/{codProveedor}/{codPyto}")
    public ResponseEntity<Void> delete(
            @PathVariable Long codCia,
            @PathVariable Long codContrato,
            @PathVariable Long codProveedor,
            @PathVariable Long codPyto) {
        try {
            ContratosProveedorId id = new ContratosProveedorId(codCia, codContrato, codProveedor, codPyto);
            log.info("Eliminando ContratosProveedor con id: {}", id);
            contratosProveedorService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (ServiceException e) {
            log.error("Error al eliminar ContratosProveedor", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
