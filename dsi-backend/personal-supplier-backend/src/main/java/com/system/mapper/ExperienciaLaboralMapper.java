package com.system.mapper;

import com.system.dto.ExperienciaLaboralDTO;
import com.system.entity.ExperienciaLaboral;
import com.system.entity.id.ExperienciaLaboralId;
import org.mapstruct.*;
import java.sql.Date;

@Mapper(componentModel = "spring", imports = {ExperienciaLaboralId.class, Date.class})
public interface ExperienciaLaboralMapper {

    @Mapping(target = "codCia", expression = "java(entity.getId() != null ? entity.getId().getCodCia() : null)")
    @Mapping(target = "codExperiencia", expression = "java(entity.getId() != null ? entity.getId().getCodExperiencia() : null)")
    @Mapping(target = "codEmpleado", expression = "java(entity.getId() != null ? entity.getId().getCodEmpleado() : null)")
    @Mapping(target = "fechaInicio", expression = "java(entity.getFechaInicio() != null ? java.sql.Date.valueOf(entity.getFechaInicio()) : null)")
    @Mapping(target = "fechaFin", expression = "java(entity.getFechaFin() != null ? java.sql.Date.valueOf(entity.getFechaFin()) : null)")
    ExperienciaLaboralDTO toDTO(ExperienciaLaboral entity);

    @Mapping(target = "id", expression = "java(new ExperienciaLaboralId(dto.getCodCia(), dto.getCodExperiencia(), dto.getCodEmpleado()))")
    @Mapping(target = "fechaInicio", expression = "java(dto.getFechaInicio() != null ? dto.getFechaInicio().toLocalDate() : null)")
    @Mapping(target = "fechaFin", expression = "java(dto.getFechaFin() != null ? dto.getFechaFin().toLocalDate() : null)")
    @Mapping(target = "empleado", ignore = true)
    ExperienciaLaboral toEntity(ExperienciaLaboralDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", expression = "java(new ExperienciaLaboralId(dto.getCodCia(), dto.getCodExperiencia(), dto.getCodEmpleado()))")
    @Mapping(target = "fechaInicio", expression = "java(dto.getFechaInicio() != null ? dto.getFechaInicio().toLocalDate() : null)")
    @Mapping(target = "fechaFin", expression = "java(dto.getFechaFin() != null ? dto.getFechaFin().toLocalDate() : null)")
    @Mapping(target = "empleado", ignore = true)
    void updateEntityFromDTO(ExperienciaLaboralDTO dto, @MappingTarget ExperienciaLaboral entity);
}
