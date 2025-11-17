package com.system.service;

import com.system.dto.AreasDTO;
import com.system.entity.Areas;
import com.system.entity.id.AreasId;
import com.system.mapper.AreasMapper;
import com.system.repository.AreasRepository;
import com.system.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class AreasService {

    @Autowired
    private AreasRepository areasRepository;

    @Autowired
    private AreasMapper areasMapper;

    public AreasDTO save(AreasDTO dto) {
        try {
            Areas entity = areasMapper.toEntity(dto);
            if (entity == null) {
                throw new ServiceException("El mapeo de DTO a entidad resultó en null");
            }
            Areas saved = areasRepository.save(entity);
            return areasMapper.toDTO(saved);
        } catch (Exception e) {
            throw new ServiceException("Error al guardar Areas", e);
        }
    }

    public AreasDTO findById(AreasId id) {
        try {
            if (id == null) {
                throw new ServiceException("El id proporcionado es null");
            }
            Optional<Areas> opt = areasRepository.findById(id);
            if (opt.isPresent()) {
                Areas entity = opt.get();
                if (entity == null) {
                    throw new ServiceException("La entidad encontrada es null");
                }
                return areasMapper.toDTO(entity);
            } else {
                throw new ServiceException("Areas no encontrada con id: " + id);
            }
        } catch (Exception e) {
            throw new ServiceException("Error al buscar Areas", e);
        }
    }

    public List<AreasDTO> findAll() {
        try {
            List<Areas> list = areasRepository.findAll();
            return list.stream().map(areasMapper::toDTO).collect(Collectors.toList());
        } catch (Exception e) {
            throw new ServiceException("Error al listar Areas", e);
        }
    }

    public AreasDTO update(AreasDTO dto) {
        try {
            AreasId id = new AreasId(dto.getCodCia(), dto.getCodArea());
            Optional<Areas> opt = areasRepository.findById(id);
            if (opt.isPresent()) {
                Areas entity = opt.get();
                if (entity == null) {
                    throw new ServiceException("La entidad encontrada es null");
                }
                areasMapper.updateEntityFromDTO(dto, entity);
                Areas updated = areasRepository.save(entity);
                return areasMapper.toDTO(updated);
            } else {
                throw new ServiceException("Areas no encontrada para actualizar con id: " + id);
            }
        } catch (Exception e) {
            throw new ServiceException("Error al actualizar Areas", e);
        }
    }

    public boolean delete(AreasId id) {
        try {
            if (id == null) {
                throw new ServiceException("El id proporcionado es null");
            }
            if (areasRepository.existsById(id)) {
                areasRepository.deleteById(id);
                return true;
            } else {
                throw new ServiceException("Areas no encontrada para eliminar con id: " + id);
            }
        } catch (Exception e) {
            throw new ServiceException("Error al eliminar Areas", e);
        }
    }
}
