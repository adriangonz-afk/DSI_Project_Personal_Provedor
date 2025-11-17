package com.system.mapper;

import com.system.dto.EvaluacionesDesempenoDTO;
import com.system.entity.EvaluacionesDesempeno;
import com.system.entity.id.EvaluacionesDesempenoId;
import org.mapstruct.*;
import java.math.BigDecimal;

@Mapper(componentModel = "spring", imports = {BigDecimal.class, EvaluacionesDesempenoId.class})
public interface EvaluacionesDesempenoMapper {

    @Mapping(target = "codCia", expression = "java(entity.getId() != null ? entity.getId().getCodCia() : null)")
    @Mapping(target = "codEvaluacion", expression = "java(entity.getId() != null ? entity.getId().getCodEvaluacion() : null)")
    @Mapping(target = "codEmpleado", expression = "java(entity.getId() != null ? entity.getId().getCodEmpleado() : null)")
    @Mapping(target = "codPyto", expression = "java(entity.getId() != null ? entity.getId().getCodPyto() : null)")
    @Mapping(target = "puntuacionTotal", expression = "java(entity.getPuntuacionTotal() != null ? entity.getPuntuacionTotal().doubleValue() : null)")
    @Mapping(target = "competenciasTecnicas", expression = "java(entity.getCompetenciasTecnicas() != null ? entity.getCompetenciasTecnicas().doubleValue() : null)")
    @Mapping(target = "competenciasBlandas", expression = "java(entity.getCompetenciasBlandas() != null ? entity.getCompetenciasBlandas().doubleValue() : null)")
    @Mapping(target = "cumplimientoObjetivos", expression = "java(entity.getCumplimientoObjetivos() != null ? entity.getCumplimientoObjetivos().doubleValue() : null)")
    EvaluacionesDesempenoDTO toDTO(EvaluacionesDesempeno entity);

    @Mapping(target = "id", expression = "java(new EvaluacionesDesempenoId(dto.getCodCia(), dto.getCodEvaluacion(), dto.getCodEmpleado(), dto.getCodPyto()))")
    @Mapping(target = "puntuacionTotal", expression = "java(dto.getPuntuacionTotal() != null ? BigDecimal.valueOf(dto.getPuntuacionTotal()) : null)")
    @Mapping(target = "competenciasTecnicas", expression = "java(dto.getCompetenciasTecnicas() != null ? BigDecimal.valueOf(dto.getCompetenciasTecnicas()) : null)")
    @Mapping(target = "competenciasBlandas", expression = "java(dto.getCompetenciasBlandas() != null ? BigDecimal.valueOf(dto.getCompetenciasBlandas()) : null)")
    @Mapping(target = "cumplimientoObjetivos", expression = "java(dto.getCumplimientoObjetivos() != null ? BigDecimal.valueOf(dto.getCumplimientoObjetivos()) : null)")
    @Mapping(target = "empleado", ignore = true)
    @Mapping(target = "proyecto", ignore = true)
    @Mapping(target = "evaluador", ignore = true)
    EvaluacionesDesempeno toEntity(EvaluacionesDesempenoDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", expression = "java(new EvaluacionesDesempenoId(dto.getCodCia(), dto.getCodEvaluacion(), dto.getCodEmpleado(), dto.getCodPyto()))")
    @Mapping(target = "puntuacionTotal", expression = "java(dto.getPuntuacionTotal() != null ? BigDecimal.valueOf(dto.getPuntuacionTotal()) : null)")
    @Mapping(target = "competenciasTecnicas", expression = "java(dto.getCompetenciasTecnicas() != null ? BigDecimal.valueOf(dto.getCompetenciasTecnicas()) : null)")
    @Mapping(target = "competenciasBlandas", expression = "java(dto.getCompetenciasBlandas() != null ? BigDecimal.valueOf(dto.getCompetenciasBlandas()) : null)")
    @Mapping(target = "cumplimientoObjetivos", expression = "java(dto.getCumplimientoObjetivos() != null ? BigDecimal.valueOf(dto.getCumplimientoObjetivos()) : null)")
    @Mapping(target = "empleado", ignore = true)
    @Mapping(target = "proyecto", ignore = true)
    @Mapping(target = "evaluador", ignore = true)
    void updateEntityFromDTO(EvaluacionesDesempenoDTO dto, @MappingTarget EvaluacionesDesempeno entity);
}
