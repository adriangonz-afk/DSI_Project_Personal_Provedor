package com.system.service;

import com.system.dto.GradosAcademicosDTO;
import com.system.entity.GradosAcademicos;
import com.system.entity.id.GradosAcademicosId;
import com.system.mapper.GradosAcademicosMapper;
import com.system.repository.GradosAcademicosRepository;
import com.system.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class GradosAcademicosService {

    @Autowired
    private GradosAcademicosRepository gradosAcademicosRepository;

    @Autowired
    private GradosAcademicosMapper gradosAcademicosMapper;

    public GradosAcademicosDTO save(GradosAcademicosDTO dto) {
        try {
            if (dto == null) {
                throw new ServiceException("El DTO de GradosAcademicos no puede ser null");
            }
            GradosAcademicos entity = gradosAcademicosMapper.toEntity(dto);
            if (entity == null) {
                throw new ServiceException("Error al convertir DTO a entidad: resultado null");
            }
            GradosAcademicos saved = gradosAcademicosRepository.save(entity);
            return gradosAcademicosMapper.toDTO(saved);
        } catch (Exception e) {
            throw new ServiceException("Error al guardar GradosAcademicos", e);
        }
    }

    public GradosAcademicosDTO findById(GradosAcademicosId id) {
        try {
            if (id == null) {
                throw new ServiceException("El ID de GradosAcademicos no puede ser null");
            }
            Optional<GradosAcademicos> entityOpt = gradosAcademicosRepository.findById(id);
            return entityOpt.map(gradosAcademicosMapper::toDTO)
                    .orElseThrow(() -> new ServiceException("GradosAcademicos no encontrado"));
        } catch (Exception e) {
            throw new ServiceException("Error al buscar GradosAcademicos", e);
        }
    }

    public List<GradosAcademicosDTO> findAll() {
        try {
            List<GradosAcademicos> entities = gradosAcademicosRepository.findAll();
            return entities.stream()
                    .map(gradosAcademicosMapper::toDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new ServiceException("Error al listar GradosAcademicos", e);
        }
    }

    public GradosAcademicosDTO update(GradosAcademicosDTO dto) {
        try {
            if (dto == null) {
                throw new ServiceException("El DTO de GradosAcademicos no puede ser null");
            }
            GradosAcademicosId id = new GradosAcademicosId(
                    dto.getCodCia(),
                    dto.getCodGrado(),
                    dto.getCodEmpleado()
            );
            GradosAcademicos entity = gradosAcademicosRepository.findById(id)
                    .orElseThrow(() -> new ServiceException("GradosAcademicos no encontrado para actualizar"));
            if (entity == null) {
                throw new ServiceException("La entidad GradosAcademicos obtenida es null");
            }
            gradosAcademicosMapper.updateEntityFromDTO(dto, entity);
            GradosAcademicos updated = gradosAcademicosRepository.save(entity);
            return gradosAcademicosMapper.toDTO(updated);
        } catch (Exception e) {
            throw new ServiceException("Error al actualizar GradosAcademicos", e);
        }
    }

    public boolean delete(GradosAcademicosId id) {
        try {
            if (id == null) {
                throw new ServiceException("El ID de GradosAcademicos no puede ser null");
            }
            if (!gradosAcademicosRepository.existsById(id)) {
                throw new ServiceException("GradosAcademicos no encontrado para eliminar");
            }
            gradosAcademicosRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new ServiceException("Error al eliminar GradosAcademicos", e);
        }
    }
}
