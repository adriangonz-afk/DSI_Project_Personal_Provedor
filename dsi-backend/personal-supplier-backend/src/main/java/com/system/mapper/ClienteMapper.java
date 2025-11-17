package com.system.mapper;

import com.system.dto.ClienteDTO;
import com.system.entity.Cliente;
import com.system.entity.id.ClienteId;
import org.mapstruct.*;

@Mapper(componentModel = "spring", imports = {ClienteId.class})
public interface ClienteMapper {
    @Mapping(target = "codCia", expression = "java(entity.getId() != null ? entity.getId().getCodCia() : null)")
    @Mapping(target = "codCliente", expression = "java(entity.getId() != null ? entity.getId().getCodCliente() : null)")
    @Mapping(target = "nroRuc", source = "nroRuc")
    @Mapping(target = "vigente", source = "vigente")
    ClienteDTO toDTO(Cliente entity);

    @Mapping(target = "id", expression = "java(new ClienteId(dto.getCodCia(), dto.getCodCliente()))")
    @Mapping(target = "nroRuc", source = "nroRuc")
    @Mapping(target = "vigente", source = "vigente")
    @Mapping(target = "persona", ignore = true)
    Cliente toEntity(ClienteDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", expression = "java(new ClienteId(dto.getCodCia(), dto.getCodCliente()))")
    @Mapping(target = "nroRuc", source = "nroRuc")
    @Mapping(target = "vigente", source = "vigente")
    @Mapping(target = "persona", ignore = true)
    void updateEntityFromDTO(ClienteDTO dto, @MappingTarget Cliente entity);
}
