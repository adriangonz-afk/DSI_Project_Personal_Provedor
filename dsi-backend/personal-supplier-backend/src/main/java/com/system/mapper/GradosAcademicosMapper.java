package com.system.mapper;

import com.system.dto.GradosAcademicosDTO;
import com.system.entity.GradosAcademicos;
import com.system.entity.id.GradosAcademicosId;
import org.mapstruct.*;
import java.sql.Date;

@Mapper(componentModel = "spring", imports = {GradosAcademicosId.class, Date.class})
public interface GradosAcademicosMapper {

    @Mapping(target = "codCia", expression = "java(entity.getId() != null ? entity.getId().getCodCia() : null)")
    @Mapping(target = "codGrado", expression = "java(entity.getId() != null ? entity.getId().getCodGrado() : null)")
    @Mapping(target = "codEmpleado", expression = "java(entity.getId() != null ? entity.getId().getCodEmpleado() : null)")
    @Mapping(target = "fechaObtencion", expression = "java(entity.getFechaObtencion() != null ? java.sql.Date.valueOf(entity.getFechaObtencion()) : null)")
    GradosAcademicosDTO toDTO(GradosAcademicos entity);

    @Mapping(target = "id", expression = "java(new GradosAcademicosId(dto.getCodCia(), dto.getCodGrado(), dto.getCodEmpleado()))")
    @Mapping(target = "fechaObtencion", expression = "java(dto.getFechaObtencion() != null ? dto.getFechaObtencion().toLocalDate() : null)")
    @Mapping(target = "empleado", ignore = true)
    GradosAcademicos toEntity(GradosAcademicosDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", expression = "java(new GradosAcademicosId(dto.getCodCia(), dto.getCodGrado(), dto.getCodEmpleado()))")
    @Mapping(target = "fechaObtencion", expression = "java(dto.getFechaObtencion() != null ? dto.getFechaObtencion().toLocalDate() : null)")
    @Mapping(target = "empleado", ignore = true)
    void updateEntityFromDTO(GradosAcademicosDTO dto, @MappingTarget GradosAcademicos entity);
}
