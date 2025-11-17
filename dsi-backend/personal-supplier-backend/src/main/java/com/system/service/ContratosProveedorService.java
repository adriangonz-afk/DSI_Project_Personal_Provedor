package com.system.service;

import com.system.dto.ContratosProveedorDTO;
import com.system.entity.ContratosProveedor;
import com.system.entity.id.ContratosProveedorId;
import com.system.mapper.ContratosProveedorMapper;
import com.system.repository.ContratosProveedorRepository;
import com.system.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ContratosProveedorService {

    @Autowired
    private ContratosProveedorRepository contratosProveedorRepository;

    @Autowired
    private ContratosProveedorMapper contratosProveedorMapper;

    public ContratosProveedorDTO save(ContratosProveedorDTO dto) {
        try {
            if (dto == null) {
                throw new ServiceException("El DTO de ContratosProveedor no puede ser null");
            }
            ContratosProveedor entity = contratosProveedorMapper.toEntity(dto);
            if (entity == null) {
                throw new ServiceException("Error al convertir DTO a entidad: resultado null");
            }
            ContratosProveedor saved = contratosProveedorRepository.save(entity);
            return contratosProveedorMapper.toDTO(saved);
        } catch (Exception e) {
            throw new ServiceException("Error al guardar ContratosProveedor", e);
        }
    }

    public ContratosProveedorDTO findById(ContratosProveedorId id) {
        try {
            if (id == null) {
                throw new ServiceException("El ID de ContratosProveedor no puede ser null");
            }
            Optional<ContratosProveedor> entityOpt = contratosProveedorRepository.findById(id);
            if (entityOpt.isPresent()) {
                return contratosProveedorMapper.toDTO(entityOpt.get());
            } else {
                throw new ServiceException("ContratosProveedor no encontrado con ID: " + id);
            }
        } catch (Exception e) {
            throw new ServiceException("Error al buscar ContratosProveedor por ID", e);
        }
    }

    public List<ContratosProveedorDTO> findAll() {
        try {
            List<ContratosProveedor> entities = contratosProveedorRepository.findAll();
            return entities.stream()
                    .map(contratosProveedorMapper::toDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new ServiceException("Error al listar ContratosProveedor", e);
        }
    }

    public ContratosProveedorDTO update(ContratosProveedorDTO dto) {
        try {
            if (dto == null) {
                throw new ServiceException("El DTO de ContratosProveedor no puede ser null");
            }
            ContratosProveedorId id = new ContratosProveedorId(dto.getCodCia(), dto.getCodContrato(), dto.getCodProveedor(), dto.getCodPyto());
            Optional<ContratosProveedor> entityOpt = contratosProveedorRepository.findById(id);
            if (entityOpt.isPresent()) {
                ContratosProveedor entity = entityOpt.get();
                if (entity == null) {
                    throw new ServiceException("La entidad ContratosProveedor obtenida es null");
                }
                contratosProveedorMapper.updateEntityFromDTO(dto, entity);
                ContratosProveedor updated = contratosProveedorRepository.save(entity);
                return contratosProveedorMapper.toDTO(updated);
            } else {
                throw new ServiceException("ContratosProveedor no encontrado para actualizar con ID: " + id);
            }
        } catch (Exception e) {
            throw new ServiceException("Error al actualizar ContratosProveedor", e);
        }
    }

    public boolean delete(ContratosProveedorId id) {
        try {
            if (id == null) {
                throw new ServiceException("El ID de ContratosProveedor no puede ser null");
            }
            if (contratosProveedorRepository.existsById(id)) {
                contratosProveedorRepository.deleteById(id);
                return true;
            } else {
                throw new ServiceException("ContratosProveedor no encontrado para eliminar con ID: " + id);
            }
        } catch (Exception e) {
            throw new ServiceException("Error al eliminar ContratosProveedor", e);
        }
    }
}
