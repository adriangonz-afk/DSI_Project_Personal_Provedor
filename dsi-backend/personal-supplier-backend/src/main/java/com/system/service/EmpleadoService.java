package com.system.service;

import com.system.dto.EmpleadoDTO;
import com.system.dto.EmpleadoCompletoDTO;
import com.system.entity.Empleado;
import com.system.entity.id.EmpleadoId;
import com.system.mapper.EmpleadoMapper;
import com.system.mapper.CompPagoempleadoMapper;
import com.system.mapper.EspecialidadesPersonalMapper;
import com.system.mapper.EvaluacionesDesempenoMapper;
import com.system.mapper.ExperienciaLaboralMapper;
import com.system.mapper.GradosAcademicosMapper;
import com.system.mapper.PersonalProyectosMapper;
import com.system.mapper.TareasPersonalMapper;
import com.system.repository.EmpleadoRepository;
import com.system.repository.CompPagoempleadoRepository;
import com.system.repository.EspecialidadesPersonalRepository;
import com.system.repository.EvaluacionesDesempenoRepository;
import com.system.repository.ExperienciaLaboralRepository;
import com.system.repository.GradosAcademicosRepository;
import com.system.repository.PersonalProyectosRepository;
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
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private EmpleadoMapper empleadoMapper;
    
    // Repositorios para las entidades relacionadas
    @Autowired
    private CompPagoempleadoRepository compPagoempleadoRepository;
    
    @Autowired
    private EspecialidadesPersonalRepository especialidadesPersonalRepository;
    
    @Autowired
    private EvaluacionesDesempenoRepository evaluacionesDesempenoRepository;
    
    @Autowired
    private ExperienciaLaboralRepository experienciaLaboralRepository;
    
    @Autowired
    private GradosAcademicosRepository gradosAcademicosRepository;
    
    @Autowired
    private PersonalProyectosRepository personalProyectosRepository;
    
    @Autowired
    private TareasPersonalRepository tareasPersonalRepository;
    
    // Mappers para las entidades relacionadas
    @Autowired
    private CompPagoempleadoMapper compPagoempleadoMapper;
    
    @Autowired
    private EspecialidadesPersonalMapper especialidadesPersonalMapper;
    
    @Autowired
    private EvaluacionesDesempenoMapper evaluacionesDesempenoMapper;
    
    @Autowired
    private ExperienciaLaboralMapper experienciaLaboralMapper;
    
    @Autowired
    private GradosAcademicosMapper gradosAcademicosMapper;
    
    @Autowired
    private PersonalProyectosMapper personalProyectosMapper;
    
    @Autowired
    private TareasPersonalMapper tareasPersonalMapper;

    public EmpleadoDTO save(EmpleadoDTO dto) {
        try {
            if (dto == null) {
                throw new ServiceException("El DTO de Empleado no puede ser null");
            }
            Empleado entity = empleadoMapper.toEntity(dto);
            if (entity == null) {
                throw new ServiceException("Error al convertir DTO a entidad: resultado null");
            }
            Empleado saved = empleadoRepository.save(entity);
            return empleadoMapper.toDTO(saved);
        } catch (Exception e) {
            throw new ServiceException("Error al guardar Empleado", e);
        }
    }

    public EmpleadoDTO findById(EmpleadoId id) {
        try {
            if (id == null) {
                throw new ServiceException("El ID de Empleado no puede ser null");
            }
            Optional<Empleado> opt = empleadoRepository.findById(id);
            if (opt.isPresent()) {
                return empleadoMapper.toDTO(opt.get());
            } else {
                throw new ServiceException("Empleado no encontrado con id: " + id);
            }
        } catch (Exception e) {
            throw new ServiceException("Error al buscar Empleado", e);
        }
    }

    public List<EmpleadoDTO> findAll() {
        try {
            List<Empleado> list = empleadoRepository.findAll();
            return list.stream().map(empleadoMapper::toDTO).collect(Collectors.toList());
        } catch (Exception e) {
            throw new ServiceException("Error al listar Empleados", e);
        }
    }

    public EmpleadoDTO update(EmpleadoDTO dto) {
        try {
            if (dto == null) {
                throw new ServiceException("El DTO de Empleado no puede ser null");
            }
            EmpleadoId id = new EmpleadoId(dto.getCodCia(), dto.getCodEmpleado());
            Optional<Empleado> opt = empleadoRepository.findById(id);
            if (opt.isPresent()) {
                Empleado entity = opt.get();
                if (entity == null) {
                    throw new ServiceException("La entidad Empleado obtenida es null");
                }
                empleadoMapper.updateEntityFromDTO(dto, entity);
                Empleado updated = empleadoRepository.save(entity);
                return empleadoMapper.toDTO(updated);
            } else {
                throw new ServiceException("Empleado no encontrado para actualizar con id: " + id);
            }
        } catch (Exception e) {
            throw new ServiceException("Error al actualizar Empleado", e);
        }
    }

    public boolean delete(EmpleadoId id) {
        try {
            if (id == null) {
                throw new ServiceException("El ID de Empleado no puede ser null");
            }
            if (empleadoRepository.existsById(id)) {
                empleadoRepository.deleteById(id);
                return true;
            } else {
                throw new ServiceException("Empleado no encontrado para eliminar con id: " + id);
            }
        } catch (Exception e) {
            throw new ServiceException("Error al eliminar Empleado", e);
        }
    }

    public EmpleadoCompletoDTO findByIdCompleto(EmpleadoId id) {
        try {
            if (id == null) {
                throw new ServiceException("El ID de Empleado no puede ser null");
            }
            
            // Buscar el empleado principal
            Optional<Empleado> opt = empleadoRepository.findById(id);
            if (!opt.isPresent()) {
                throw new ServiceException("Empleado no encontrado con id: " + id);
            }
            
            Empleado empleado = opt.get();
            
            // Mapear el empleado base
            EmpleadoCompletoDTO empleadoCompleto = empleadoMapper.toCompletoDTO(empleado);
            
            // Obtener y mapear todas las listas relacionadas
            empleadoCompleto.setComprobantePagos(
                compPagoempleadoRepository.findByIdCodCiaAndIdCodEmpleado(id.getCodCia(), id.getCodEmpleado())
                    .stream()
                    .map(compPagoempleadoMapper::toDTO)
                    .collect(Collectors.toList())
            );
            
            empleadoCompleto.setEspecialidades(
                especialidadesPersonalRepository.findByIdCodCiaAndIdCodEmpleado(id.getCodCia(), id.getCodEmpleado())
                    .stream()
                    .map(especialidadesPersonalMapper::toDTO)
                    .collect(Collectors.toList())
            );
            
            empleadoCompleto.setEvaluacionesDesempeno(
                evaluacionesDesempenoRepository.findByIdCodCiaAndIdCodEmpleado(id.getCodCia(), id.getCodEmpleado())
                    .stream()
                    .map(evaluacionesDesempenoMapper::toDTO)
                    .collect(Collectors.toList())
            );
            
            empleadoCompleto.setExperienciasLaborales(
                experienciaLaboralRepository.findByIdCodCiaAndIdCodEmpleado(id.getCodCia(), id.getCodEmpleado())
                    .stream()
                    .map(experienciaLaboralMapper::toDTO)
                    .collect(Collectors.toList())
            );
            
            empleadoCompleto.setGradosAcademicos(
                gradosAcademicosRepository.findByIdCodCiaAndIdCodEmpleado(id.getCodCia(), id.getCodEmpleado())
                    .stream()
                    .map(gradosAcademicosMapper::toDTO)
                    .collect(Collectors.toList())
            );
            
            empleadoCompleto.setProyectos(
                personalProyectosRepository.findByIdCodCiaAndIdCodEmpleado(id.getCodCia(), id.getCodEmpleado())
                    .stream()
                    .map(personalProyectosMapper::toDTO)
                    .collect(Collectors.toList())
            );
            
            empleadoCompleto.setTareas(
                tareasPersonalRepository.findByIdCodCiaAndIdCodEmpleado(id.getCodCia(), id.getCodEmpleado())
                    .stream()
                    .map(tareasPersonalMapper::toDTO)
                    .collect(Collectors.toList())
            );
            
            return empleadoCompleto;
            
        } catch (Exception e) {
            throw new ServiceException("Error al buscar información completa del Empleado", e);
        }
    }

    public List<EmpleadoCompletoDTO> findAllCompletos() {
        try {
            // Usar el método personalizado con fetch join
            List<Empleado> empleados = empleadoRepository.findAllWithPersona();
            
            return empleados.stream().map(empleado -> {
                try {
                    EmpleadoId id = empleado.getId();
                    
                    // Mapear el empleado base
                    EmpleadoCompletoDTO empleadoCompleto = empleadoMapper.toCompletoDTO(empleado);
                    
                    // Obtener y mapear todas las listas relacionadas
                    empleadoCompleto.setComprobantePagos(
                        compPagoempleadoRepository.findByIdCodCiaAndIdCodEmpleado(id.getCodCia(), id.getCodEmpleado())
                            .stream()
                            .map(compPagoempleadoMapper::toDTO)
                            .collect(Collectors.toList())
                    );
                    
                    empleadoCompleto.setEspecialidades(
                        especialidadesPersonalRepository.findByIdCodCiaAndIdCodEmpleado(id.getCodCia(), id.getCodEmpleado())
                            .stream()
                            .map(especialidadesPersonalMapper::toDTO)
                            .collect(Collectors.toList())
                    );
                    
                    empleadoCompleto.setEvaluacionesDesempeno(
                        evaluacionesDesempenoRepository.findByIdCodCiaAndIdCodEmpleado(id.getCodCia(), id.getCodEmpleado())
                            .stream()
                            .map(evaluacionesDesempenoMapper::toDTO)
                            .collect(Collectors.toList())
                    );
                    
                    empleadoCompleto.setExperienciasLaborales(
                        experienciaLaboralRepository.findByIdCodCiaAndIdCodEmpleado(id.getCodCia(), id.getCodEmpleado())
                            .stream()
                            .map(experienciaLaboralMapper::toDTO)
                            .collect(Collectors.toList())
                    );
                    
                    empleadoCompleto.setGradosAcademicos(
                        gradosAcademicosRepository.findByIdCodCiaAndIdCodEmpleado(id.getCodCia(), id.getCodEmpleado())
                            .stream()
                            .map(gradosAcademicosMapper::toDTO)
                            .collect(Collectors.toList())
                    );
                    
                    empleadoCompleto.setProyectos(
                        personalProyectosRepository.findByIdCodCiaAndIdCodEmpleado(id.getCodCia(), id.getCodEmpleado())
                            .stream()
                            .map(personalProyectosMapper::toDTO)
                            .collect(Collectors.toList())
                    );
                    
                    empleadoCompleto.setTareas(
                        tareasPersonalRepository.findByIdCodCiaAndIdCodEmpleado(id.getCodCia(), id.getCodEmpleado())
                            .stream()
                            .map(tareasPersonalMapper::toDTO)
                            .collect(Collectors.toList())
                    );
                    
                    return empleadoCompleto;
                } catch (Exception e) {
                    throw new RuntimeException("Error procesando empleado: " + empleado.getId().getCodCia() + "/" + empleado.getId().getCodEmpleado(), e);
                }
            }).collect(Collectors.toList());
            
        } catch (Exception e) {
            throw new ServiceException("Error al listar información completa de Empleados", e);
        }
    }
}
