package com.system.entity.id;

import lombok.*;
import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class TareasPersonalId implements Serializable {
    private Long codCia;
    private Long codTarea;
    private Long codPyto;
    private Long codEmpleado;
}
