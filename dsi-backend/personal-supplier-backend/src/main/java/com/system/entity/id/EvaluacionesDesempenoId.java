package com.system.entity.id;

import lombok.*;
import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class EvaluacionesDesempenoId implements Serializable {
    private Long codCia;
    private Long codEvaluacion;
    private Long codEmpleado;
    private Long codPyto;
}
