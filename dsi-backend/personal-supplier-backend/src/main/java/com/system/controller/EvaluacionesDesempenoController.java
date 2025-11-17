package com.system.controller;

import com.system.dto.EvaluacionesDesempenoDTO;
import com.system.entity.id.EvaluacionesDesempenoId;
import com.system.service.EvaluacionesDesempenoService;
import com.system.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evaluaciones-personal")
@CrossOrigin(origins = "*")
@Slf4j
public class EvaluacionesDesempenoController {

    @Autowired
    private EvaluacionesDesempenoService evaluacionesDesempenoService;

    @GetMapping
    public ResponseEntity<List<EvaluacionesDesempenoDTO>> getAll() {
        try {
            log.info("Obteniendo todas las evaluaciones de desempeño");
            List<EvaluacionesDesempenoDTO> list = evaluacionesDesempenoService.findAll();
            return ResponseEntity.ok(list);
        } catch (ServiceException e) {
            log.error("Error al obtener todas las evaluaciones de desempeño", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<EvaluacionesDesempenoDTO> create(@RequestBody EvaluacionesDesempenoDTO dto) {
        try {
            log.info("Creando evaluación de desempeño: {}", dto);
            EvaluacionesDesempenoDTO created = evaluacionesDesempenoService.save(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (ServiceException e) {
            log.error("Error al crear evaluación de desempeño", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{codCia}/{codEvaluacion}/{codEmpleado}/{codPyto}")
    public ResponseEntity<EvaluacionesDesempenoDTO> getById(@PathVariable Long codCia,
                                                           @PathVariable Long codEvaluacion,
                                                           @PathVariable Long codEmpleado,
                                                           @PathVariable Long codPyto) {
        try {
            log.info("Buscando evaluación de desempeño por id: codCia={}, codEvaluacion={}, codEmpleado={}, codPyto={}", codCia, codEvaluacion, codEmpleado, codPyto);
            EvaluacionesDesempenoId id = new EvaluacionesDesempenoId(codCia, codEvaluacion, codEmpleado, codPyto);
            EvaluacionesDesempenoDTO dto = evaluacionesDesempenoService.findById(id);
            return ResponseEntity.ok(dto);
        } catch (ServiceException e) {
            log.error("Error al buscar evaluación de desempeño por id", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{codCia}/{codEvaluacion}/{codEmpleado}/{codPyto}")
    public ResponseEntity<EvaluacionesDesempenoDTO> update(@PathVariable Long codCia,
                                                          @PathVariable Long codEvaluacion,
                                                          @PathVariable Long codEmpleado,
                                                          @PathVariable Long codPyto,
                                                          @RequestBody EvaluacionesDesempenoDTO dto) {
        try {
            log.info("Actualizando evaluación de desempeño: codCia={}, codEvaluacion={}, codEmpleado={}, codPyto={}", codCia, codEvaluacion, codEmpleado, codPyto);
            dto.setCodCia(codCia);
            dto.setCodEvaluacion(codEvaluacion);
            dto.setCodEmpleado(codEmpleado);
            dto.setCodPyto(codPyto);
            EvaluacionesDesempenoDTO updated = evaluacionesDesempenoService.update(dto);
            return ResponseEntity.ok(updated);
        } catch (ServiceException e) {
            log.error("Error al actualizar evaluación de desempeño", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{codCia}/{codEvaluacion}/{codEmpleado}/{codPyto}")
    public ResponseEntity<Void> delete(@PathVariable Long codCia,
                                       @PathVariable Long codEvaluacion,
                                       @PathVariable Long codEmpleado,
                                       @PathVariable Long codPyto) {
        try {
            log.info("Eliminando evaluación de desempeño: codCia={}, codEvaluacion={}, codEmpleado={}, codPyto={}", codCia, codEvaluacion, codEmpleado, codPyto);
            EvaluacionesDesempenoId id = new EvaluacionesDesempenoId(codCia, codEvaluacion, codEmpleado, codPyto);
            evaluacionesDesempenoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (ServiceException e) {
            log.error("Error al eliminar evaluación de desempeño", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
