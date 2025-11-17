package com.system.mapper;

import com.system.dto.ServiciosProveedorDTO;
import com.system.entity.ServiciosProveedor;
import com.system.entity.id.ServiciosProveedorId;
import org.mapstruct.*;

@Mapper(componentModel = "spring", imports = {ServiciosProveedorId.class})
public interface ServiciosProveedorMapper {

    @Mapping(target = "codCia", expression = "java(entity.getId() != null ? entity.getId().getCodCia() : null)")
    @Mapping(target = "codServicio", expression = "java(entity.getId() != null ? entity.getId().getCodServicio() : null)")
    @Mapping(target = "codProveedor", expression = "java(entity.getId() != null ? entity.getId().getCodProveedor() : null)")
    ServiciosProveedorDTO toDTO(ServiciosProveedor entity);

    @Mapping(target = "id", expression = "java(new ServiciosProveedorId(dto.getCodCia(), dto.getCodServicio(), dto.getCodProveedor()))")
    @Mapping(target = "proveedor", ignore = true)
    ServiciosProveedor toEntity(ServiciosProveedorDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", expression = "java(new ServiciosProveedorId(dto.getCodCia(), dto.getCodServicio(), dto.getCodProveedor()))")
    @Mapping(target = "proveedor", ignore = true)
    void updateEntityFromDTO(ServiciosProveedorDTO dto, @MappingTarget ServiciosProveedor entity);
}
