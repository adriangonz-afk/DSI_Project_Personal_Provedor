package com.system.entity.id;

import lombok.*;
import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PersonalProyectosId implements Serializable {
    private Long codCia;
    private Long codPersonalProyecto;
    private Long codEmpleado;
}
