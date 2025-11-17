package com.system.controller;

import com.system.dto.CompPagoempleadoDTO;
import com.system.entity.id.CompPagoempleadoId;
import com.system.service.CompPagoempleadoService;
import com.system.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagos-personal")
@CrossOrigin(origins = "*")
@Slf4j
public class CompPagoempleadoController {

    @Autowired
    private CompPagoempleadoService compPagoempleadoService;

    @GetMapping
    public ResponseEntity<List<CompPagoempleadoDTO>> getAll() {
        try {
            log.info("Listando todos los CompPagoempleado");
            List<CompPagoempleadoDTO> list = compPagoempleadoService.findAll();
            return ResponseEntity.ok(list);
        } catch (ServiceException e) {
            log.error("Error al listar CompPagoempleado", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<CompPagoempleadoDTO> create(@RequestBody CompPagoempleadoDTO dto) {
        try {
            log.info("Creando CompPagoempleado: {}", dto);
            CompPagoempleadoDTO created = compPagoempleadoService.save(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (ServiceException e) {
            log.error("Error al crear CompPagoempleado", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{codCia}/{codEmpleado}/{nroCP}")
    public ResponseEntity<CompPagoempleadoDTO> getById(@PathVariable Long codCia,
                                                       @PathVariable Long codEmpleado,
                                                       @PathVariable String nroCP) {
        try {
            log.info("Buscando CompPagoempleado por ID: codCia={}, codEmpleado={}, nroCP={}", codCia, codEmpleado, nroCP);
            CompPagoempleadoId id = new CompPagoempleadoId(codCia, codEmpleado, nroCP);
            CompPagoempleadoDTO dto = compPagoempleadoService.findById(id);
            return ResponseEntity.ok(dto);
        } catch (ServiceException e) {
            log.error("Error al buscar CompPagoempleado por ID", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{codCia}/{codEmpleado}/{nroCP}")
    public ResponseEntity<CompPagoempleadoDTO> update(@PathVariable Long codCia,
                                                      @PathVariable Long codEmpleado,
                                                      @PathVariable String nroCP,
                                                      @RequestBody CompPagoempleadoDTO dto) {
        try {
            log.info("Actualizando CompPagoempleado: codCia={}, codEmpleado={}, nroCP={}, dto={}", codCia, codEmpleado, nroCP, dto);
            dto.setCodCia(codCia);
            dto.setCodEmpleado(codEmpleado);
            dto.setNroCP(nroCP);
            CompPagoempleadoDTO updated = compPagoempleadoService.update(dto);
            return ResponseEntity.ok(updated);
        } catch (ServiceException e) {
            log.error("Error al actualizar CompPagoempleado", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{codCia}/{codEmpleado}/{nroCP}")
    public ResponseEntity<Void> delete(@PathVariable Long codCia,
                                       @PathVariable Long codEmpleado,
                                       @PathVariable String nroCP) {
        try {
            log.info("Eliminando CompPagoempleado: codCia={}, codEmpleado={}, nroCP={}", codCia, codEmpleado, nroCP);
            CompPagoempleadoId id = new CompPagoempleadoId(codCia, codEmpleado, nroCP);
            compPagoempleadoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (ServiceException e) {
            log.error("Error al eliminar CompPagoempleado", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
