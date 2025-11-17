package com.system.service;

import com.system.dto.ExperienciaLaboralDTO;
import com.system.entity.ExperienciaLaboral;
import com.system.entity.id.ExperienciaLaboralId;
import com.system.mapper.ExperienciaLaboralMapper;
import com.system.repository.ExperienciaLaboralRepository;
import com.system.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ExperienciaLaboralService {

    @Autowired
    private ExperienciaLaboralRepository experienciaLaboralRepository;

    @Autowired
    private ExperienciaLaboralMapper experienciaLaboralMapper;

    public ExperienciaLaboralDTO save(ExperienciaLaboralDTO dto) {
        try {
            if (dto == null) {
                throw new ServiceException("El DTO de ExperienciaLaboral no puede ser null");
            }
            ExperienciaLaboral entity = experienciaLaboralMapper.toEntity(dto);
            if (entity == null) {
                throw new ServiceException("Error al convertir DTO a entidad: resultado null");
            }
            ExperienciaLaboral saved = experienciaLaboralRepository.save(entity);
            return experienciaLaboralMapper.toDTO(saved);
        } catch (Exception e) {
            throw new ServiceException("Error al guardar ExperienciaLaboral", e);
        }
    }

    public ExperienciaLaboralDTO findById(ExperienciaLaboralId id) {
        try {
            if (id == null) {
                throw new ServiceException("El ID de ExperienciaLaboral no puede ser null");
            }
            Optional<ExperienciaLaboral> entityOpt = experienciaLaboralRepository.findById(id);
            return entityOpt.map(experienciaLaboralMapper::toDTO)
                    .orElseThrow(() -> new ServiceException("ExperienciaLaboral no encontrada"));
        } catch (Exception e) {
            throw new ServiceException("Error al buscar ExperienciaLaboral", e);
        }
    }

    public List<ExperienciaLaboralDTO> findAll() {
        try {
            List<ExperienciaLaboral> entities = experienciaLaboralRepository.findAll();
            return entities.stream()
                    .map(experienciaLaboralMapper::toDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new ServiceException("Error al listar ExperienciaLaboral", e);
        }
    }

    public ExperienciaLaboralDTO update(ExperienciaLaboralDTO dto) {
        try {
            if (dto == null) {
                throw new ServiceException("El DTO de ExperienciaLaboral no puede ser null");
            }
            ExperienciaLaboralId id = new ExperienciaLaboralId(
                    dto.getCodCia(),
                    dto.getCodExperiencia(),
                    dto.getCodEmpleado()
            );
            ExperienciaLaboral entity = experienciaLaboralRepository.findById(id)
                    .orElseThrow(() -> new ServiceException("ExperienciaLaboral no encontrada para actualizar"));
            if (entity == null) {
                throw new ServiceException("La entidad ExperienciaLaboral obtenida es null");
            }
            experienciaLaboralMapper.updateEntityFromDTO(dto, entity);
            ExperienciaLaboral updated = experienciaLaboralRepository.save(entity);
            return experienciaLaboralMapper.toDTO(updated);
        } catch (Exception e) {
            throw new ServiceException("Error al actualizar ExperienciaLaboral", e);
        }
    }

    public boolean delete(ExperienciaLaboralId id) {
        try {
            if (id == null) {
                throw new ServiceException("El ID de ExperienciaLaboral no puede ser null");
            }
            if (!experienciaLaboralRepository.existsById(id)) {
                throw new ServiceException("ExperienciaLaboral no encontrada para eliminar");
            }
            experienciaLaboralRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new ServiceException("Error al eliminar ExperienciaLaboral", e);
        }
    }
}
