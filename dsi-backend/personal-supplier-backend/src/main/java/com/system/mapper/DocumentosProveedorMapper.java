package com.system.mapper;

import com.system.dto.DocumentosProveedorDTO;
import com.system.entity.DocumentosProveedor;
import com.system.entity.id.DocumentosProveedorId;
import org.mapstruct.*;
import java.sql.Date;
import java.time.LocalDate;

@Mapper(componentModel = "spring", imports = {DocumentosProveedorId.class, LocalDate.class, Date.class})
public interface DocumentosProveedorMapper {

    @Mapping(target = "id", expression = "java(new DocumentosProveedorId(dto.getCodCia(), dto.getCodDocumento(), dto.getCodProveedor()))")
    @Mapping(target = "fechaEmision", expression = "java(dto.getFechaEmision() != null ? dto.getFechaEmision().toLocalDate() : null)")
    @Mapping(target = "fechaVencimiento", expression = "java(dto.getFechaVencimiento() != null ? dto.getFechaVencimiento().toLocalDate() : null)")
    @Mapping(target = "proveedor", ignore = true)
    DocumentosProveedor toEntity(DocumentosProveedorDTO dto);

    @Mapping(target = "codCia", expression = "java(entity.getId() != null ? entity.getId().getCodCia() : null)")
    @Mapping(target = "codDocumento", expression = "java(entity.getId() != null ? entity.getId().getCodDocumento() : null)")
    @Mapping(target = "codProveedor", expression = "java(entity.getId() != null ? entity.getId().getCodProveedor() : null)")
    @Mapping(target = "fechaEmision", expression = "java(entity.getFechaEmision() != null ? Date.valueOf(entity.getFechaEmision()) : null)")
    @Mapping(target = "fechaVencimiento", expression = "java(entity.getFechaVencimiento() != null ? Date.valueOf(entity.getFechaVencimiento()) : null)")
    DocumentosProveedorDTO toDTO(DocumentosProveedor entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", expression = "java(new DocumentosProveedorId(dto.getCodCia(), dto.getCodDocumento(), dto.getCodProveedor()))")
    @Mapping(target = "fechaEmision", expression = "java(dto.getFechaEmision() != null ? dto.getFechaEmision().toLocalDate() : null)")
    @Mapping(target = "fechaVencimiento", expression = "java(dto.getFechaVencimiento() != null ? dto.getFechaVencimiento().toLocalDate() : null)")
    @Mapping(target = "proveedor", ignore = true)
    void updateEntityFromDTO(DocumentosProveedorDTO dto, @MappingTarget DocumentosProveedor entity);
}
