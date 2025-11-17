package com.system.mapper;

import com.system.dto.EspecialidadesPersonalDTO;
import com.system.entity.EspecialidadesPersonal;
import com.system.entity.id.EspecialidadesPersonalId;
import org.mapstruct.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

@Mapper(componentModel = "spring", imports = {BigDecimal.class, EspecialidadesPersonalId.class, LocalDate.class, Date.class})
public interface EspecialidadesPersonalMapper {

    @Mapping(target = "codCia", expression = "java(entity.getId() != null ? entity.getId().getCodCia() : null)")
    @Mapping(target = "codEspecialidad", expression = "java(entity.getId() != null ? entity.getId().getCodEspecialidad() : null)")
    @Mapping(target = "codEmpleado", expression = "java(entity.getId() != null ? entity.getId().getCodEmpleado() : null)")
    @Mapping(target = "horasCapacitacion", expression = "java(entity.getHorasCapacitacion() != null ? entity.getHorasCapacitacion().doubleValue() : null)")
    @Mapping(target = "fechaObtencion", expression = "java(entity.getFechaObtencion() != null ? java.sql.Date.valueOf(entity.getFechaObtencion()) : null)")
    EspecialidadesPersonalDTO toDTO(EspecialidadesPersonal entity);

    @Mapping(target = "id", expression = "java(new EspecialidadesPersonalId(dto.getCodCia(), dto.getCodEspecialidad(), dto.getCodEmpleado()))")
    @Mapping(target = "horasCapacitacion", expression = "java(dto.getHorasCapacitacion() != null ? BigDecimal.valueOf(dto.getHorasCapacitacion()) : null)")
    @Mapping(target = "fechaObtencion", expression = "java(dto.getFechaObtencion() != null ? dto.getFechaObtencion().toLocalDate() : null)")
    @Mapping(target = "empleado", ignore = true)
    EspecialidadesPersonal toEntity(EspecialidadesPersonalDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", expression = "java(new EspecialidadesPersonalId(dto.getCodCia(), dto.getCodEspecialidad(), dto.getCodEmpleado()))")
    @Mapping(target = "horasCapacitacion", expression = "java(dto.getHorasCapacitacion() != null ? BigDecimal.valueOf(dto.getHorasCapacitacion()) : null)")
    @Mapping(target = "fechaObtencion", expression = "java(dto.getFechaObtencion() != null ? dto.getFechaObtencion().toLocalDate() : null)")
    @Mapping(target = "empleado", ignore = true)
    void updateEntityFromDTO(EspecialidadesPersonalDTO dto, @MappingTarget EspecialidadesPersonal entity);

}