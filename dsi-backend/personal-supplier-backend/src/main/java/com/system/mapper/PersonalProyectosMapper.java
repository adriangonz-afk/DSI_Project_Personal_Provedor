package com.system.mapper;

import com.system.dto.PersonalProyectosDTO;
import com.system.entity.PersonalProyectos;
import com.system.entity.id.PersonalProyectosId;
import org.mapstruct.*;
import java.math.BigDecimal;
import java.sql.Date;

@Mapper(componentModel = "spring", imports = {PersonalProyectosId.class, BigDecimal.class, Date.class})
public interface PersonalProyectosMapper {

    @Mapping(target = "codCia", expression = "java(entity.getId() != null ? entity.getId().getCodCia() : null)")
    @Mapping(target = "codPersonalProyecto", expression = "java(entity.getId() != null ? entity.getId().getCodPersonalProyecto() : null)")
    @Mapping(target = "codEmpleado", expression = "java(entity.getId() != null ? entity.getId().getCodEmpleado() : null)")
    @Mapping(target = "fechaAsignacion", expression = "java(entity.getFechaAsignacion() != null ? java.sql.Date.valueOf(entity.getFechaAsignacion()) : null)")
    @Mapping(target = "fechaDesasignacion", expression = "java(entity.getFechaDesasignacion() != null ? java.sql.Date.valueOf(entity.getFechaDesasignacion()) : null)")
    @Mapping(target = "horasAsignadas", expression = "java(entity.getHorasAsignadas() != null ? entity.getHorasAsignadas().doubleValue() : null)")
    PersonalProyectosDTO toDTO(PersonalProyectos entity);

    @Mapping(target = "id", expression = "java(new PersonalProyectosId(dto.getCodCia(), dto.getCodPersonalProyecto(), dto.getCodEmpleado()))")
    @Mapping(target = "fechaAsignacion", expression = "java(dto.getFechaAsignacion() != null ? dto.getFechaAsignacion().toLocalDate() : null)")
    @Mapping(target = "fechaDesasignacion", expression = "java(dto.getFechaDesasignacion() != null ? dto.getFechaDesasignacion().toLocalDate() : null)")
    @Mapping(target = "horasAsignadas", expression = "java(dto.getHorasAsignadas() != null ? BigDecimal.valueOf(dto.getHorasAsignadas()) : null)")
    @Mapping(target = "empleado", ignore = true)
    @Mapping(target = "proyecto", ignore = true)
    @Mapping(target = "cargosAreas", ignore = true)
    @Mapping(target = "areas", ignore = true)
    PersonalProyectos toEntity(PersonalProyectosDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", expression = "java(new PersonalProyectosId(dto.getCodCia(), dto.getCodPersonalProyecto(), dto.getCodEmpleado()))")
    @Mapping(target = "fechaAsignacion", expression = "java(dto.getFechaAsignacion() != null ? dto.getFechaAsignacion().toLocalDate() : null)")
    @Mapping(target = "fechaDesasignacion", expression = "java(dto.getFechaDesasignacion() != null ? dto.getFechaDesasignacion().toLocalDate() : null)")
    @Mapping(target = "horasAsignadas", expression = "java(dto.getHorasAsignadas() != null ? BigDecimal.valueOf(dto.getHorasAsignadas()) : null)")
    @Mapping(target = "empleado", ignore = true)
    @Mapping(target = "proyecto", ignore = true)
    @Mapping(target = "cargosAreas", ignore = true)
    @Mapping(target = "areas", ignore = true)
    void updateEntityFromDTO(PersonalProyectosDTO dto, @MappingTarget PersonalProyectos entity);
}
