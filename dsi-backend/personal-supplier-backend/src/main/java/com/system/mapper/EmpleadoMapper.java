package com.system.mapper;

import com.system.dto.EmpleadoDTO;
import com.system.dto.EmpleadoCompletoDTO;
import com.system.entity.Empleado;
import com.system.entity.id.EmpleadoId;
import org.mapstruct.*;
import java.sql.Date;
import java.time.LocalDate;

@Mapper(componentModel = "spring", imports = {EmpleadoId.class, LocalDate.class, Date.class}, uses = {PersonaMapper.class})
public interface EmpleadoMapper {
    // DTO -> Entidad
    @Mapping(target = "id", expression = "java(new EmpleadoId(dto.getCodCia(), dto.getCodEmpleado()))")
    @Mapping(target = "direcc", source = "direcc")
    @Mapping(target = "celular", source = "celular")
    @Mapping(target = "hobby", source = "hobby")
    @Mapping(target = "foto", source = "foto")
    @Mapping(target = "fecNac", expression = "java(dto.getFecNac() != null ? dto.getFecNac().toLocalDate() : null)")
    @Mapping(target = "dni", source = "dni")
    @Mapping(target = "nroCIP", source = "nroCIP")
    @Mapping(target = "fecCIPVig", expression = "java(dto.getFecCIPVig() != null ? dto.getFecCIPVig().toLocalDate() : null)")
    @Mapping(target = "licCond", source = "licCond")
    @Mapping(target = "flgEmplIEA", source = "flgEmplIEA")
    @Mapping(target = "observac", source = "observac")
    @Mapping(target = "codCargo", source = "codCargo")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "vigente", source = "vigente")
    @Mapping(target = "persona", ignore = true) // Relación, no está en DTO
    Empleado toEntity(EmpleadoDTO dto);

    // Entidad -> DTO
    @Mapping(target = "codCia", expression = "java(entity.getId() != null ? entity.getId().getCodCia() : null)")
    @Mapping(target = "codEmpleado", expression = "java(entity.getId() != null ? entity.getId().getCodEmpleado() : null)")
    @Mapping(target = "direcc", source = "direcc")
    @Mapping(target = "celular", source = "celular")
    @Mapping(target = "hobby", source = "hobby")
    @Mapping(target = "foto", source = "foto")
    @Mapping(target = "fecNac", expression = "java(entity.getFecNac() != null ? java.sql.Date.valueOf(entity.getFecNac()) : null)")
    @Mapping(target = "dni", source = "dni")
    @Mapping(target = "nroCIP", source = "nroCIP")
    @Mapping(target = "fecCIPVig", expression = "java(entity.getFecCIPVig() != null ? java.sql.Date.valueOf(entity.getFecCIPVig()) : null)")
    @Mapping(target = "licCond", source = "licCond")
    @Mapping(target = "flgEmplIEA", source = "flgEmplIEA")
    @Mapping(target = "observac", source = "observac")
    @Mapping(target = "codCargo", source = "codCargo")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "vigente", source = "vigente")
    EmpleadoDTO toDTO(Empleado entity);

    // Actualización parcial de entidad desde DTO
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", expression = "java(new EmpleadoId(dto.getCodCia(), dto.getCodEmpleado()))")
    @Mapping(target = "direcc", source = "direcc")
    @Mapping(target = "celular", source = "celular")
    @Mapping(target = "hobby", source = "hobby")
    @Mapping(target = "foto", source = "foto")
    @Mapping(target = "fecNac", expression = "java(dto.getFecNac() != null ? dto.getFecNac().toLocalDate() : null)")
    @Mapping(target = "dni", source = "dni")
    @Mapping(target = "nroCIP", source = "nroCIP")
    @Mapping(target = "fecCIPVig", expression = "java(dto.getFecCIPVig() != null ? dto.getFecCIPVig().toLocalDate() : null)")
    @Mapping(target = "licCond", source = "licCond")
    @Mapping(target = "flgEmplIEA", source = "flgEmplIEA")
    @Mapping(target = "observac", source = "observac")
    @Mapping(target = "codCargo", source = "codCargo")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "vigente", source = "vigente")
    @Mapping(target = "persona", ignore = true)
    void updateEntityFromDTO(EmpleadoDTO dto, @MappingTarget Empleado entity);

    // Entidad -> DTO Completo (sin las listas, se agregan en el service)
    @Mapping(target = "codCia", expression = "java(entity.getId() != null ? entity.getId().getCodCia() : null)")
    @Mapping(target = "codEmpleado", expression = "java(entity.getId() != null ? entity.getId().getCodEmpleado() : null)")
    @Mapping(target = "direcc", source = "direcc")
    @Mapping(target = "celular", source = "celular")
    @Mapping(target = "hobby", source = "hobby")
    @Mapping(target = "foto", source = "foto")
    @Mapping(target = "fecNac", expression = "java(entity.getFecNac() != null ? java.sql.Date.valueOf(entity.getFecNac()) : null)")
    @Mapping(target = "dni", source = "dni")
    @Mapping(target = "nroCIP", source = "nroCIP")
    @Mapping(target = "fecCIPVig", expression = "java(entity.getFecCIPVig() != null ? java.sql.Date.valueOf(entity.getFecCIPVig()) : null)")
    @Mapping(target = "licCond", source = "licCond")
    @Mapping(target = "flgEmplIEA", source = "flgEmplIEA")
    @Mapping(target = "observac", source = "observac")
    @Mapping(target = "codCargo", source = "codCargo")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "vigente", source = "vigente")
    @Mapping(target = "persona", source = "persona") // Incluimos la persona en el DTO completo
    @Mapping(target = "comprobantePagos", ignore = true) // Se asigna en el service
    @Mapping(target = "especialidades", ignore = true) // Se asigna en el service
    @Mapping(target = "evaluacionesDesempeno", ignore = true) // Se asigna en el service
    @Mapping(target = "experienciasLaborales", ignore = true) // Se asigna en el service
    @Mapping(target = "gradosAcademicos", ignore = true) // Se asigna en el service
    @Mapping(target = "proyectos", ignore = true) // Se asigna en el service
    @Mapping(target = "tareas", ignore = true) // Se asigna en el service
    EmpleadoCompletoDTO toCompletoDTO(Empleado entity);
}
