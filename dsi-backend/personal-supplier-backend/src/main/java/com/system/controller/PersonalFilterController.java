package com.system.controller;

import com.system.dto.filters.PersonalFilterCriteria;
import com.system.dto.filters.PersonalFiltersOptionsDTO;
import com.system.dto.filters.PersonalFiltradoDTO;
import com.system.service.PersonalFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/personal")
@CrossOrigin(origins = "*")
public class PersonalFilterController {

    @Autowired
    private PersonalFilterService personalFilterService;

    /**
     * Obtiene todas las opciones disponibles para filtros
     */
    @GetMapping("/opciones-filtros")
    public ResponseEntity<PersonalFiltersOptionsDTO> getFilterOptions() {
        try {
            PersonalFiltersOptionsDTO options = personalFilterService.getFilterOptions();
            return ResponseEntity.ok(options);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Filtros usando query parameters - permite multifiltros
     */
    @GetMapping("/filtros")
    public ResponseEntity<List<PersonalFiltradoDTO>> getFilteredPersonal(
            @RequestParam(required = false) String proyecto,
            @RequestParam(required = false) String area,
            @RequestParam(required = false) String cargo,
            @RequestParam(required = false) String universidad,
            @RequestParam(required = false) String carrera,
            @RequestParam(required = false) String especialidad,
            @RequestParam(required = false) BigDecimal horasMinimas,
            @RequestParam(required = false) BigDecimal horasMaximas,
            @RequestParam(required = false) String experiencia,
            @RequestParam(required = false) Integer anosMinimos,
            @RequestParam(required = false) Integer anosMaximos) {
        
        try {
            PersonalFilterCriteria criteria = PersonalFilterCriteria.builder()
                    .proyecto(proyecto)
                    .area(area)
                    .cargo(cargo)
                    .universidad(universidad)
                    .carrera(carrera)
                    .especialidadCapacitacion(especialidad)
                    .horasMinimas(horasMinimas)
                    .horasMaximas(horasMaximas)
                    .especialidadExperiencia(experiencia)
                    .anosMinimos(anosMinimos)
                    .anosMaximos(anosMaximos)
                    .build();

            List<PersonalFiltradoDTO> results = personalFilterService.getFilteredPersonal(criteria);
            return ResponseEntity.ok(results);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Filtros usando POST con cuerpo de la petición - permite filtros más complejos
     */
    @PostMapping("/filtros")
    public ResponseEntity<List<PersonalFiltradoDTO>> getFilteredPersonalPost(
            @RequestBody PersonalFilterCriteria criteria) {
        
        try {
            List<PersonalFiltradoDTO> results = personalFilterService.getFilteredPersonal(criteria);
            return ResponseEntity.ok(results);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Filtros específicos por proyecto
     */
    @GetMapping("/filtros/proyecto/{nombreProyecto}")
    public ResponseEntity<List<PersonalFiltradoDTO>> getPersonalByProject(
            @PathVariable String nombreProyecto) {
        
        try {
            PersonalFilterCriteria criteria = PersonalFilterCriteria.builder()
                    .proyecto(nombreProyecto)
                    .build();

            List<PersonalFiltradoDTO> results = personalFilterService.getFilteredPersonal(criteria);
            return ResponseEntity.ok(results);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Filtros específicos por area
     */
    @GetMapping("/filtros/area/{nombreArea}")
    public ResponseEntity<List<PersonalFiltradoDTO>> getPersonalByArea(
            @PathVariable String nombreArea) {
        
        try {
            PersonalFilterCriteria criteria = PersonalFilterCriteria.builder()
                    .area(nombreArea)
                    .build();

            List<PersonalFiltradoDTO> results = personalFilterService.getFilteredPersonal(criteria);
            return ResponseEntity.ok(results);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Filtros específicos por cargo
     */
    @GetMapping("/filtros/cargo/{nombreCargo}")
    public ResponseEntity<List<PersonalFiltradoDTO>> getPersonalByCargo(
            @PathVariable String nombreCargo) {
        
        try {
            PersonalFilterCriteria criteria = PersonalFilterCriteria.builder()
                    .cargo(nombreCargo)
                    .build();

            List<PersonalFiltradoDTO> results = personalFilterService.getFilteredPersonal(criteria);
            return ResponseEntity.ok(results);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Filtros específicos por universidad
     */
    @GetMapping("/filtros/universidad/{nombreUniversidad}")
    public ResponseEntity<List<PersonalFiltradoDTO>> getPersonalByUniversidad(
            @PathVariable String nombreUniversidad) {
        
        try {
            PersonalFilterCriteria criteria = PersonalFilterCriteria.builder()
                    .universidad(nombreUniversidad)
                    .build();

            List<PersonalFiltradoDTO> results = personalFilterService.getFilteredPersonal(criteria);
            return ResponseEntity.ok(results);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Filtros específicos por carrera
     */
    @GetMapping("/filtros/carrera/{nombreCarrera}")
    public ResponseEntity<List<PersonalFiltradoDTO>> getPersonalByCarrera(
            @PathVariable String nombreCarrera) {
        
        try {
            PersonalFilterCriteria criteria = PersonalFilterCriteria.builder()
                    .carrera(nombreCarrera)
                    .build();

            List<PersonalFiltradoDTO> results = personalFilterService.getFilteredPersonal(criteria);
            return ResponseEntity.ok(results);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Filtros específicos por especialidad de capacitación
     */
    @GetMapping("/filtros/especialidad-capacitacion/{especialidad}")
    public ResponseEntity<List<PersonalFiltradoDTO>> getPersonalByEspecialidadCapacitacion(
            @PathVariable String especialidad,
            @RequestParam(required = false) BigDecimal horasMinimas,
            @RequestParam(required = false) BigDecimal horasMaximas) {
        
        try {
            PersonalFilterCriteria criteria = PersonalFilterCriteria.builder()
                    .especialidadCapacitacion(especialidad)
                    .horasMinimas(horasMinimas)
                    .horasMaximas(horasMaximas)
                    .build();

            List<PersonalFiltradoDTO> results = personalFilterService.getFilteredPersonal(criteria);
            return ResponseEntity.ok(results);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Filtros específicos por especialidad de experiencia
     */
    @GetMapping("/filtros/especialidad-experiencia/{especialidad}")
    public ResponseEntity<List<PersonalFiltradoDTO>> getPersonalByEspecialidadExperiencia(
            @PathVariable String especialidad,
            @RequestParam(required = false) Integer anosMinimos,
            @RequestParam(required = false) Integer anosMaximos) {
        
        try {
            PersonalFilterCriteria criteria = PersonalFilterCriteria.builder()
                    .especialidadExperiencia(especialidad)
                    .anosMinimos(anosMinimos)
                    .anosMaximos(anosMaximos)
                    .build();

            List<PersonalFiltradoDTO> results = personalFilterService.getFilteredPersonal(criteria);
            return ResponseEntity.ok(results);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}