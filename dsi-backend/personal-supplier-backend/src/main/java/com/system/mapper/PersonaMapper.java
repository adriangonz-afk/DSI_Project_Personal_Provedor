package com.system.mapper;

import com.system.dto.PersonaDTO;
import com.system.entity.Persona;
import com.system.entity.id.PersonaId;
import org.mapstruct.*;

@Mapper(componentModel = "spring", imports = {PersonaId.class})
public interface PersonaMapper {
    // DTO -> Entidad
    @Mapping(target = "id", expression = "java(new PersonaId(dto.getCodCia(), dto.getCodPersona()))")
    @Mapping(target = "tipPersona", source = "tipPersona")
    @Mapping(target = "desPersona", source = "desPersona")
    @Mapping(target = "desCorta", source = "desCorta")
    @Mapping(target = "descAlterna", source = "descAlterna")
    @Mapping(target = "desCortaAlt", source = "desCortaAlt")
    @Mapping(target = "vigente", source = "vigente")
    @Mapping(target = "cia", ignore = true)
    Persona toEntity(PersonaDTO dto);

    // Entidad -> DTO
    @Mapping(target = "codCia", expression = "java(entity.getId() != null ? entity.getId().getCodCia() : null)")
    @Mapping(target = "codPersona", expression = "java(entity.getId() != null ? entity.getId().getCodPersona() : null)")
    @Mapping(target = "tipPersona", source = "tipPersona")
    @Mapping(target = "desPersona", source = "desPersona")
    @Mapping(target = "desCorta", source = "desCorta")
    @Mapping(target = "descAlterna", source = "descAlterna")
    @Mapping(target = "desCortaAlt", source = "desCortaAlt")
    @Mapping(target = "vigente", source = "vigente")
    PersonaDTO toDTO(Persona entity);

    // Actualización parcial de entidad desde DTO
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", expression = "java(new PersonaId(dto.getCodCia(), dto.getCodPersona()))")
    @Mapping(target = "tipPersona", source = "tipPersona")
    @Mapping(target = "desPersona", source = "desPersona")
    @Mapping(target = "desCorta", source = "desCorta")
    @Mapping(target = "descAlterna", source = "descAlterna")
    @Mapping(target = "desCortaAlt", source = "desCortaAlt")
    @Mapping(target = "vigente", source = "vigente")
    @Mapping(target = "cia", ignore = true)
    void updateEntityFromDTO(PersonaDTO dto, @MappingTarget Persona entity);
}
