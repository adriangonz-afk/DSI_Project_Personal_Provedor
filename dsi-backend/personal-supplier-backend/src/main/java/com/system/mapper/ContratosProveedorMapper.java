package com.system.mapper;

import com.system.dto.ContratosProveedorDTO;
import com.system.entity.ContratosProveedor;
import com.system.entity.id.ContratosProveedorId;
import org.mapstruct.*;
import java.math.BigDecimal;
import java.sql.Date;

@Mapper(componentModel = "spring", imports = {BigDecimal.class, ContratosProveedorId.class, Date.class})
public interface ContratosProveedorMapper {
    // DTO -> Entidad
    @Mapping(target = "id", expression = "java(new ContratosProveedorId(dto.getCodCia(), dto.getCodContrato(), dto.getCodProveedor(), dto.getCodPyto()))")
    @Mapping(target = "numeroContrato", source = "numeroContrato")
    @Mapping(target = "tipoContrato", source = "tipoContrato")
    @Mapping(target = "fechaInicio", expression = "java(dto.getFechaInicio() != null ? dto.getFechaInicio().toLocalDate() : null)")
    @Mapping(target = "fechaFin", expression = "java(dto.getFechaFin() != null ? dto.getFechaFin().toLocalDate() : null)")
    @Mapping(target = "montoTotal", expression = "java(dto.getMontoTotal() != null ? BigDecimal.valueOf(dto.getMontoTotal()) : null)")
    @Mapping(target = "moneda", source = "moneda")
    @Mapping(target = "documentoContrato", source = "documentoContrato")
    @Mapping(target = "proveedor", ignore = true)
    @Mapping(target = "proyecto", ignore = true)
    ContratosProveedor toEntity(ContratosProveedorDTO dto);

    // Entidad -> DTO
    @Mapping(target = "codCia", expression = "java(entity.getId() != null ? entity.getId().getCodCia() : null)")
    @Mapping(target = "codContrato", expression = "java(entity.getId() != null ? entity.getId().getCodContrato() : null)")
    @Mapping(target = "codProveedor", expression = "java(entity.getId() != null ? entity.getId().getCodProveedor() : null)")
    @Mapping(target = "codPyto", expression = "java(entity.getId() != null ? entity.getId().getCodPyto() : null)")
    @Mapping(target = "numeroContrato", source = "numeroContrato")
    @Mapping(target = "tipoContrato", source = "tipoContrato")
    @Mapping(target = "fechaInicio", expression = "java(entity.getFechaInicio() != null ? Date.valueOf(entity.getFechaInicio()) : null)")
    @Mapping(target = "fechaFin", expression = "java(entity.getFechaFin() != null ? Date.valueOf(entity.getFechaFin()) : null)")
    @Mapping(target = "montoTotal", expression = "java(entity.getMontoTotal() != null ? entity.getMontoTotal().doubleValue() : null)")
    @Mapping(target = "moneda", source = "moneda")
    @Mapping(target = "documentoContrato", source = "documentoContrato")
    ContratosProveedorDTO toDTO(ContratosProveedor entity);

    // Actualización parcial de entidad desde DTO
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", expression = "java(new ContratosProveedorId(dto.getCodCia(), dto.getCodContrato(), dto.getCodProveedor(), dto.getCodPyto()))")
    @Mapping(target = "numeroContrato", source = "numeroContrato")
    @Mapping(target = "tipoContrato", source = "tipoContrato")
    @Mapping(target = "fechaInicio", expression = "java(dto.getFechaInicio() != null ? dto.getFechaInicio().toLocalDate() : null)")
    @Mapping(target = "fechaFin", expression = "java(dto.getFechaFin() != null ? dto.getFechaFin().toLocalDate() : null)")
    @Mapping(target = "montoTotal", expression = "java(dto.getMontoTotal() != null ? BigDecimal.valueOf(dto.getMontoTotal()) : null)")
    @Mapping(target = "moneda", source = "moneda")
    @Mapping(target = "documentoContrato", source = "documentoContrato")
    @Mapping(target = "proveedor", ignore = true)
    @Mapping(target = "proyecto", ignore = true)
    void updateEntityFromDTO(ContratosProveedorDTO dto, @MappingTarget ContratosProveedor entity);
}
