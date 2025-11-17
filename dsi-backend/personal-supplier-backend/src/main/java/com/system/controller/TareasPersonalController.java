package com.system.controller;

import com.system.dto.TareasPersonalDTO;
import com.system.entity.id.TareasPersonalId;
import com.system.exception.ServiceException;
import com.system.service.TareasPersonalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tareas-personal")
@CrossOrigin(origins = "*")
@Slf4j
public class TareasPersonalController {

    @Autowired
    private TareasPersonalService tareasPersonalService;

    @GetMapping
    public ResponseEntity<List<TareasPersonalDTO>> getAll() {
        try {
            log.info("Listando todos los TareasPersonal");
            List<TareasPersonalDTO> list = tareasPersonalService.findAll();
            return ResponseEntity.ok(list);
        } catch (ServiceException e) {
            log.error("Error al listar TareasPersonal", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<TareasPersonalDTO> create(@RequestBody TareasPersonalDTO dto) {
        try {
            log.info("Creando TareasPersonal: {}", dto);
            TareasPersonalDTO created = tareasPersonalService.save(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (ServiceException e) {
            log.error("Error al crear TareasPersonal", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{codCia}/{codTarea}/{codPyto}/{codEmpleado}")
    public ResponseEntity<TareasPersonalDTO> getById(@PathVariable Long codCia,
                                                    @PathVariable Long codTarea,
                                                    @PathVariable Long codPyto,
                                                    @PathVariable Long codEmpleado) {
        try {
            log.info("Buscando TareasPersonal con id: codCia={}, codTarea={}, codPyto={}, codEmpleado={}", codCia, codTarea, codPyto, codEmpleado);
            TareasPersonalId id = new TareasPersonalId(codCia, codTarea, codPyto, codEmpleado);
            TareasPersonalDTO dto = tareasPersonalService.findById(id);
            return ResponseEntity.ok(dto);
        } catch (ServiceException e) {
            log.error("Error al buscar TareasPersonal", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{codCia}/{codTarea}/{codPyto}/{codEmpleado}")
    public ResponseEntity<TareasPersonalDTO> update(@PathVariable Long codCia,
                                                  @PathVariable Long codTarea,
                                                  @PathVariable Long codPyto,
                                                  @PathVariable Long codEmpleado,
                                                  @RequestBody TareasPersonalDTO dto) {
        try {
            log.info("Actualizando TareasPersonal con id: codCia={}, codTarea={}, codPyto={}, codEmpleado={}", codCia, codTarea, codPyto, codEmpleado);
            // Asegurarse de que el DTO tenga los valores correctos de ID
            dto.setCodCia(codCia);
            dto.setCodTarea(codTarea);
            dto.setCodPyto(codPyto);
            dto.setCodEmpleado(codEmpleado);
            TareasPersonalDTO updated = tareasPersonalService.update(dto);
            return ResponseEntity.ok(updated);
        } catch (ServiceException e) {
            log.error("Error al actualizar TareasPersonal", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{codCia}/{codTarea}/{codPyto}/{codEmpleado}")
    public ResponseEntity<Void> delete(@PathVariable Long codCia,
                                       @PathVariable Long codTarea,
                                       @PathVariable Long codPyto,
                                       @PathVariable Long codEmpleado) {
        try {
            log.info("Eliminando TareasPersonal con id: codCia={}, codTarea={}, codPyto={}, codEmpleado={}", codCia, codTarea, codPyto, codEmpleado);
            TareasPersonalId id = new TareasPersonalId(codCia, codTarea, codPyto, codEmpleado);
            tareasPersonalService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (ServiceException e) {
            log.error("Error al eliminar TareasPersonal", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}