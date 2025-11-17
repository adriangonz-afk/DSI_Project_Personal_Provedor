package com.system.service;

import com.system.dto.CompPagoempleadoDTO;
import com.system.entity.CompPagoempleado;
import com.system.entity.id.CompPagoempleadoId;
import com.system.mapper.CompPagoempleadoMapper;
import com.system.repository.CompPagoempleadoRepository;
import com.system.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CompPagoempleadoService {

    @Autowired
    private CompPagoempleadoRepository compPagoempleadoRepository;

    @Autowired
    private CompPagoempleadoMapper compPagoempleadoMapper;

    public CompPagoempleadoDTO save(CompPagoempleadoDTO dto) {
        try {
            if (dto == null) {
                throw new ServiceException("El DTO de CompPagoempleado no puede ser null");
            }
            CompPagoempleado entity = compPagoempleadoMapper.toEntity(dto);
            if (entity == null) {
                throw new ServiceException("Error al convertir DTO a entidad: resultado null");
            }
            CompPagoempleado saved = compPagoempleadoRepository.save(entity);
            return compPagoempleadoMapper.toDTO(saved);
        } catch (Exception e) {
            throw new ServiceException("Error al guardar CompPagoempleado", e);
        }
    }

    public CompPagoempleadoDTO findById(CompPagoempleadoId id) {
        try {
            if (id == null) {
                throw new ServiceException("El ID de CompPagoempleado no puede ser null");
            }
            Optional<CompPagoempleado> entityOpt = compPagoempleadoRepository.findById(id);
            if (entityOpt.isPresent()) {
                return compPagoempleadoMapper.toDTO(entityOpt.get());
            } else {
                throw new ServiceException("CompPagoempleado no encontrado con ID: " + id);
            }
        } catch (Exception e) {
            throw new ServiceException("Error al buscar CompPagoempleado por ID", e);
        }
    }

    public List<CompPagoempleadoDTO> findAll() {
        try {
            List<CompPagoempleado> entities = compPagoempleadoRepository.findAll();
            return entities.stream()
                    .map(compPagoempleadoMapper::toDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new ServiceException("Error al listar CompPagoempleado", e);
        }
    }

    public CompPagoempleadoDTO update(CompPagoempleadoDTO dto) {
        try {
            if (dto == null) {
                throw new ServiceException("El DTO de CompPagoempleado no puede ser null");
            }
            CompPagoempleadoId id = new CompPagoempleadoId(dto.getCodCia(), dto.getCodEmpleado(), dto.getNroCP());
            Optional<CompPagoempleado> entityOpt = compPagoempleadoRepository.findById(id);
            if (entityOpt.isPresent()) {
                CompPagoempleado entity = entityOpt.get();
                if (entity == null) {
                    throw new ServiceException("La entidad CompPagoempleado obtenida es null");
                }
                compPagoempleadoMapper.updateEntityFromDTO(dto, entity);
                CompPagoempleado updated = compPagoempleadoRepository.save(entity);
                return compPagoempleadoMapper.toDTO(updated);
            } else {
                throw new ServiceException("CompPagoempleado no encontrado para actualizar con ID: " + id);
            }
        } catch (Exception e) {
            throw new ServiceException("Error al actualizar CompPagoempleado", e);
        }
    }

    public boolean delete(CompPagoempleadoId id) {
        try {
            if (id == null) {
                throw new ServiceException("El ID de CompPagoempleado no puede ser null");
            }
            if (compPagoempleadoRepository.existsById(id)) {
                compPagoempleadoRepository.deleteById(id);
                return true;
            } else {
                throw new ServiceException("CompPagoempleado no encontrado para eliminar con ID: " + id);
            }
        } catch (Exception e) {
            throw new ServiceException("Error al eliminar CompPagoempleado", e);
        }
    }
}
