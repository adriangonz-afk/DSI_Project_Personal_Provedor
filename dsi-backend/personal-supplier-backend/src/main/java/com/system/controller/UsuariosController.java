package com.system.controller;

import com.system.dto.UsuariosDTO;
import com.system.entity.id.UsuariosId;
import com.system.exception.ServiceException;
import com.system.service.UsuariosService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
@Slf4j
public class UsuariosController {

    @Autowired
    private UsuariosService usuariosService;

    @GetMapping
    public ResponseEntity<List<UsuariosDTO>> getAll() {
        try {
            log.info("Listando todos los Usuarios");
            List<UsuariosDTO> list = usuariosService.findAll();
            return ResponseEntity.ok(list);
        } catch (ServiceException e) {
            log.error("Error al listar Usuarios", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<UsuariosDTO> create(@RequestBody UsuariosDTO dto) {
        try {
            log.info("Creando Usuarios: {}", dto);
            UsuariosDTO created = usuariosService.save(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (ServiceException e) {
            log.error("Error al crear Usuarios", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{codCia}/{codUsuario}")
    public ResponseEntity<UsuariosDTO> getById(@PathVariable Long codCia,
                                              @PathVariable Long codUsuario) {
        try {
            log.info("Buscando Usuarios con id: codCia={}, codUsuario={}", codCia, codUsuario);
            UsuariosId id = new UsuariosId(codCia, codUsuario);
            UsuariosDTO dto = usuariosService.findById(id);
            return ResponseEntity.ok(dto);
        } catch (ServiceException e) {
            log.error("Error al buscar Usuarios", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{codCia}/{codUsuario}")
    public ResponseEntity<UsuariosDTO> update(@PathVariable Long codCia,
                                            @PathVariable Long codUsuario,
                                            @RequestBody UsuariosDTO dto) {
        try {
            log.info("Actualizando Usuarios con id: codCia={}, codUsuario={}", codCia, codUsuario);
            // Asegurarse de que el DTO tenga los valores correctos de ID
            dto.setCodCia(codCia);
            dto.setCodUsuario(codUsuario);
            UsuariosDTO updated = usuariosService.update(dto);
            return ResponseEntity.ok(updated);
        } catch (ServiceException e) {
            log.error("Error al actualizar Usuarios", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{codCia}/{codUsuario}")
    public ResponseEntity<Void> delete(@PathVariable Long codCia,
                                       @PathVariable Long codUsuario) {
        try {
            log.info("Eliminando Usuarios con id: codCia={}, codUsuario={}", codCia, codUsuario);
            UsuariosId id = new UsuariosId(codCia, codUsuario);
            usuariosService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (ServiceException e) {
            log.error("Error al eliminar Usuarios", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}