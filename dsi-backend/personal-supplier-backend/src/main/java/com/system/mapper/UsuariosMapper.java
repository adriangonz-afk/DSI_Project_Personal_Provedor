package com.system.mapper;

import com.system.dto.UsuariosDTO;
import com.system.entity.Usuarios;
import com.system.entity.id.UsuariosId;
import org.mapstruct.*;

@Mapper(componentModel = "spring", imports = {UsuariosId.class})
public interface UsuariosMapper {

    @Mapping(target = "codCia", expression = "java(entity.getId() != null ? entity.getId().getCodCia() : null)")
    @Mapping(target = "codUsuario", expression = "java(entity.getId() != null ? entity.getId().getCodUsuario() : null)")
    UsuariosDTO toDTO(Usuarios entity);

    @Mapping(target = "id", expression = "java(new UsuariosId(dto.getCodCia(), dto.getCodUsuario()))")
    @Mapping(target = "cia", ignore = true)
    @Mapping(target = "passwordHash", ignore = true)
    Usuarios toEntity(UsuariosDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", expression = "java(new UsuariosId(dto.getCodCia(), dto.getCodUsuario()))")
    @Mapping(target = "cia", ignore = true)
    @Mapping(target = "passwordHash", ignore = true)
    void updateEntityFromDTO(UsuariosDTO dto, @MappingTarget Usuarios entity);
}