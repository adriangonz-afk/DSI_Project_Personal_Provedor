package com.system.service;

import com.system.dto.UsuariosDTO;
import com.system.entity.Usuarios;
import com.system.entity.id.UsuariosId;
import com.system.mapper.UsuariosMapper;
import com.system.repository.UsuariosRepository;
import com.system.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UsuariosService {

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private UsuariosMapper usuariosMapper;

    public UsuariosDTO save(UsuariosDTO dto) {
        try {
            if (dto == null) {
                throw new ServiceException("El DTO de Usuarios no puede ser null");
            }
            Usuarios entity = usuariosMapper.toEntity(dto);
            if (entity == null) {
                throw new ServiceException("Error al convertir DTO a entidad: resultado null");
            }
            Usuarios saved = usuariosRepository.save(entity);
            return usuariosMapper.toDTO(saved);
        } catch (Exception e) {
            throw new ServiceException("Error al guardar Usuarios", e);
        }
    }

    public UsuariosDTO findById(UsuariosId id) {
        try {
            if (id == null) {
                throw new ServiceException("El ID de Usuarios no puede ser null");
            }
            Optional<Usuarios> optional = usuariosRepository.findById(id);
            if (optional.isPresent()) {
                return usuariosMapper.toDTO(optional.get());
            } else {
                throw new ServiceException("Usuarios no encontrado con id: " + id);
            }
        } catch (Exception e) {
            throw new ServiceException("Error al buscar Usuarios", e);
        }
    }

    public List<UsuariosDTO> findAll() {
        try {
            List<Usuarios> list = usuariosRepository.findAll();
            return list.stream()
                    .map(usuariosMapper::toDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new ServiceException("Error al listar Usuarios", e);
        }
    }

    public UsuariosDTO update(UsuariosDTO dto) {
        try {
            if (dto == null) {
                throw new ServiceException("El DTO de Usuarios no puede ser null");
            }
            UsuariosId id = new UsuariosId(dto.getCodCia(), dto.getCodUsuario());
            Optional<Usuarios> optional = usuariosRepository.findById(id);
            if (optional.isPresent()) {
                Usuarios entity = optional.get();
                if (entity == null) {
                    throw new ServiceException("La entidad Usuarios obtenida es null");
                }
                usuariosMapper.updateEntityFromDTO(dto, entity);
                Usuarios updated = usuariosRepository.save(entity);
                return usuariosMapper.toDTO(updated);
            } else {
                throw new ServiceException("No se puede actualizar. Usuarios no encontrado con id: " + id);
            }
        } catch (Exception e) {
            throw new ServiceException("Error al actualizar Usuarios", e);
        }
    }

    public boolean delete(UsuariosId id) {
        try {
            if (id == null) {
                throw new ServiceException("El ID de Usuarios no puede ser null");
            }
            if (usuariosRepository.existsById(id)) {
                usuariosRepository.deleteById(id);
                return true;
            } else {
                throw new ServiceException("No se puede eliminar. Usuarios no encontrado con id: " + id);
            }
        } catch (Exception e) {
            throw new ServiceException("Error al eliminar Usuarios", e);
        }
    }
}