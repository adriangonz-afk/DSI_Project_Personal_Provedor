package com.system.service;

import com.system.dto.ActividadesProveedorDTO;
import com.system.entity.ActividadesProveedor;
import com.system.entity.id.ActividadesProveedorId;
import com.system.mapper.ActividadesProveedorMapper;
import com.system.repository.ActividadesProveedorRepository;
import com.system.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ActividadesProveedorService {

    @Autowired
    private ActividadesProveedorRepository actividadesProveedorRepository;

    @Autowired
    private ActividadesProveedorMapper actividadesProveedorMapper;

    public ActividadesProveedorDTO save(ActividadesProveedorDTO dto) {
        try {
            ActividadesProveedor entity = actividadesProveedorMapper.toEntity(dto);
            if (entity == null) {
                throw new ServiceException("El mapeo de DTO a entidad resultó en null");
            }
            ActividadesProveedor saved = actividadesProveedorRepository.save(entity);
            return actividadesProveedorMapper.toDTO(saved);
        } catch (Exception e) {
            throw new ServiceException("Error al guardar ActividadesProveedor", e);
        }
    }

    public ActividadesProveedorDTO findById(ActividadesProveedorId id) {
        try {
            if (id == null) {
                throw new ServiceException("El id proporcionado es null");
            }
            Optional<ActividadesProveedor> opt = actividadesProveedorRepository.findById(id);
            if (opt.isPresent()) {
                ActividadesProveedor entity = opt.get();
                if (entity == null) {
                    throw new ServiceException("La entidad encontrada es null");
                }
                return actividadesProveedorMapper.toDTO(entity);
            } else {
                throw new ServiceException("ActividadesProveedor no encontrado con id: " + id);
            }
        } catch (Exception e) {
            throw new ServiceException("Error al buscar ActividadesProveedor", e);
        }
    }

    public List<ActividadesProveedorDTO> findAll() {
        try {
            List<ActividadesProveedor> list = actividadesProveedorRepository.findAll();
            return list.stream().map(actividadesProveedorMapper::toDTO).collect(Collectors.toList());
        } catch (Exception e) {
            throw new ServiceException("Error al listar ActividadesProveedor", e);
        }
    }

    public ActividadesProveedorDTO update(ActividadesProveedorDTO dto) {
        try {
            ActividadesProveedorId id = new ActividadesProveedorId(
                dto.getCodCIA(),
                dto.getCodActividad(),
                dto.getCodProveedor(),
                dto.getCodPyto(),
                dto.getCodContrato()
            );
            Optional<ActividadesProveedor> opt = actividadesProveedorRepository.findById(id);
            if (opt.isPresent()) {
                ActividadesProveedor entity = opt.get();
                if (entity == null) {
                    throw new ServiceException("La entidad encontrada es null");
                }
                actividadesProveedorMapper.updateEntityFromDTO(dto, entity);
                ActividadesProveedor updated = actividadesProveedorRepository.save(entity);
                return actividadesProveedorMapper.toDTO(updated);
            } else {
                throw new ServiceException("ActividadesProveedor no encontrado para actualizar con id: " + id);
            }
        } catch (Exception e) {
            throw new ServiceException("Error al actualizar ActividadesProveedor", e);
        }
    }

    public boolean delete(ActividadesProveedorId id) {
        try {
            if (id == null) {
                throw new ServiceException("El id proporcionado es null");
            }
            if (actividadesProveedorRepository.existsById(id)) {
                actividadesProveedorRepository.deleteById(id);
                return true;
            } else {
                throw new ServiceException("ActividadesProveedor no encontrado para eliminar con id: " + id);
            }
        } catch (Exception e) {
            throw new ServiceException("Error al eliminar ActividadesProveedor", e);
        }
    }
}
