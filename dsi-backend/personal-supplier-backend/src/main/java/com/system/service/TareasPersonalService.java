package com.system.service;

import com.system.dto.TareasPersonalDTO;
import com.system.entity.TareasPersonal;
import com.system.entity.id.TareasPersonalId;
import com.system.mapper.TareasPersonalMapper;
import com.system.repository.TareasPersonalRepository;
import com.system.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class TareasPersonalService {

    @Autowired
    private TareasPersonalRepository tareasPersonalRepository;

    @Autowired
    private TareasPersonalMapper tareasPersonalMapper;

    public TareasPersonalDTO save(TareasPersonalDTO dto) {
        try {
            if (dto == null) {
                throw new ServiceException("El DTO de TareasPersonal no puede ser null");
            }
            TareasPersonal entity = tareasPersonalMapper.toEntity(dto);
            if (entity == null) {
                throw new ServiceException("Error al convertir DTO a entidad: resultado null");
            }
            TareasPersonal saved = tareasPersonalRepository.save(entity);
            return tareasPersonalMapper.toDTO(saved);
        } catch (Exception e) {
            throw new ServiceException("Error al guardar TareasPersonal", e);
        }
    }

    public TareasPersonalDTO findById(TareasPersonalId id) {
        try {
            if (id == null) {
                throw new ServiceException("El ID de TareasPersonal no puede ser null");
            }
            Optional<TareasPersonal> optional = tareasPersonalRepository.findById(id);
            if (optional.isPresent()) {
                return tareasPersonalMapper.toDTO(optional.get());
            } else {
                throw new ServiceException("TareasPersonal no encontrado con id: " + id);
            }
        } catch (Exception e) {
            throw new ServiceException("Error al buscar TareasPersonal", e);
        }
    }

    public List<TareasPersonalDTO> findAll() {
        try {
            List<TareasPersonal> list = tareasPersonalRepository.findAll();
            return list.stream()
                    .map(tareasPersonalMapper::toDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new ServiceException("Error al listar TareasPersonal", e);
        }
    }

    public TareasPersonalDTO update(TareasPersonalDTO dto) {
        try {
            if (dto == null) {
                throw new ServiceException("El DTO de TareasPersonal no puede ser null");
            }
            TareasPersonalId id = new TareasPersonalId(dto.getCodCia(), dto.getCodTarea(), dto.getCodPyto(), dto.getCodEmpleado());
            Optional<TareasPersonal> optional = tareasPersonalRepository.findById(id);
            if (optional.isPresent()) {
                TareasPersonal entity = optional.get();
                if (entity == null) {
                    throw new ServiceException("La entidad TareasPersonal obtenida es null");
                }
                tareasPersonalMapper.updateEntityFromDTO(dto, entity);
                TareasPersonal updated = tareasPersonalRepository.save(entity);
                return tareasPersonalMapper.toDTO(updated);
            } else {
                throw new ServiceException("No se puede actualizar. TareasPersonal no encontrado con id: " + id);
            }
        } catch (Exception e) {
            throw new ServiceException("Error al actualizar TareasPersonal", e);
        }
    }

    public boolean delete(TareasPersonalId id) {
        try {
            if (id == null) {
                throw new ServiceException("El ID de TareasPersonal no puede ser null");
            }
            if (tareasPersonalRepository.existsById(id)) {
                tareasPersonalRepository.deleteById(id);
                return true;
            } else {
                throw new ServiceException("No se puede eliminar. TareasPersonal no encontrado con id: " + id);
            }
        } catch (Exception e) {
            throw new ServiceException("Error al eliminar TareasPersonal", e);
        }
    }
}