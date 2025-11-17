package com.system.mapper;

import com.system.dto.CompPagoempleadoDTO;
import com.system.entity.CompPagoempleado;
import com.system.entity.id.CompPagoempleadoId;
import org.mapstruct.*;
import java.math.BigDecimal;
import java.sql.Date;

@Mapper(componentModel = "spring", imports = {BigDecimal.class, CompPagoempleadoId.class, Date.class})
public interface CompPagoempleadoMapper {
    // DTO -> Entidad
    @Mapping(target = "id", expression = "java(new CompPagoempleadoId(dto.getCodCia(), dto.getCodEmpleado(), dto.getNroCP()))")
    @Mapping(target = "codPyto", source = "codPyto")
    @Mapping(target = "nroPago", source = "nroPago")
    @Mapping(target = "tCompPago", source = "TCompPago")
    @Mapping(target = "eCompPago", source = "ECompPago")
    @Mapping(target = "fecCP", expression = "java(dto.getFecCP() != null ? dto.getFecCP().toLocalDate() : null)")
    @Mapping(target = "tMoneda", source = "TMoneda")
    @Mapping(target = "eMoneda", source = "EMoneda")
    @Mapping(target = "tipCambio", expression = "java(dto.getTipCambio() != null ? BigDecimal.valueOf(dto.getTipCambio()) : null)")
    @Mapping(target = "impMO", expression = "java(dto.getImpMO() != null ? BigDecimal.valueOf(dto.getImpMO()) : null)")
    @Mapping(target = "impNetoMN", expression = "java(dto.getImpNetoMN() != null ? BigDecimal.valueOf(dto.getImpNetoMN()) : null)")
    @Mapping(target = "impIGVMN", expression = "java(dto.getImpIGVMN() != null ? BigDecimal.valueOf(dto.getImpIGVMN()) : null)")
    @Mapping(target = "impTotalMn", expression = "java(dto.getImpTotalMn() != null ? BigDecimal.valueOf(dto.getImpTotalMn()) : null)")
    @Mapping(target = "fotoCP", source = "fotoCP")
    @Mapping(target = "fotoAbono", source = "fotoAbono")
    @Mapping(target = "fecAbono", expression = "java(dto.getFecAbono() != null ? dto.getFecAbono().toLocalDate() : null)")
    @Mapping(target = "desAbono", source = "desAbono")
    @Mapping(target = "semilla", source = "semilla")
    @Mapping(target = "tabEstado", source = "tabEstado")
    @Mapping(target = "codEstado", source = "codEstado")
    @Mapping(target = "empleado", ignore = true)
    @Mapping(target = "proyecto", ignore = true)
    @Mapping(target = "monedaElementos", ignore = true)
    @Mapping(target = "compPagoElementos", ignore = true)
    CompPagoempleado toEntity(CompPagoempleadoDTO dto);

    // Entidad -> DTO
    @Mapping(target = "codCia", expression = "java(entity.getId() != null ? entity.getId().getCodCia() : null)")
    @Mapping(target = "codEmpleado", expression = "java(entity.getId() != null ? entity.getId().getCodEmpleado() : null)")
    @Mapping(target = "nroCP", expression = "java(entity.getId() != null ? entity.getId().getNroCP() : null)")
    @Mapping(target = "codPyto", source = "codPyto")
    @Mapping(target = "nroPago", source = "nroPago")
    @Mapping(target = "tCompPago", source = "TCompPago")
    @Mapping(target = "eCompPago", source = "ECompPago")
    @Mapping(target = "fecCP", expression = "java(entity.getFecCP() != null ? Date.valueOf(entity.getFecCP()) : null)")
    @Mapping(target = "tMoneda", source = "TMoneda")
    @Mapping(target = "eMoneda", source = "EMoneda")
    @Mapping(target = "tipCambio", expression = "java(entity.getTipCambio() != null ? entity.getTipCambio().doubleValue() : null)")
    @Mapping(target = "impMO", expression = "java(entity.getImpMO() != null ? entity.getImpMO().doubleValue() : null)")
    @Mapping(target = "impNetoMN", expression = "java(entity.getImpNetoMN() != null ? entity.getImpNetoMN().doubleValue() : null)")
    @Mapping(target = "impIGVMN", expression = "java(entity.getImpIGVMN() != null ? entity.getImpIGVMN().doubleValue() : null)")
    @Mapping(target = "impTotalMn", expression = "java(entity.getImpTotalMn() != null ? entity.getImpTotalMn().doubleValue() : null)")
    @Mapping(target = "fotoCP", source = "fotoCP")
    @Mapping(target = "fotoAbono", source = "fotoAbono")
    @Mapping(target = "fecAbono", expression = "java(entity.getFecAbono() != null ? Date.valueOf(entity.getFecAbono()) : null)")
    @Mapping(target = "desAbono", source = "desAbono")
    @Mapping(target = "semilla", source = "semilla")
    @Mapping(target = "tabEstado", source = "tabEstado")
    @Mapping(target = "codEstado", source = "codEstado")
    CompPagoempleadoDTO toDTO(CompPagoempleado entity);

    // Actualización parcial de entidad desde DTO
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", expression = "java(new CompPagoempleadoId(dto.getCodCia(), dto.getCodEmpleado(), dto.getNroCP()))")
    @Mapping(target = "codPyto", source = "codPyto")
    @Mapping(target = "nroPago", source = "nroPago")
    @Mapping(target = "TCompPago", source = "TCompPago")
    @Mapping(target = "ECompPago", source = "ECompPago")
    @Mapping(target = "fecCP", expression = "java(dto.getFecCP() != null ? dto.getFecCP().toLocalDate() : null)")
    @Mapping(target = "TMoneda", source = "TMoneda")
    @Mapping(target = "EMoneda", source = "EMoneda")
    @Mapping(target = "tipCambio", expression = "java(dto.getTipCambio() != null ? BigDecimal.valueOf(dto.getTipCambio()) : null)")
    @Mapping(target = "impMO", expression = "java(dto.getImpMO() != null ? BigDecimal.valueOf(dto.getImpMO()) : null)")
    @Mapping(target = "impNetoMN", expression = "java(dto.getImpNetoMN() != null ? BigDecimal.valueOf(dto.getImpNetoMN()) : null)")
    @Mapping(target = "impIGVMN", expression = "java(dto.getImpIGVMN() != null ? BigDecimal.valueOf(dto.getImpIGVMN()) : null)")
    @Mapping(target = "impTotalMn", expression = "java(dto.getImpTotalMn() != null ? BigDecimal.valueOf(dto.getImpTotalMn()) : null)")
    @Mapping(target = "fotoCP", source = "fotoCP")
    @Mapping(target = "fotoAbono", source = "fotoAbono")
    @Mapping(target = "fecAbono", expression = "java(dto.getFecAbono() != null ? dto.getFecAbono().toLocalDate() : null)")
    @Mapping(target = "desAbono", source = "desAbono")
    @Mapping(target = "semilla", source = "semilla")
    @Mapping(target = "tabEstado", source = "tabEstado")
    @Mapping(target = "codEstado", source = "codEstado")
    @Mapping(target = "empleado", ignore = true)
    @Mapping(target = "proyecto", ignore = true)
    @Mapping(target = "monedaElementos", ignore = true)
    @Mapping(target = "compPagoElementos", ignore = true)
    void updateEntityFromDTO(CompPagoempleadoDTO dto, @MappingTarget CompPagoempleado entity);
}
