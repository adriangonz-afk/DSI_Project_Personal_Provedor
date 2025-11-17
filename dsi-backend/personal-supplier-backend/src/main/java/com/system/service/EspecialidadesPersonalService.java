package com.system.service;

import com.system.dto.EspecialidadesPersonalDTO;
import com.system.entity.EspecialidadesPersonal;
import com.system.entity.id.EspecialidadesPersonalId;
import com.system.mapper.EspecialidadesPersonalMapper;
import com.system.repository.EspecialidadesPersonalRepository;
import com.system.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class EspecialidadesPersonalService {

    @Autowired
    private EspecialidadesPersonalRepository especialidadesPersonalRepository;

    @Autowired
    private EspecialidadesPersonalMapper especialidadesPersonalMapper;

    public EspecialidadesPersonalDTO save(EspecialidadesPersonalDTO dto) {
        try {
            if (dto == null) {
                throw new ServiceException("El DTO de EspecialidadesPersonal no puede ser null");
            }
            EspecialidadesPersonal entity = especialidadesPersonalMapper.toEntity(dto);
            if (entity == null) {
                throw new ServiceException("Error al convertir DTO a entidad: resultado null");
            }
            EspecialidadesPersonal saved = especialidadesPersonalRepository.save(entity);
            return especialidadesPersonalMapper.toDTO(saved);
        } catch (Exception e) {
            throw new ServiceException("Error al guardar EspecialidadesPersonal", e);
        }
    }

    public EspecialidadesPersonalDTO findById(EspecialidadesPersonalId id) {
        try {
            if (id == null) {
                throw new ServiceException("El ID de EspecialidadesPersonal no puede ser null");
            }
            Optional<EspecialidadesPersonal> entityOpt = especialidadesPersonalRepository.findById(id);
            return entityOpt.map(especialidadesPersonalMapper::toDTO)
                    .orElseThrow(() -> new ServiceException("EspecialidadesPersonal no encontrado"));
        } catch (Exception e) {
            throw new ServiceException("Error al buscar EspecialidadesPersonal", e);
        }
    }

    public List<EspecialidadesPersonalDTO> findAll() {
        try {
            List<EspecialidadesPersonal> entities = especialidadesPersonalRepository.findAll();
            return entities.stream()
                    .map(especialidadesPersonalMapper::toDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new ServiceException("Error al listar EspecialidadesPersonal", e);
        }
    }

    public EspecialidadesPersonalDTO update(EspecialidadesPersonalDTO dto) {
        try {
            if (dto == null) {
                throw new ServiceException("El DTO de EspecialidadesPersonal no puede ser null");
            }
            EspecialidadesPersonalId id = new EspecialidadesPersonalId(
                    dto.getCodCia(),
                    dto.getCodEspecialidad(),
                    dto.getCodEmpleado()
            );
            EspecialidadesPersonal entity = especialidadesPersonalRepository.findById(id)
                    .orElseThrow(() -> new ServiceException("EspecialidadesPersonal no encontrado para actualizar"));
            if (entity == null) {
                throw new ServiceException("La entidad EspecialidadesPersonal obtenida es null");
            }
            especialidadesPersonalMapper.updateEntityFromDTO(dto, entity);
            EspecialidadesPersonal updated = especialidadesPersonalRepository.save(entity);
            return especialidadesPersonalMapper.toDTO(updated);
        } catch (Exception e) {
            throw new ServiceException("Error al actualizar EspecialidadesPersonal", e);
        }
    }

    public boolean delete(EspecialidadesPersonalId id) {
        try {
            if (id == null) {
                throw new ServiceException("El ID de EspecialidadesPersonal no puede ser null");
            }
            if (!especialidadesPersonalRepository.existsById(id)) {
                throw new ServiceException("EspecialidadesPersonal no encontrado para eliminar");
            }
            especialidadesPersonalRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new ServiceException("Error al eliminar EspecialidadesPersonal", e);
        }
    }
}
