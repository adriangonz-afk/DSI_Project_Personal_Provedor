package com.system.service;

import com.system.dto.ServiciosProveedorDTO;
import com.system.entity.ServiciosProveedor;
import com.system.entity.id.ServiciosProveedorId;
import com.system.mapper.ServiciosProveedorMapper;
import com.system.repository.ServiciosProveedorRepository;
import com.system.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ServiciosProveedorService {

    @Autowired
    private ServiciosProveedorRepository serviciosProveedorRepository;

    @Autowired
    private ServiciosProveedorMapper serviciosProveedorMapper;

    public ServiciosProveedorDTO save(ServiciosProveedorDTO dto) {
        try {
            if (dto == null) {
                throw new ServiceException("El DTO de ServiciosProveedor no puede ser null");
            }
            ServiciosProveedor entity = serviciosProveedorMapper.toEntity(dto);
            if (entity == null) {
                throw new ServiceException("Error al convertir DTO a entidad: resultado null");
            }
            ServiciosProveedor saved = serviciosProveedorRepository.save(entity);
            return serviciosProveedorMapper.toDTO(saved);
        } catch (Exception e) {
            throw new ServiceException("Error al guardar ServiciosProveedor", e);
        }
    }

    public ServiciosProveedorDTO findById(ServiciosProveedorId id) {
        try {
            if (id == null) {
                throw new ServiceException("El ID de ServiciosProveedor no puede ser null");
            }
            Optional<ServiciosProveedor> optional = serviciosProveedorRepository.findById(id);
            if (optional.isPresent()) {
                return serviciosProveedorMapper.toDTO(optional.get());
            } else {
                throw new ServiceException("ServiciosProveedor no encontrado con id: " + id);
            }
        } catch (Exception e) {
            throw new ServiceException("Error al buscar ServiciosProveedor", e);
        }
    }

    public List<ServiciosProveedorDTO> findAll() {
        try {
            List<ServiciosProveedor> list = serviciosProveedorRepository.findAll();
            return list.stream()
                    .map(serviciosProveedorMapper::toDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new ServiceException("Error al listar ServiciosProveedor", e);
        }
    }

    public ServiciosProveedorDTO update(ServiciosProveedorDTO dto) {
        try {
            if (dto == null) {
                throw new ServiceException("El DTO de ServiciosProveedor no puede ser null");
            }
            ServiciosProveedorId id = new ServiciosProveedorId(dto.getCodCia(), dto.getCodServicio(), dto.getCodProveedor());
            Optional<ServiciosProveedor> optional = serviciosProveedorRepository.findById(id);
            if (optional.isPresent()) {
                ServiciosProveedor entity = optional.get();
                if (entity == null) {
                    throw new ServiceException("La entidad ServiciosProveedor obtenida es null");
                }
                serviciosProveedorMapper.updateEntityFromDTO(dto, entity);
                ServiciosProveedor updated = serviciosProveedorRepository.save(entity);
                return serviciosProveedorMapper.toDTO(updated);
            } else {
                throw new ServiceException("No se puede actualizar. ServiciosProveedor no encontrado con id: " + id);
            }
        } catch (Exception e) {
            throw new ServiceException("Error al actualizar ServiciosProveedor", e);
        }
    }

    public boolean delete(ServiciosProveedorId id) {
        try {
            if (id == null) {
                throw new ServiceException("El ID de ServiciosProveedor no puede ser null");
            }
            if (serviciosProveedorRepository.existsById(id)) {
                serviciosProveedorRepository.deleteById(id);
                return true;
            } else {
                throw new ServiceException("No se puede eliminar. ServiciosProveedor no encontrado con id: " + id);
            }
        } catch (Exception e) {
            throw new ServiceException("Error al eliminar ServiciosProveedor", e);
        }
    }
}