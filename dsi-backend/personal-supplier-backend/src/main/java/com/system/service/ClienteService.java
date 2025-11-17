package com.system.service;

import com.system.dto.ClienteDTO;
import com.system.entity.Cliente;
import com.system.entity.id.ClienteId;
import com.system.mapper.ClienteMapper;
import com.system.repository.ClienteRepository;
import com.system.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteMapper clienteMapper;

    public ClienteDTO save(ClienteDTO dto) {
        try {
            if (dto == null) {
                throw new ServiceException("El ClienteDTO no puede ser null");
            }
            Cliente entity = clienteMapper.toEntity(dto);
            if (entity == null) {
                throw new ServiceException("Error al convertir ClienteDTO a entidad: resultado null");
            }
            Cliente saved = clienteRepository.save(entity);
            return clienteMapper.toDTO(saved);
        } catch (Exception e) {
            throw new ServiceException("Error al guardar Cliente", e);
        }
    }

    public ClienteDTO findById(ClienteId id) {
        try {
            if (id == null) {
                throw new ServiceException("El ClienteId no puede ser null");
            }
            Optional<Cliente> entityOpt = clienteRepository.findById(id);
            return entityOpt.map(clienteMapper::toDTO)
                    .orElseThrow(() -> new ServiceException("Cliente no encontrado"));
        } catch (Exception e) {
            throw new ServiceException("Error al buscar Cliente", e);
        }
    }

    public List<ClienteDTO> findAll() {
        try {
            return clienteRepository.findAll().stream()
                    .map(clienteMapper::toDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new ServiceException("Error al listar Clientes", e);
        }
    }

    public ClienteDTO update(ClienteDTO dto) {
        try {
            if (dto == null) {
                throw new ServiceException("El ClienteDTO no puede ser null");
            }
            ClienteId id = new ClienteId(dto.getCodCia(), dto.getCodCliente());
            Cliente entity = clienteRepository.findById(id)
                    .orElseThrow(() -> new ServiceException("Cliente no encontrado para actualizar"));
            if (entity == null) {
                throw new ServiceException("La entidad Cliente obtenida es null");
            }
            clienteMapper.updateEntityFromDTO(dto, entity);
            Cliente updated = clienteRepository.save(entity);
            return clienteMapper.toDTO(updated);
        } catch (Exception e) {
            throw new ServiceException("Error al actualizar Cliente", e);
        }
    }

    public boolean delete(ClienteId id) {
        try {
            if (id == null) {
                throw new ServiceException("El ClienteId no puede ser null");
            }
            if (!clienteRepository.existsById(id)) {
                throw new ServiceException("Cliente no encontrado para eliminar");
            }
            clienteRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new ServiceException("Error al eliminar Cliente", e);
        }
    }
}
