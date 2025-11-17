
package com.system.service;

import com.system.dto.ProyectoDTO;
import com.system.dto.ProyectoCompletoDTO;
import com.system.entity.Proyecto;
import com.system.entity.id.ProyectoId;
import com.system.mapper.ProyectoMapper;
import com.system.mapper.PersonaMapper;
import com.system.repository.ProyectoRepository;
import com.system.repository.PersonaRepository;
import com.system.repository.PersonalProyectosRepository;
import com.system.repository.ContratosProveedorRepository;
import com.system.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class ProyectoService {

    @Autowired
    private ProyectoRepository proyectoRepository;

    @Autowired
    private ProyectoMapper proyectoMapper;
    
    // Repositorios para las entidades relacionadas

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private PersonalProyectosRepository personalProyectosRepository;

    @Autowired
    private ContratosProveedorRepository contratosProveedorRepository;

    // Servicios para obtener datos completos
    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private ProveedorService proveedorService;

    // Mappers para las entidades relacionadas

    @Autowired
    private PersonaMapper personaMapper;


    /**
     * Devuelve la lista de todos los proyectos con sus relaciones principales (áreas, cargos, cliente)
     */
    /**
     * Devuelve un ProyectoCompletoDTO con todas las relaciones principales (áreas, cargos, cliente)
     */
    public ProyectoCompletoDTO findByIdCompleto(Long codCia, Long codPyto) {
        try {
            ProyectoId id = new ProyectoId(codCia, codPyto);
            Optional<Proyecto> opt = proyectoRepository.findById(id);
            if (opt.isEmpty()) {
                throw new ServiceException("Proyecto no encontrado para id: " + codCia + "/" + codPyto);
            }
            Proyecto proyecto = opt.get();
            ProyectoCompletoDTO dto = proyectoMapper.toCompletoDTO(proyecto);

            // Removed setting areas and cargosAreas as per new requirements

            // Cliente (persona)
            // Si el proyecto tiene codCliente, buscar la persona y mapear a DTO
            if (proyecto.getCodCliente() != null) {
                com.system.entity.id.PersonaId personaId = new com.system.entity.id.PersonaId(codCia, proyecto.getCodCliente());
                personaRepository.findById(personaId).ifPresent(persona -> dto.setCliente(personaMapper.toDTO(persona)));
            }

            // Empleados asignados al proyecto con información completa
            java.util.List<com.system.dto.EmpleadoCompletoDTO> empleadosCompletos = new java.util.ArrayList<>();
            personalProyectosRepository.findByIdCodCiaAndCodPyto(codCia, codPyto).forEach(personalProyecto -> {
                try {
                    com.system.entity.id.EmpleadoId empleadoId = new com.system.entity.id.EmpleadoId(codCia, personalProyecto.getId().getCodEmpleado());
                    com.system.dto.EmpleadoCompletoDTO empleadoCompleto = empleadoService.findByIdCompleto(empleadoId);
                    if (empleadoCompleto != null) {
                        empleadosCompletos.add(empleadoCompleto);
                    }
                } catch (Exception e) {
                    // Log error pero continúa con otros empleados
                    log.warn("Error al obtener empleado completo {}/{}: {}", codCia, personalProyecto.getId().getCodEmpleado(), e.getMessage());
                }
            });
            dto.setEmpleados(empleadosCompletos);

            // Proveedores contratados para el proyecto con información completa
            java.util.List<com.system.dto.ProveedorCompletoDTO> proveedoresCompletos = new java.util.ArrayList<>();
            contratosProveedorRepository.findByIdCodCiaAndIdCodPyto(codCia, codPyto).forEach(contrato -> {
                try {
                    com.system.entity.id.ProveedorId proveedorId = new com.system.entity.id.ProveedorId(codCia, contrato.getId().getCodProveedor());
                    com.system.dto.ProveedorCompletoDTO proveedorCompleto = proveedorService.findByIdCompleto(proveedorId);
                    if (proveedorCompleto != null) {
                        // Evitar duplicados en caso de múltiples contratos del mismo proveedor
                        boolean exists = proveedoresCompletos.stream()
                            .anyMatch(p -> p.getCodCia().equals(codCia) && p.getCodProveedor().equals(contrato.getId().getCodProveedor()));
                        if (!exists) {
                            proveedoresCompletos.add(proveedorCompleto);
                        }
                    }
                } catch (Exception e) {
                    // Log error pero continúa con otros proveedores
                    log.warn("Error al obtener proveedor completo {}/{}: {}", codCia, contrato.getId().getCodProveedor(), e.getMessage());
                }
            });
            dto.setProveedores(proveedoresCompletos);

            return dto;
        } catch (Exception e) {
            throw new ServiceException("Error al obtener información completa del proyecto", e);
        }
    }

    /**
     * Devuelve la lista de todos los proyectos con sus relaciones principales (áreas, cargos, cliente)
     */
    public java.util.List<ProyectoCompletoDTO> findAllCompletos() {
        try {
            java.util.List<Proyecto> proyectos = proyectoRepository.findAll();
            return proyectos.stream()
                .map(p -> findByIdCompleto(p.getId().getCodCia(), p.getId().getCodPyto()))
                .collect(java.util.stream.Collectors.toList());
        } catch (Exception e) {
            throw new ServiceException("Error al listar información completa de Proyectos", e);
        }
    }

    public ProyectoDTO save(ProyectoDTO dto) {
        try {
            if (dto == null) {
                throw new ServiceException("El DTO de Proyecto no puede ser null");
            }
            Proyecto entity = proyectoMapper.toEntity(dto);
            if (entity == null) {
                throw new ServiceException("Error al convertir DTO a entidad: resultado null");
            }
            Proyecto saved = proyectoRepository.save(entity);
            return proyectoMapper.toDTO(saved);
        } catch (Exception e) {
            throw new ServiceException("Error al guardar el proyecto", e);
        }
    }

    public ProyectoDTO findById(ProyectoId id) {
        try {
            if (id == null) {
                throw new ServiceException("El ID de Proyecto no puede ser null");
            }
            Optional<Proyecto> opt = proyectoRepository.findById(id);
            return opt.map(proyectoMapper::toDTO).orElse(null);
        } catch (Exception e) {
            throw new ServiceException("Error al buscar el proyecto", e);
        }
    }

    public List<ProyectoDTO> findAll() {
        try {
            List<Proyecto> list = proyectoRepository.findAll();
            return list.stream().map(proyectoMapper::toDTO).collect(Collectors.toList());
        } catch (Exception e) {
            throw new ServiceException("Error al listar los proyectos", e);
        }
    }

    public ProyectoDTO update(ProyectoDTO dto) {
        try {
            if (dto == null) {
                throw new ServiceException("El DTO de Proyecto no puede ser null");
            }
            ProyectoId id = new ProyectoId(dto.getCodCia(), dto.getCodPyto());
            Optional<Proyecto> opt = proyectoRepository.findById(id);
            if (opt.isEmpty()) {
                throw new ServiceException("Proyecto no encontrado para actualizar");
            }
            Proyecto entity = opt.get();
            if (entity == null) {
                throw new ServiceException("La entidad Proyecto obtenida es null");
            }
            proyectoMapper.updateEntityFromDTO(dto, entity);
            Proyecto updated = proyectoRepository.save(entity);
            return proyectoMapper.toDTO(updated);
        } catch (Exception e) {
            throw new ServiceException("Error al actualizar el proyecto", e);
        }
    }

    public boolean delete(ProyectoId id) {
        try {
            if (id == null) {
                throw new ServiceException("El ID de Proyecto no puede ser null");
            }
            if (!proyectoRepository.existsById(id)) {
                return false;
            }
            proyectoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new ServiceException("Error al eliminar el proyecto", e);
        }
    }
}
