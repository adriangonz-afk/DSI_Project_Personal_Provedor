package com.system.service;

import com.system.dto.ProveedorDTO;
import com.system.dto.ProveedorCompletoDTO;
import com.system.entity.Proveedor;
import com.system.entity.id.ProveedorId;
import com.system.mapper.ProveedorMapper;
import com.system.mapper.ContratosProveedorMapper;
import com.system.mapper.DocumentosProveedorMapper;
import com.system.mapper.ActividadesProveedorMapper;
import com.system.mapper.CompPagocabMapper;
import com.system.mapper.ServiciosProveedorMapper;
import com.system.repository.ProveedorRepository;
import com.system.repository.ContratosProveedorRepository;
import com.system.repository.DocumentosProveedorRepository;
import com.system.repository.ActividadesProveedorRepository;
import com.system.repository.CompPagocabRepository;
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
public class ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private ProveedorMapper proveedorMapper;
    
    @Autowired
    private ContratosProveedorRepository contratosProveedorRepository;
    
    @Autowired
    private ContratosProveedorMapper contratosProveedorMapper;
    
    @Autowired
    private DocumentosProveedorRepository documentosProveedorRepository;
    
    @Autowired
    private DocumentosProveedorMapper documentosProveedorMapper;
    
    @Autowired
    private ActividadesProveedorRepository actividadesProveedorRepository;
    
    @Autowired
    private ActividadesProveedorMapper actividadesProveedorMapper;
    
    @Autowired
    private CompPagocabRepository compPagocabRepository;
    
    @Autowired
    private CompPagocabMapper compPagocabMapper;
    
    @Autowired
    private ServiciosProveedorRepository serviciosProveedorRepository;
    
    @Autowired
    private ServiciosProveedorMapper serviciosProveedorMapper;

    public ProveedorDTO save(ProveedorDTO dto) {
        try {
            if (dto == null) {
                throw new ServiceException("El DTO de Proveedor no puede ser null");
            }
            Proveedor entity = proveedorMapper.toEntity(dto);
            if (entity == null) {
                throw new ServiceException("Error al convertir DTO a entidad: resultado null");
            }
            Proveedor saved = proveedorRepository.save(entity);
            return proveedorMapper.toDTO(saved);
        } catch (Exception e) {
            throw new ServiceException("Error al guardar Proveedor", e);
        }
    }

    public ProveedorDTO findById(ProveedorId id) {
        try {
            if (id == null) {
                throw new ServiceException("El ID de Proveedor no puede ser null");
            }
            Optional<Proveedor> opt = proveedorRepository.findById(id);
            if (opt.isPresent()) {
                return proveedorMapper.toDTO(opt.get());
            } else {
                throw new ServiceException("Proveedor no encontrado con id: " + id);
            }
        } catch (Exception e) {
            throw new ServiceException("Error al buscar Proveedor", e);
        }
    }

    public List<ProveedorDTO> findAll() {
        try {
            List<Proveedor> list = proveedorRepository.findAll();
            return list.stream().map(proveedorMapper::toDTO).collect(Collectors.toList());
        } catch (Exception e) {
            throw new ServiceException("Error al listar Proveedores", e);
        }
    }

    public ProveedorDTO update(ProveedorDTO dto) {
        try {
            if (dto == null) {
                throw new ServiceException("El DTO de Proveedor no puede ser null");
            }
            ProveedorId id = new ProveedorId(dto.getCodCia(), dto.getCodProveedor());
            Optional<Proveedor> opt = proveedorRepository.findById(id);
            if (opt.isPresent()) {
                Proveedor entity = opt.get();
                if (entity == null) {
                    throw new ServiceException("La entidad Proveedor obtenida es null");
                }
                proveedorMapper.updateEntityFromDTO(dto, entity);
                Proveedor updated = proveedorRepository.save(entity);
                return proveedorMapper.toDTO(updated);
            } else {
                throw new ServiceException("Proveedor no encontrado para actualizar con id: " + id);
            }
        } catch (Exception e) {
            throw new ServiceException("Error al actualizar Proveedor", e);
        }
    }

    public boolean delete(ProveedorId id) {
        try {
            if (id == null) {
                throw new ServiceException("El ID de Proveedor no puede ser null");
            }
            if (proveedorRepository.existsById(id)) {
                proveedorRepository.deleteById(id);
                return true;
            } else {
                throw new ServiceException("Proveedor no encontrado para eliminar con id: " + id);
            }
        } catch (Exception e) {
            throw new ServiceException("Error al eliminar Proveedor", e);
        }
    }

    public ProveedorCompletoDTO findByIdCompleto(ProveedorId id) {
        try {
            if (id == null) {
                throw new ServiceException("El ID de Proveedor no puede ser null");
            }
            
            // Buscar el proveedor principal
            Optional<Proveedor> opt = proveedorRepository.findById(id);
            if (!opt.isPresent()) {
                throw new ServiceException("Proveedor no encontrado con id: " + id);
            }
            
            Proveedor proveedor = opt.get();
            
            // Mapear el proveedor base
            ProveedorCompletoDTO proveedorCompleto = proveedorMapper.toCompletoDTO(proveedor);
            
            // Obtener y mapear todas las listas relacionadas
            proveedorCompleto.setContratos(
                contratosProveedorRepository.findByProveedorId(id.getCodCia(), id.getCodProveedor())
                    .stream()
                    .map(contratosProveedorMapper::toDTO)
                    .collect(Collectors.toList())
            );
            
            proveedorCompleto.setDocumentos(
                documentosProveedorRepository.findByProveedorId(id.getCodCia(), id.getCodProveedor())
                    .stream()
                    .map(documentosProveedorMapper::toDTO)
                    .collect(Collectors.toList())
            );
            
            proveedorCompleto.setActividades(
                actividadesProveedorRepository.findByProveedorId(id.getCodCia(), id.getCodProveedor())
                    .stream()
                    .map(actividadesProveedorMapper::toDTO)
                    .collect(Collectors.toList())
            );
            
            proveedorCompleto.setComprobantePagos(
                compPagocabRepository.findByProveedorId(id.getCodCia(), id.getCodProveedor())
                    .stream()
                    .map(compPagocabMapper::toDTO)
                    .collect(Collectors.toList())
            );
            
            proveedorCompleto.setServicios(
                serviciosProveedorRepository.findByProveedorId(id.getCodCia(), id.getCodProveedor())
                    .stream()
                    .map(serviciosProveedorMapper::toDTO)
                    .collect(Collectors.toList())
            );
            
            return proveedorCompleto;
            
        } catch (Exception e) {
            throw new ServiceException("Error al buscar información completa del Proveedor", e);
        }
    }

    public List<ProveedorCompletoDTO> findAllCompletos() {
        try {
            List<Proveedor> proveedores = proveedorRepository.findAll();
            
            return proveedores.stream().map(proveedor -> {
                try {
                    ProveedorId id = proveedor.getId();
                    
                    // Mapear el proveedor base
                    ProveedorCompletoDTO proveedorCompleto = proveedorMapper.toCompletoDTO(proveedor);
                    
                    // Obtener y mapear todas las listas relacionadas
                    proveedorCompleto.setContratos(
                        contratosProveedorRepository.findByProveedorId(id.getCodCia(), id.getCodProveedor())
                            .stream()
                            .map(contratosProveedorMapper::toDTO)
                            .collect(Collectors.toList())
                    );
                    
                    proveedorCompleto.setDocumentos(
                        documentosProveedorRepository.findByProveedorId(id.getCodCia(), id.getCodProveedor())
                            .stream()
                            .map(documentosProveedorMapper::toDTO)
                            .collect(Collectors.toList())
                    );
                    
                    proveedorCompleto.setActividades(
                        actividadesProveedorRepository.findByProveedorId(id.getCodCia(), id.getCodProveedor())
                            .stream()
                            .map(actividadesProveedorMapper::toDTO)
                            .collect(Collectors.toList())
                    );
                    
                    proveedorCompleto.setComprobantePagos(
                        compPagocabRepository.findByProveedorId(id.getCodCia(), id.getCodProveedor())
                            .stream()
                            .map(compPagocabMapper::toDTO)
                            .collect(Collectors.toList())
                    );
                    
                    proveedorCompleto.setServicios(
                        serviciosProveedorRepository.findByProveedorId(id.getCodCia(), id.getCodProveedor())
                            .stream()
                            .map(serviciosProveedorMapper::toDTO)
                            .collect(Collectors.toList())
                    );
                    
                    return proveedorCompleto;
                } catch (Exception e) {
                    throw new RuntimeException("Error procesando proveedor: " + proveedor.getId().getCodCia() + "/" + proveedor.getId().getCodProveedor(), e);
                }
            }).collect(Collectors.toList());
            
        } catch (Exception e) {
            throw new ServiceException("Error al listar información completa de Proveedores", e);
        }
    }
}
