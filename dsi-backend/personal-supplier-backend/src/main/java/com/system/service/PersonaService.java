package com.system.service;

import com.system.dto.PersonaDTO;
import com.system.entity.Persona;
import com.system.entity.id.PersonaId;
import com.system.mapper.PersonaMapper;
import com.system.repository.PersonaRepository;
import com.system.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private PersonaMapper personaMapper;

    public PersonaDTO save(PersonaDTO dto) {
        try {
            if (dto == null) {
                throw new ServiceException("El DTO de Persona no puede ser null");
            }
            Persona entity = personaMapper.toEntity(dto);
            if (entity == null) {
                throw new ServiceException("Error al convertir DTO a entidad: resultado null");
            }
            Persona saved = personaRepository.save(entity);
            return personaMapper.toDTO(saved);
        } catch (Exception e) {
            throw new ServiceException("Error al guardar Persona", e);
        }
    }

    public PersonaDTO findById(PersonaId id) {
        try {
            if (id == null) {
                throw new ServiceException("El ID de Persona no puede ser null");
            }
            Optional<Persona> opt = personaRepository.findById(id);
            if (opt.isPresent()) {
                return personaMapper.toDTO(opt.get());
            } else {
                throw new ServiceException("Persona no encontrada con id: " + id);
            }
        } catch (Exception e) {
            throw new ServiceException("Error al buscar Persona", e);
        }
    }

    public List<PersonaDTO> findAll() {
        try {
            List<Persona> list = personaRepository.findAll();
            return list.stream().map(personaMapper::toDTO).collect(Collectors.toList());
        } catch (Exception e) {
            throw new ServiceException("Error al listar Personas", e);
        }
    }

    public PersonaDTO update(PersonaDTO dto) {
        try {
            if (dto == null) {
                throw new ServiceException("El DTO de Persona no puede ser null");
            }
            PersonaId id = new PersonaId(dto.getCodCia(), dto.getCodPersona());
            Optional<Persona> opt = personaRepository.findById(id);
            if (opt.isPresent()) {
                Persona entity = opt.get();
                if (entity == null) {
                    throw new ServiceException("La entidad Persona obtenida es null");
                }
                personaMapper.updateEntityFromDTO(dto, entity);
                Persona updated = personaRepository.save(entity);
                return personaMapper.toDTO(updated);
            } else {
                throw new ServiceException("Persona no encontrada para actualizar con id: " + id);
            }
        } catch (Exception e) {
            throw new ServiceException("Error al actualizar Persona", e);
        }
    }

    public boolean delete(PersonaId id) {
        try {
            if (id == null) {
                throw new ServiceException("El ID de Persona no puede ser null");
            }
            if (personaRepository.existsById(id)) {
                personaRepository.deleteById(id);
                return true;
            } else {
                throw new ServiceException("Persona no encontrada para eliminar con id: " + id);
            }
        } catch (Exception e) {
            throw new ServiceException("Error al eliminar Persona", e);
        }
    }
}
