package com.system.controller;

import com.system.dto.ClienteDTO;
import com.system.entity.id.ClienteId;
import com.system.exception.ServiceException;
import com.system.service.ClienteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*")
@Slf4j
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> getAll() {
        log.info("Obteniendo todos los clientes");
        try {
            List<ClienteDTO> clientes = clienteService.findAll();
            return ResponseEntity.ok(clientes);
        } catch (Exception e) {
            log.error("Error al obtener clientes", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> create(@RequestBody ClienteDTO dto) {
        log.info("Creando cliente: {}", dto);
        try {
            ClienteDTO created = clienteService.save(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (ServiceException e) {
            log.error("Error al crear cliente", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            log.error("Error inesperado al crear cliente", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{codCia}/{codCliente}")
    public ResponseEntity<ClienteDTO> getById(@PathVariable Long codCia, @PathVariable Long codCliente) {
        log.info("Buscando cliente con codCia={} y codCliente={}", codCia, codCliente);
        try {
            ClienteId id = new ClienteId(codCia, codCliente);
            ClienteDTO dto = clienteService.findById(id);
            return ResponseEntity.ok(dto);
        } catch (ServiceException e) {
            log.warn("Cliente no encontrado: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            log.error("Error al buscar cliente", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{codCia}/{codCliente}")
    public ResponseEntity<ClienteDTO> update(@PathVariable Long codCia, @PathVariable Long codCliente, @RequestBody ClienteDTO dto) {
        log.info("Actualizando cliente codCia={}, codCliente={}", codCia, codCliente);
        try {
            dto.setCodCia(codCia);
            dto.setCodCliente(codCliente);
            ClienteDTO updated = clienteService.update(dto);
            return ResponseEntity.ok(updated);
        } catch (ServiceException e) {
            log.warn("Cliente no encontrado para actualizar: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            log.error("Error al actualizar cliente", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{codCia}/{codCliente}")
    public ResponseEntity<Void> delete(@PathVariable Long codCia, @PathVariable Long codCliente) {
        log.info("Eliminando cliente codCia={}, codCliente={}", codCia, codCliente);
        try {
            ClienteId id = new ClienteId(codCia, codCliente);
            clienteService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (ServiceException e) {
            log.warn("Cliente no encontrado para eliminar: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            log.error("Error al eliminar cliente", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
