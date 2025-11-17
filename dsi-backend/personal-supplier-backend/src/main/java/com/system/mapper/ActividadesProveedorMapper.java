package com.system.mapper;

import com.system.dto.ActividadesProveedorDTO;
import com.system.entity.ActividadesProveedor;
import com.system.entity.id.ActividadesProveedorId;
import org.mapstruct.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

@Mapper(componentModel = "spring", imports = {ActividadesProveedorId.class, BigDecimal.class, LocalDate.class, Date.class})
public interface ActividadesProveedorMapper {
    // DTO -> Entidad
    @Mapping(target = "id", expression = "java(new ActividadesProveedorId(dto.getCodCIA(), dto.getCodActividad(), dto.getCodProveedor(), dto.getCodPyto(), dto.getCodContrato()))")
    @Mapping(target = "descripcion", source = "descripcion")
    @Mapping(target = "fechaActividad", expression = "java(dto.getFechaActividad() != null ? dto.getFechaActividad().toLocalDate() : null)")
    @Mapping(target = "monto", expression = "java(dto.getMonto() != null ? BigDecimal.valueOf(dto.getMonto()) : null)")
    @Mapping(target = "estado", source = "estado")
    @Mapping(target = "documento", source = "documento")
    @Mapping(target = "observaciones", source = "observaciones")
    @Mapping(target = "proveedor", ignore = true)
    @Mapping(target = "proyecto", ignore = true)
    @Mapping(target = "contratosProveedor", ignore = true)
    ActividadesProveedor toEntity(ActividadesProveedorDTO dto);

    // Entidad -> DTO
    @Mapping(target = "codCIA", expression = "java(entity.getId() != null ? entity.getId().getCodCia() : null)")
    @Mapping(target = "codActividad", expression = "java(entity.getId() != null ? entity.getId().getCodActividad() : null)")
    @Mapping(target = "codProveedor", expression = "java(entity.getId() != null ? entity.getId().getCodProveedor() : null)")
    @Mapping(target = "codPyto", expression = "java(entity.getId() != null ? entity.getId().getCodPyto() : null)")
    @Mapping(target = "codContrato", expression = "java(entity.getId() != null ? entity.getId().getCodContrato() : null)")
    @Mapping(target = "descripcion", source = "descripcion")
    @Mapping(target = "fechaActividad", expression = "java(entity.getFechaActividad() != null ? java.sql.Date.valueOf(entity.getFechaActividad()) : null)")
    @Mapping(target = "monto", expression = "java(entity.getMonto() != null ? entity.getMonto().doubleValue() : null)")
    @Mapping(target = "estado", source = "estado")
    @Mapping(target = "documento", source = "documento")
    @Mapping(target = "observaciones", source = "observaciones")
    ActividadesProveedorDTO toDTO(ActividadesProveedor entity);

    // Actualización parcial de entidad desde DTO
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", expression = "java(new ActividadesProveedorId(dto.getCodCIA(), dto.getCodActividad(), dto.getCodProveedor(), dto.getCodPyto(), dto.getCodContrato()))")
    @Mapping(target = "descripcion", source = "descripcion")
    @Mapping(target = "fechaActividad", expression = "java(dto.getFechaActividad() != null ? dto.getFechaActividad().toLocalDate() : null)")
    @Mapping(target = "monto", expression = "java(dto.getMonto() != null ? BigDecimal.valueOf(dto.getMonto()) : null)")
    @Mapping(target = "estado", source = "estado")
    @Mapping(target = "documento", source = "documento")
    @Mapping(target = "observaciones", source = "observaciones")
    @Mapping(target = "proveedor", ignore = true)
    @Mapping(target = "proyecto", ignore = true)
    @Mapping(target = "contratosProveedor", ignore = true)
    void updateEntityFromDTO(ActividadesProveedorDTO dto, @MappingTarget ActividadesProveedor entity);
}
