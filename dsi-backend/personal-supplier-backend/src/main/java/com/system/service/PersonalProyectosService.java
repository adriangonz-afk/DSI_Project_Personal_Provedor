package com.system.service;

import com.system.dto.PersonalProyectosDTO;
import com.system.entity.PersonalProyectos;
import com.system.entity.id.PersonalProyectosId;
import com.system.mapper.PersonalProyectosMapper;
import com.system.repository.PersonalProyectosRepository;
import com.system.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class PersonalProyectosService {

    @Autowired
    private PersonalProyectosRepository personalProyectosRepository;

    @Autowired
    private PersonalProyectosMapper personalProyectosMapper;

    public PersonalProyectosDTO save(PersonalProyectosDTO dto) {
        try {
            if (dto == null) {
                throw new ServiceException("El DTO de PersonalProyectos no puede ser null");
            }
            PersonalProyectos entity = personalProyectosMapper.toEntity(dto);
            if (entity == null) {
                throw new ServiceException("Error al convertir DTO a entidad: resultado null");
            }
            PersonalProyectos saved = personalProyectosRepository.save(entity);
            return personalProyectosMapper.toDTO(saved);
        } catch (Exception e) {
            throw new ServiceException("Error al guardar PersonalProyectos", e);
        }
    }

    public PersonalProyectosDTO findById(PersonalProyectosId id) {
        try {
            if (id == null) {
                throw new ServiceException("El ID de PersonalProyectos no puede ser null");
            }
            Optional<PersonalProyectos> entityOpt = personalProyectosRepository.findById(id);
            return entityOpt.map(personalProyectosMapper::toDTO)
                    .orElseThrow(() -> new ServiceException("PersonalProyectos no encontrado"));
        } catch (Exception e) {
            throw new ServiceException("Error al buscar PersonalProyectos", e);
        }
    }

    public List<PersonalProyectosDTO> findAll() {
        try {
            List<PersonalProyectos> entities = personalProyectosRepository.findAll();
            return entities.stream()
                    .map(personalProyectosMapper::toDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new ServiceException("Error al listar PersonalProyectos", e);
        }
    }

    public PersonalProyectosDTO update(PersonalProyectosDTO dto) {
        try {
            if (dto == null) {
                throw new ServiceException("El DTO de PersonalProyectos no puede ser null");
            }
            PersonalProyectosId id = new PersonalProyectosId(
                    dto.getCodCia(),
                    dto.getCodPersonalProyecto(),
                    dto.getCodEmpleado()
            );
            PersonalProyectos entity = personalProyectosRepository.findById(id)
                    .orElseThrow(() -> new ServiceException("PersonalProyectos no encontrado para actualizar"));
            if (entity == null) {
                throw new ServiceException("La entidad PersonalProyectos obtenida es null");
            }
            personalProyectosMapper.updateEntityFromDTO(dto, entity);
            PersonalProyectos updated = personalProyectosRepository.save(entity);
            return personalProyectosMapper.toDTO(updated);
        } catch (Exception e) {
            throw new ServiceException("Error al actualizar PersonalProyectos", e);
        }
    }

    public boolean delete(PersonalProyectosId id) {
        try {
            if (id == null) {
                throw new ServiceException("El ID de PersonalProyectos no puede ser null");
            }
            if (!personalProyectosRepository.existsById(id)) {
                throw new ServiceException("PersonalProyectos no encontrado para eliminar");
            }
            personalProyectosRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new ServiceException("Error al eliminar PersonalProyectos", e);
        }
    }
}
