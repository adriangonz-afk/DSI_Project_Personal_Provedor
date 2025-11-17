package com.system.mapper;


import com.system.dto.filters.PersonalFiltradoDTO;
import com.system.entity.Empleado;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring", uses = {PersonaMapper.class})
public interface PersonalFilterMapper {
    
    @Mapping(target = "codCia", source = "id.codCia")
    @Mapping(target = "codEmpleado", source = "id.codEmpleado")
    @Mapping(target = "direcc", source = "direcc")
    @Mapping(target = "celular", source = "celular")
    @Mapping(target = "hobby", source = "hobby")
    @Mapping(target = "foto", source = "foto")
    @Mapping(target = "fecNac", source = "fecNac")
    @Mapping(target = "dni", source = "dni")
    @Mapping(target = "nroCIP", source = "nroCIP")
    @Mapping(target = "fecCIPVig", source = "fecCIPVig")
    @Mapping(target = "licCond", source = "licCond")
    @Mapping(target = "flgEmplIEA", source = "flgEmplIEA")
    @Mapping(target = "observac", source = "observac")
    @Mapping(target = "codCargo", source = "codCargo")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "vigente", source = "vigente")
    @Mapping(target = "persona", source = "persona")
    @Mapping(target = "proyectosActuales", ignore = true)
    @Mapping(target = "areasActuales", ignore = true)
    @Mapping(target = "cargosActuales", ignore = true)
    @Mapping(target = "universidades", ignore = true)
    @Mapping(target = "carreras", ignore = true)
    @Mapping(target = "especialidadesCapacitacion", ignore = true)
    @Mapping(target = "especialidadesExperiencia", ignore = true)
    PersonalFiltradoDTO toPersonalFiltradoDTO(Empleado empleado);
    
    List<PersonalFiltradoDTO> toPersonalFiltradoDTOList(List<Empleado> empleados);
}