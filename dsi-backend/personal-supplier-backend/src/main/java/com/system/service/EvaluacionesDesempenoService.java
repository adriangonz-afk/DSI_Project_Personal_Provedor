package com.system.service;

import com.system.dto.EvaluacionesDesempenoDTO;
import com.system.entity.EvaluacionesDesempeno;
import com.system.entity.id.EvaluacionesDesempenoId;
import com.system.mapper.EvaluacionesDesempenoMapper;
import com.system.repository.EvaluacionesDesempenoRepository;
import com.system.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class EvaluacionesDesempenoService {

    @Autowired
    private EvaluacionesDesempenoRepository evaluacionesDesempenoRepository;

    @Autowired
    private EvaluacionesDesempenoMapper evaluacionesDesempenoMapper;

    public EvaluacionesDesempenoDTO save(EvaluacionesDesempenoDTO dto) {
        try {
            if (dto == null) {
                throw new ServiceException("El DTO de EvaluacionesDesempeno no puede ser null");
            }
            EvaluacionesDesempeno entity = evaluacionesDesempenoMapper.toEntity(dto);
            if (entity == null) {
                throw new ServiceException("Error al convertir DTO a entidad: resultado null");
            }
            EvaluacionesDesempeno saved = evaluacionesDesempenoRepository.save(entity);
            return evaluacionesDesempenoMapper.toDTO(saved);
        } catch (Exception e) {
            throw new ServiceException("Error al guardar EvaluacionesDesempeno", e);
        }
    }

    public EvaluacionesDesempenoDTO findById(EvaluacionesDesempenoId id) {
        try {
            if (id == null) {
                throw new ServiceException("El ID de EvaluacionesDesempeno no puede ser null");
            }
            Optional<EvaluacionesDesempeno> entityOpt = evaluacionesDesempenoRepository.findById(id);
            return entityOpt.map(evaluacionesDesempenoMapper::toDTO)
                    .orElseThrow(() -> new ServiceException("EvaluacionesDesempeno no encontrado"));
        } catch (Exception e) {
            throw new ServiceException("Error al buscar EvaluacionesDesempeno", e);
        }
    }

    public List<EvaluacionesDesempenoDTO> findAll() {
        try {
            List<EvaluacionesDesempeno> entities = evaluacionesDesempenoRepository.findAll();
            return entities.stream()
                    .map(evaluacionesDesempenoMapper::toDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new ServiceException("Error al listar EvaluacionesDesempeno", e);
        }
    }

    public EvaluacionesDesempenoDTO update(EvaluacionesDesempenoDTO dto) {
        try {
            if (dto == null) {
                throw new ServiceException("El DTO de EvaluacionesDesempeno no puede ser null");
            }
            EvaluacionesDesempenoId id = new EvaluacionesDesempenoId(
                    dto.getCodCia(),
                    dto.getCodEvaluacion(),
                    dto.getCodEmpleado(),
                    dto.getCodPyto()
            );
            EvaluacionesDesempeno entity = evaluacionesDesempenoRepository.findById(id)
                    .orElseThrow(() -> new ServiceException("EvaluacionesDesempeno no encontrado para actualizar"));
            if (entity == null) {
                throw new ServiceException("La entidad EvaluacionesDesempeno obtenida es null");
            }
            evaluacionesDesempenoMapper.updateEntityFromDTO(dto, entity);
            EvaluacionesDesempeno updated = evaluacionesDesempenoRepository.save(entity);
            return evaluacionesDesempenoMapper.toDTO(updated);
        } catch (Exception e) {
            throw new ServiceException("Error al actualizar EvaluacionesDesempeno", e);
        }
    }

    public boolean delete(EvaluacionesDesempenoId id) {
        try {
            if (id == null) {
                throw new ServiceException("El ID de EvaluacionesDesempeno no puede ser null");
            }
            if (!evaluacionesDesempenoRepository.existsById(id)) {
                throw new ServiceException("EvaluacionesDesempeno no encontrado para eliminar");
            }
            evaluacionesDesempenoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new ServiceException("Error al eliminar EvaluacionesDesempeno", e);
        }
    }
}
