package com.system.mapper;

import com.system.dto.TareasPersonalDTO;
import com.system.entity.TareasPersonal;
import com.system.entity.id.TareasPersonalId;
import org.mapstruct.*;
import java.sql.Date;
import java.time.LocalDate;

@Mapper(componentModel = "spring", imports = {TareasPersonalId.class, Date.class, LocalDate.class})
public interface TareasPersonalMapper {

    @Mapping(target = "codCia", expression = "java(entity.getId() != null ? entity.getId().getCodCia() : null)")
    @Mapping(target = "codTarea", expression = "java(entity.getId() != null ? entity.getId().getCodTarea() : null)")
    @Mapping(target = "codPyto", expression = "java(entity.getId() != null ? entity.getId().getCodPyto() : null)")
    @Mapping(target = "codEmpleado", expression = "java(entity.getId() != null ? entity.getId().getCodEmpleado() : null)")
    @Mapping(target = "fechaInicio", expression = "java(entity.getFechaInicio() != null ? Date.valueOf(entity.getFechaInicio()) : null)")
    @Mapping(target = "fechaFin", expression = "java(entity.getFechaFin() != null ? Date.valueOf(entity.getFechaFin()) : null)")
    TareasPersonalDTO toDTO(TareasPersonal entity);

    @Mapping(target = "id", expression = "java(new TareasPersonalId(dto.getCodCia(), dto.getCodTarea(), dto.getCodPyto(), dto.getCodEmpleado()))")
    @Mapping(target = "fechaInicio", expression = "java(dto.getFechaInicio() != null ? dto.getFechaInicio().toLocalDate() : null)")
    @Mapping(target = "fechaFin", expression = "java(dto.getFechaFin() != null ? dto.getFechaFin().toLocalDate() : null)")
    @Mapping(target = "proyecto", ignore = true)
    @Mapping(target = "empleado", ignore = true)
    TareasPersonal toEntity(TareasPersonalDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", expression = "java(new TareasPersonalId(dto.getCodCia(), dto.getCodTarea(), dto.getCodPyto(), dto.getCodEmpleado()))")
    @Mapping(target = "fechaInicio", expression = "java(dto.getFechaInicio() != null ? dto.getFechaInicio().toLocalDate() : null)")
    @Mapping(target = "fechaFin", expression = "java(dto.getFechaFin() != null ? dto.getFechaFin().toLocalDate() : null)")
    @Mapping(target = "proyecto", ignore = true)
    @Mapping(target = "empleado", ignore = true)
    void updateEntityFromDTO(TareasPersonalDTO dto, @MappingTarget TareasPersonal entity);
}