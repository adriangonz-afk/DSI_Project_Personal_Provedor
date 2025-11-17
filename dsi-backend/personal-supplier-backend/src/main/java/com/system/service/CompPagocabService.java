package com.system.service;

import com.system.dto.CompPagocabDTO;
import com.system.entity.CompPagocab;
import com.system.entity.id.CompPagocabId;
import com.system.mapper.CompPagocabMapper;
import com.system.repository.CompPagocabRepository;
import com.system.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CompPagocabService {

    @Autowired
    private CompPagocabRepository compPagocabRepository;

    @Autowired
    private CompPagocabMapper compPagocabMapper;

    public CompPagocabDTO save(CompPagocabDTO dto) {
        try {
            if (dto == null) {
                throw new ServiceException("El DTO de CompPagocab no puede ser null");
            }
            CompPagocab entity = compPagocabMapper.toEntity(dto);
            if (entity == null) {
                throw new ServiceException("Error al convertir DTO a entidad: resultado null");
            }
            CompPagocab saved = compPagocabRepository.save(entity);
            return compPagocabMapper.toDTO(saved);
        } catch (Exception e) {
            throw new ServiceException("Error al guardar CompPagocab", e);
        }
    }

    public CompPagocabDTO findById(CompPagocabId id) {
        try {
            if (id == null) {
                throw new ServiceException("El ID de CompPagocab no puede ser null");
            }
            Optional<CompPagocab> entityOpt = compPagocabRepository.findById(id);
            if (entityOpt.isPresent()) {
                return compPagocabMapper.toDTO(entityOpt.get());
            } else {
                throw new ServiceException("CompPagocab no encontrado con ID: " + id);
            }
        } catch (Exception e) {
            throw new ServiceException("Error al buscar CompPagocab por ID", e);
        }
    }

    public List<CompPagocabDTO> findAll() {
        try {
            List<CompPagocab> entities = compPagocabRepository.findAll();
            return entities.stream()
                    .map(compPagocabMapper::toDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new ServiceException("Error al listar CompPagocab", e);
        }
    }

    public CompPagocabDTO update(CompPagocabDTO dto) {
        try {
            if (dto == null) {
                throw new ServiceException("El DTO de CompPagocab no puede ser null");
            }
            CompPagocabId id = new CompPagocabId(dto.getCodCia(), dto.getCodProveedor(), dto.getNroCP());
            Optional<CompPagocab> entityOpt = compPagocabRepository.findById(id);
            if (entityOpt.isPresent()) {
                CompPagocab entity = entityOpt.get();
                if (entity == null) {
                    throw new ServiceException("La entidad CompPagocab obtenida es null");
                }
                compPagocabMapper.updateEntityFromDTO(dto, entity);
                CompPagocab updated = compPagocabRepository.save(entity);
                return compPagocabMapper.toDTO(updated);
            } else {
                throw new ServiceException("CompPagocab no encontrado para actualizar con ID: " + id);
            }
        } catch (Exception e) {
            throw new ServiceException("Error al actualizar CompPagocab", e);
        }
    }

    public boolean delete(CompPagocabId id) {
        try {
            if (id == null) {
                throw new ServiceException("El ID de CompPagocab no puede ser null");
            }
            if (compPagocabRepository.existsById(id)) {
                compPagocabRepository.deleteById(id);
                return true;
            } else {
                throw new ServiceException("CompPagocab no encontrado para eliminar con ID: " + id);
            }
        } catch (Exception e) {
            throw new ServiceException("Error al eliminar CompPagocab", e);
        }
    }
}
