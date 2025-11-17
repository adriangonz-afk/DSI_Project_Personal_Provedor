package com.system.mapper;

import com.system.dto.ProveedorDTO;
import com.system.dto.ProveedorCompletoDTO;
import com.system.entity.Proveedor;
import com.system.entity.id.ProveedorId;
import org.mapstruct.*;

@Mapper(componentModel = "spring", 
        imports = {ProveedorId.class},
        uses = {PersonaMapper.class, ContratosProveedorMapper.class, 
                DocumentosProveedorMapper.class, ActividadesProveedorMapper.class,
                CompPagocabMapper.class, ServiciosProveedorMapper.class})
public interface ProveedorMapper {
    // DTO -> Entidad
    @Mapping(target = "id", expression = "java(new ProveedorId(dto.getCodCia(), dto.getCodProveedor()))")
    @Mapping(target = "nroRuc", source = "nroRuc")
    @Mapping(target = "vigente", source = "vigente")
    @Mapping(target = "persona", ignore = true)
    Proveedor toEntity(ProveedorDTO dto);

    // Entidad -> DTO
    @Mapping(target = "codCia", expression = "java(entity.getId() != null ? entity.getId().getCodCia() : null)")
    @Mapping(target = "codProveedor", expression = "java(entity.getId() != null ? entity.getId().getCodProveedor() : null)")
    @Mapping(target = "nroRuc", source = "nroRuc")
    @Mapping(target = "vigente", source = "vigente")
    ProveedorDTO toDTO(Proveedor entity);

    // Actualización parcial de entidad desde DTO
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", expression = "java(new ProveedorId(dto.getCodCia(), dto.getCodProveedor()))")
    @Mapping(target = "nroRuc", source = "nroRuc")
    @Mapping(target = "vigente", source = "vigente")
    @Mapping(target = "persona", ignore = true)
    void updateEntityFromDTO(ProveedorDTO dto, @MappingTarget Proveedor entity);

    // Entidad -> DTO Completo (mapeo manual para incluir las listas)
    @Mapping(target = "codCia", expression = "java(entity.getId() != null ? entity.getId().getCodCia() : null)")
    @Mapping(target = "codProveedor", expression = "java(entity.getId() != null ? entity.getId().getCodProveedor() : null)")
    @Mapping(target = "nroRuc", source = "nroRuc")
    @Mapping(target = "vigente", source = "vigente")
    @Mapping(target = "persona", source = "persona")
    @Mapping(target = "contratos", ignore = true)
    @Mapping(target = "documentos", ignore = true)
    @Mapping(target = "actividades", ignore = true)
    @Mapping(target = "comprobantePagos", ignore = true)
    @Mapping(target = "servicios", ignore = true)
    ProveedorCompletoDTO toCompletoDTO(Proveedor entity);
}
