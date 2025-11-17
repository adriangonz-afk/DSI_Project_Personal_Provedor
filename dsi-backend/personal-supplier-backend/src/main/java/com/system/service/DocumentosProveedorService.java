package com.system.service;

import com.system.dto.DocumentosProveedorDTO;
import com.system.entity.DocumentosProveedor;
import com.system.entity.id.DocumentosProveedorId;
import com.system.mapper.DocumentosProveedorMapper;
import com.system.repository.DocumentosProveedorRepository;
import com.system.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class DocumentosProveedorService {

    @Autowired
    private DocumentosProveedorRepository documentosProveedorRepository;

    @Autowired
    private DocumentosProveedorMapper documentosProveedorMapper;

    public DocumentosProveedorDTO save(DocumentosProveedorDTO dto) {
        try {
            if (dto == null) {
                throw new ServiceException("El DTO de DocumentosProveedor no puede ser null");
            }
            DocumentosProveedor entity = documentosProveedorMapper.toEntity(dto);
            if (entity == null) {
                throw new ServiceException("Error al convertir DTO a entidad: resultado null");
            }
            DocumentosProveedor saved = documentosProveedorRepository.save(entity);
            return documentosProveedorMapper.toDTO(saved);
        } catch (Exception e) {
            throw new ServiceException("Error al guardar DocumentosProveedor", e);
        }
    }

    public DocumentosProveedorDTO findById(DocumentosProveedorId id) {
        try {
            if (id == null) {
                throw new ServiceException("El ID de DocumentosProveedor no puede ser null");
            }
            Optional<DocumentosProveedor> entityOpt = documentosProveedorRepository.findById(id);
            if (entityOpt.isPresent()) {
                return documentosProveedorMapper.toDTO(entityOpt.get());
            } else {
                throw new ServiceException("DocumentosProveedor no encontrado con ID: " + id);
            }
        } catch (Exception e) {
            throw new ServiceException("Error al buscar DocumentosProveedor por ID", e);
        }
    }

    public List<DocumentosProveedorDTO> findAll() {
        try {
            List<DocumentosProveedor> entities = documentosProveedorRepository.findAll();
            return entities.stream()
                    .map(documentosProveedorMapper::toDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new ServiceException("Error al listar DocumentosProveedor", e);
        }
    }

    public DocumentosProveedorDTO update(DocumentosProveedorDTO dto) {
        try {
            if (dto == null) {
                throw new ServiceException("El DTO de DocumentosProveedor no puede ser null");
            }
            DocumentosProveedorId id = new DocumentosProveedorId(dto.getCodCia(), dto.getCodDocumento(), dto.getCodProveedor());
            Optional<DocumentosProveedor> entityOpt = documentosProveedorRepository.findById(id);
            if (entityOpt.isPresent()) {
                DocumentosProveedor entity = entityOpt.get();
                if (entity == null) {
                    throw new ServiceException("La entidad DocumentosProveedor obtenida es null");
                }
                documentosProveedorMapper.updateEntityFromDTO(dto, entity);
                DocumentosProveedor updated = documentosProveedorRepository.save(entity);
                return documentosProveedorMapper.toDTO(updated);
            } else {
                throw new ServiceException("DocumentosProveedor no encontrado para actualizar con ID: " + id);
            }
        } catch (Exception e) {
            throw new ServiceException("Error al actualizar DocumentosProveedor", e);
        }
    }

    public boolean delete(DocumentosProveedorId id) {
        try {
            if (id == null) {
                throw new ServiceException("El ID de DocumentosProveedor no puede ser null");
            }
            if (documentosProveedorRepository.existsById(id)) {
                documentosProveedorRepository.deleteById(id);
                return true;
            } else {
                throw new ServiceException("DocumentosProveedor no encontrado para eliminar con ID: " + id);
            }
        } catch (Exception e) {
            throw new ServiceException("Error al eliminar DocumentosProveedor", e);
        }
    }
}
