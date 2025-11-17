package com.system.service;

import com.system.dto.CargosAreasDTO;
import com.system.entity.CargosAreas;
import com.system.entity.id.CargosAreasId;
import com.system.mapper.CargosAreasMapper;
import com.system.repository.CargosAreasRepository;
import com.system.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CargosAreasService {

    @Autowired
    private CargosAreasRepository cargosAreasRepository;

    @Autowired
    private CargosAreasMapper cargosAreasMapper;

    public CargosAreasDTO save(CargosAreasDTO dto) {
        try {
            if (dto == null) {
                throw new ServiceException("El DTO de CargosAreas no puede ser null");
            }
            CargosAreas entity = cargosAreasMapper.toEntity(dto);
            if (entity == null) {
                throw new ServiceException("Error al convertir DTO a entidad: resultado null");
            }
            CargosAreas saved = cargosAreasRepository.save(entity);
            return cargosAreasMapper.toDTO(saved);
        } catch (Exception e) {
            throw new ServiceException("Error al guardar CargosAreas", e);
        }
    }

    public CargosAreasDTO findById(CargosAreasId id) {
        try {
            if (id == null) {
                throw new ServiceException("El ID de CargosAreas no puede ser null");
            }
            Optional<CargosAreas> entityOpt = cargosAreasRepository.findById(id);
            if (entityOpt.isPresent()) {
                return cargosAreasMapper.toDTO(entityOpt.get());
            } else {
                throw new ServiceException("CargosAreas no encontrado con ID: " + id);
            }
        } catch (Exception e) {
            throw new ServiceException("Error al buscar CargosAreas por ID", e);
        }
    }

    public List<CargosAreasDTO> findAll() {
        try {
            List<CargosAreas> entities = cargosAreasRepository.findAll();
            return entities.stream()
                    .map(cargosAreasMapper::toDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new ServiceException("Error al listar CargosAreas", e);
        }
    }

    public CargosAreasDTO update(CargosAreasDTO dto) {
        try {
            if (dto == null) {
                throw new ServiceException("El DTO de CargosAreas no puede ser null");
            }
            CargosAreasId id = new CargosAreasId(dto.getCodCargo(), dto.getCodArea(), dto.getCodCia());
            Optional<CargosAreas> entityOpt = cargosAreasRepository.findById(id);
            if (entityOpt.isPresent()) {
                CargosAreas entity = entityOpt.get();
                if (entity == null) {
                    throw new ServiceException("La entidad CargosAreas obtenida es null");
                }
                cargosAreasMapper.updateEntityFromDTO(dto, entity);
                CargosAreas updated = cargosAreasRepository.save(entity);
                return cargosAreasMapper.toDTO(updated);
            } else {
                throw new ServiceException("CargosAreas no encontrado para actualizar con ID: " + id);
            }
        } catch (Exception e) {
            throw new ServiceException("Error al actualizar CargosAreas", e);
        }
    }

    public boolean delete(CargosAreasId id) {
        try {
            if (id == null) {
                throw new ServiceException("El ID de CargosAreas no puede ser null");
            }
            if (cargosAreasRepository.existsById(id)) {
                cargosAreasRepository.deleteById(id);
                return true;
            } else {
                throw new ServiceException("CargosAreas no encontrado para eliminar con ID: " + id);
            }
        } catch (Exception e) {
            throw new ServiceException("Error al eliminar CargosAreas", e);
        }
    }
}
