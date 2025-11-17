package com.system.controller;

import com.system.dto.ActividadesProveedorDTO;
import com.system.entity.id.ActividadesProveedorId;
import com.system.service.ActividadesProveedorService;
import com.system.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/actividades-proveedor")
@CrossOrigin(origins = "*")
public class ActividadesProveedorController {

    @Autowired
    private ActividadesProveedorService actividadesProveedorService;

    @GetMapping
    public ResponseEntity<List<ActividadesProveedorDTO>> getAll() {
        try {
            log.info("Listando todas las actividades de proveedor");
            List<ActividadesProveedorDTO> actividades = actividadesProveedorService.findAll();
            return ResponseEntity.ok(actividades);
        } catch (ServiceException e) {
            log.error("Error al listar actividades de proveedor", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<ActividadesProveedorDTO> create(@RequestBody ActividadesProveedorDTO dto) {
        try {
            log.info("Creando actividad de proveedor: {}", dto);
            ActividadesProveedorDTO saved = actividadesProveedorService.save(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (ServiceException e) {
            log.error("Error al crear actividad de proveedor", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/{codCIA}/{codActividad}/{codProveedor}/{codPyto}/{codContrato}")
    public ResponseEntity<ActividadesProveedorDTO> getById(
            @PathVariable Long codCIA,
            @PathVariable Long codActividad,
            @PathVariable Long codProveedor,
            @PathVariable Long codPyto,
            @PathVariable Long codContrato) {
        try {
            log.info("Buscando actividad de proveedor con codCIA={}, codActividad={}, codProveedor={}, codPyto={}, codContrato={}", codCIA, codActividad, codProveedor, codPyto, codContrato);
            ActividadesProveedorId id = new ActividadesProveedorId(codCIA, codActividad, codProveedor, codPyto, codContrato);
            ActividadesProveedorDTO dto = actividadesProveedorService.findById(id);
            return ResponseEntity.ok(dto);
        } catch (ServiceException e) {
            log.error("Actividad de proveedor no encontrada: codCIA={}, codActividad={}, codProveedor={}, codPyto={}, codContrato={}", codCIA, codActividad, codProveedor, codPyto, codContrato);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{codCIA}/{codActividad}/{codProveedor}/{codPyto}/{codContrato}")
    public ResponseEntity<ActividadesProveedorDTO> update(
            @PathVariable Long codCIA,
            @PathVariable Long codActividad,
            @PathVariable Long codProveedor,
            @PathVariable Long codPyto,
            @PathVariable Long codContrato,
            @RequestBody ActividadesProveedorDTO dto) {
        try {
            log.info("Actualizando actividad de proveedor codCIA={}, codActividad={}, codProveedor={}, codPyto={}, codContrato={}", codCIA, codActividad, codProveedor, codPyto, codContrato);
            dto.setCodCIA(codCIA);
            dto.setCodActividad(codActividad);
            dto.setCodProveedor(codProveedor);
            dto.setCodPyto(codPyto);
            dto.setCodContrato(codContrato);
            ActividadesProveedorDTO updated = actividadesProveedorService.update(dto);
            return ResponseEntity.ok(updated);
        } catch (ServiceException e) {
            log.error("Error al actualizar actividad de proveedor codCIA={}, codActividad={}, codProveedor={}, codPyto={}, codContrato={}", codCIA, codActividad, codProveedor, codPyto, codContrato);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{codCIA}/{codActividad}/{codProveedor}/{codPyto}/{codContrato}")
    public ResponseEntity<Void> delete(
            @PathVariable Long codCIA,
            @PathVariable Long codActividad,
            @PathVariable Long codProveedor,
            @PathVariable Long codPyto,
            @PathVariable Long codContrato) {
        try {
            log.info("Eliminando actividad de proveedor codCIA={}, codActividad={}, codProveedor={}, codPyto={}, codContrato={}", codCIA, codActividad, codProveedor, codPyto, codContrato);
            ActividadesProveedorId id = new ActividadesProveedorId(codCIA, codActividad, codProveedor, codPyto, codContrato);
            actividadesProveedorService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (ServiceException e) {
            log.error("Error al eliminar actividad de proveedor codCIA={}, codActividad={}, codProveedor={}, codPyto={}, codContrato={}", codCIA, codActividad, codProveedor, codPyto, codContrato);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
