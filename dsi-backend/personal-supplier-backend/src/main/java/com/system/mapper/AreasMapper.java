package com.system.mapper;

import com.system.dto.AreasDTO;
import com.system.entity.Areas;
import com.system.entity.id.AreasId;
import org.mapstruct.*;

@Mapper(componentModel = "spring", imports = {AreasId.class})
public interface AreasMapper {
    // DTO -> Entidad
    @Mapping(target = "id", expression = "java(new AreasId(dto.getCodCia(), dto.getCodArea()))")
    @Mapping(target = "nombre", source = "nombre")
    @Mapping(target = "descripcion", source = "descripcion")
    @Mapping(target = "estado", source = "estado")
    @Mapping(target = "cia", ignore = true)
    Areas toEntity(AreasDTO dto);

    // Entidad -> DTO
    @Mapping(target = "codCia", expression = "java(entity.getId() != null ? entity.getId().getCodCia() : null)")
    @Mapping(target = "codArea", expression = "java(entity.getId() != null ? entity.getId().getCodArea() : null)")
    @Mapping(target = "nombre", source = "nombre")
    @Mapping(target = "descripcion", source = "descripcion")
    @Mapping(target = "estado", source = "estado")
    AreasDTO toDTO(Areas entity);

    // Actualización parcial de entidad desde DTO
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", expression = "java(new AreasId(dto.getCodCia(), dto.getCodArea()))")
    @Mapping(target = "nombre", source = "nombre")
    @Mapping(target = "descripcion", source = "descripcion")
    @Mapping(target = "estado", source = "estado")
    @Mapping(target = "cia", ignore = true)
    void updateEntityFromDTO(AreasDTO dto, @MappingTarget Areas entity);
}
