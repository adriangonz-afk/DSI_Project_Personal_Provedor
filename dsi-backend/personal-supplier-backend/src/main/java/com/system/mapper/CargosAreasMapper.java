package com.system.mapper;

import com.system.dto.CargosAreasDTO;
import com.system.entity.CargosAreas;
import com.system.entity.id.CargosAreasId;
import org.mapstruct.*;

@Mapper(componentModel = "spring", imports = {CargosAreasId.class})
public interface CargosAreasMapper {
    // DTO -> Entidad
    @Mapping(target = "id", expression = "java(new CargosAreasId(dto.getCodCargo(), dto.getCodArea(), dto.getCodCia()))")
    @Mapping(target = "nombreCargo", source = "nombreCargo")
    @Mapping(target = "areas", ignore = true)
    CargosAreas toEntity(CargosAreasDTO dto);

    // Entidad -> DTO
    @Mapping(target = "codCargo", expression = "java(entity.getId() != null ? entity.getId().getCodCargo() : null)")
    @Mapping(target = "codArea", expression = "java(entity.getId() != null ? entity.getId().getCodArea() : null)")
    @Mapping(target = "codCia", expression = "java(entity.getId() != null ? entity.getId().getCodCia() : null)")
    @Mapping(target = "nombreCargo", source = "nombreCargo")
    CargosAreasDTO toDTO(CargosAreas entity);

    // Actualización parcial de entidad desde DTO
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", expression = "java(new CargosAreasId(dto.getCodCargo(), dto.getCodArea(), dto.getCodCia()))")
    @Mapping(target = "nombreCargo", source = "nombreCargo")
    @Mapping(target = "areas", ignore = true)
    void updateEntityFromDTO(CargosAreasDTO dto, @MappingTarget CargosAreas entity);
}
