package com.system.controller;

import com.system.dto.CompPagocabDTO;
import com.system.entity.id.CompPagocabId;
import com.system.service.CompPagocabService;
import com.system.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagos-proveedor")
@CrossOrigin(origins = "*")
@Slf4j
public class CompPagocabController {

    @Autowired
    private CompPagocabService compPagocabService;

    @GetMapping
    public ResponseEntity<List<CompPagocabDTO>> getAll() {
        try {
            log.info("Listando todos los CompPagocab");
            List<CompPagocabDTO> list = compPagocabService.findAll();
            return ResponseEntity.ok(list);
        } catch (ServiceException e) {
            log.error("Error al listar CompPagocab", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<CompPagocabDTO> create(@RequestBody CompPagocabDTO dto) {
        try {
            log.info("Creando CompPagocab: {}", dto);
            CompPagocabDTO created = compPagocabService.save(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (ServiceException e) {
            log.error("Error al crear CompPagocab", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{codCia}/{codProveedor}/{nroCP}")
    public ResponseEntity<CompPagocabDTO> getById(@PathVariable Long codCia,
                                                  @PathVariable Long codProveedor,
                                                  @PathVariable String nroCP) {
        try {
            log.info("Buscando CompPagocab por ID: codCia={}, codProveedor={}, nroCP={}", codCia, codProveedor, nroCP);
            CompPagocabId id = new CompPagocabId(codCia, codProveedor, nroCP);
            CompPagocabDTO dto = compPagocabService.findById(id);
            return ResponseEntity.ok(dto);
        } catch (ServiceException e) {
            log.error("Error al buscar CompPagocab por ID", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{codCia}/{codProveedor}/{nroCP}")
    public ResponseEntity<CompPagocabDTO> update(@PathVariable Long codCia,
                                                 @PathVariable Long codProveedor,
                                                 @PathVariable String nroCP,
                                                 @RequestBody CompPagocabDTO dto) {
        try {
            log.info("Actualizando CompPagocab: codCia={}, codProveedor={}, nroCP={}, dto={}", codCia, codProveedor, nroCP, dto);
            dto.setCodCia(codCia);
            dto.setCodProveedor(codProveedor);
            dto.setNroCP(nroCP);
            CompPagocabDTO updated = compPagocabService.update(dto);
            return ResponseEntity.ok(updated);
        } catch (ServiceException e) {
            log.error("Error al actualizar CompPagocab", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{codCia}/{codProveedor}/{nroCP}")
    public ResponseEntity<Void> delete(@PathVariable Long codCia,
                                       @PathVariable Long codProveedor,
                                       @PathVariable String nroCP) {
        try {
            log.info("Eliminando CompPagocab: codCia={}, codProveedor={}, nroCP={}", codCia, codProveedor, nroCP);
            CompPagocabId id = new CompPagocabId(codCia, codProveedor, nroCP);
            compPagocabService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (ServiceException e) {
            log.error("Error al eliminar CompPagocab", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
