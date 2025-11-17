package com.system.service;

import com.system.dto.filters.PersonalFilterCriteria;
import com.system.dto.filters.PersonalFiltersOptionsDTO;
import com.system.dto.filters.PersonalFiltradoDTO;
import com.system.entity.Empleado;
import com.system.mapper.PersonalFilterMapper;
import com.system.repository.PersonalFilterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PersonalFilterService {

    @Autowired
    private PersonalFilterRepository personalFilterRepository;
    
    @Autowired
    private PersonalFilterMapper personalFilterMapper;

    @Transactional(readOnly = true)
    public PersonalFiltersOptionsDTO getFilterOptions() {
        // Capacitacion
        var especialidadesCap = personalFilterRepository.findAvailableEspecialidadesCapacitacion();
        java.math.BigDecimal horasMin = null;
        java.math.BigDecimal horasMax = null;
        List<String> nombresCap = new java.util.ArrayList<>();
        for (var e : especialidadesCap) {
            nombresCap.add(e.getEspecialidad());
            if (e.getHorasMinimas() != null) {
                if (horasMin == null || e.getHorasMinimas().compareTo(horasMin) < 0) horasMin = e.getHorasMinimas();
            }
            if (e.getHorasMaximas() != null) {
                if (horasMax == null || e.getHorasMaximas().compareTo(horasMax) > 0) horasMax = e.getHorasMaximas();
            }
        }

        // Experiencia
        var especialidadesExp = personalFilterRepository.findAvailableEspecialidadesExperiencia();
        Integer anosMin = null;
        Integer anosMax = null;
        List<String> nombresExp = new java.util.ArrayList<>();
        for (var e : especialidadesExp) {
            nombresExp.add(e.getEspecialidad());
            if (e.getAnosMinimos() != null) {
                if (anosMin == null || e.getAnosMinimos() < anosMin) anosMin = e.getAnosMinimos();
            }
            if (e.getAnosMaximos() != null) {
                if (anosMax == null || e.getAnosMaximos() > anosMax) anosMax = e.getAnosMaximos();
            }
        }

        return PersonalFiltersOptionsDTO.builder()
                .proyectos(personalFilterRepository.findAvailableProjects())
                .areas(personalFilterRepository.findAvailableAreas())
                .cargos(personalFilterRepository.findAvailableCargos())
                .universidades(personalFilterRepository.findAvailableUniversidades())
                .carreras(personalFilterRepository.findAvailableCarreras())
                .especialidadesCapacitacion(nombresCap)
                .horasMinimasCapacitacion(horasMin)
                .horasMaximasCapacitacion(horasMax)
                .especialidadesExperiencia(nombresExp)
                .anosMinimosExperiencia(anosMin)
                .anosMaximosExperiencia(anosMax)
                .build();
    }

    @Transactional(readOnly = true)
    public List<PersonalFiltradoDTO> getFilteredPersonal(PersonalFilterCriteria criteria) {
        List<Empleado> empleados = personalFilterRepository.findEmpleadosWithFilters(criteria);
        List<PersonalFiltradoDTO> dtos = personalFilterMapper.toPersonalFiltradoDTOList(empleados);
        
        // Enriquecer cada DTO con información adicional
        for (PersonalFiltradoDTO dto : dtos) {
            dto.setProyectosActuales(personalFilterRepository.findProyectosByEmpleado(dto.getCodCia(), dto.getCodEmpleado()));
            dto.setAreasActuales(personalFilterRepository.findAreasByEmpleado(dto.getCodCia(), dto.getCodEmpleado()));
            dto.setCargosActuales(personalFilterRepository.findCargosByEmpleado(dto.getCodCia(), dto.getCodEmpleado()));
            dto.setUniversidades(personalFilterRepository.findUniversidadesByEmpleado(dto.getCodCia(), dto.getCodEmpleado()));
            dto.setCarreras(personalFilterRepository.findCarrerasByEmpleado(dto.getCodCia(), dto.getCodEmpleado()));
            dto.setEspecialidadesCapacitacion(personalFilterRepository.findEspecialidadesCapacitacionByEmpleado(dto.getCodCia(), dto.getCodEmpleado()));
            dto.setEspecialidadesExperiencia(personalFilterRepository.findEspecialidadesExperienciaByEmpleado(dto.getCodCia(), dto.getCodEmpleado()));
        }
        
        return dtos;
    }
}