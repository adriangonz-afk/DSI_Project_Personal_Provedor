package com.system.controller;

import com.system.dto.CargosAreasDTO;
import com.system.entity.id.CargosAreasId;
import com.system.service.CargosAreasService;
import com.system.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cargos-areas")
@CrossOrigin(origins = "*")
@Slf4j
public class CargosAreasController {

    @Autowired
    private CargosAreasService cargosAreasService;

    @GetMapping
    public ResponseEntity<List<CargosAreasDTO>> getAll() {
        try {
            log.info("Listando todos los CargosAreas");
            List<CargosAreasDTO> list = cargosAreasService.findAll();
            return ResponseEntity.ok(list);
        } catch (ServiceException e) {
            log.error("Error al listar CargosAreas", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<CargosAreasDTO> create(@RequestBody CargosAreasDTO dto) {
        try {
            log.info("Creando CargosAreas: {}", dto);
            CargosAreasDTO created = cargosAreasService.save(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (ServiceException e) {
            log.error("Error al crear CargosAreas", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{codCargo}/{codArea}/{codCia}")
    public ResponseEntity<CargosAreasDTO> getById(@PathVariable Long codCargo,
                                                  @PathVariable Long codArea,
                                                  @PathVariable Long codCia) {
        try {
            log.info("Buscando CargosAreas por ID: codCargo={}, codArea={}, codCia={}", codCargo, codArea, codCia);
            CargosAreasId id = new CargosAreasId(codCargo, codArea, codCia);
            CargosAreasDTO dto = cargosAreasService.findById(id);
            return ResponseEntity.ok(dto);
        } catch (ServiceException e) {
            log.error("Error al buscar CargosAreas por ID", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{codCargo}/{codArea}/{codCia}")
    public ResponseEntity<CargosAreasDTO> update(@PathVariable Long codCargo,
                                                 @PathVariable Long codArea,
                                                 @PathVariable Long codCia,
                                                 @RequestBody CargosAreasDTO dto) {
        try {
            log.info("Actualizando CargosAreas: codCargo={}, codArea={}, codCia={}, dto={}", codCargo, codArea, codCia, dto);
            dto.setCodCargo(codCargo);
            dto.setCodArea(codArea);
            dto.setCodCia(codCia);
            CargosAreasDTO updated = cargosAreasService.update(dto);
            return ResponseEntity.ok(updated);
        } catch (ServiceException e) {
            log.error("Error al actualizar CargosAreas", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{codCargo}/{codArea}/{codCia}")
    public ResponseEntity<Void> delete(@PathVariable Long codCargo,
                                       @PathVariable Long codArea,
                                       @PathVariable Long codCia) {
        try {
            log.info("Eliminando CargosAreas: codCargo={}, codArea={}, codCia={}", codCargo, codArea, codCia);
            CargosAreasId id = new CargosAreasId(codCargo, codArea, codCia);
            cargosAreasService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (ServiceException e) {
            log.error("Error al eliminar CargosAreas", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
