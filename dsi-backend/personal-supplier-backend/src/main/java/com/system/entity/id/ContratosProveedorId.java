package com.system.entity.id;

import lombok.*;
import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ContratosProveedorId implements Serializable {
    private Long codCia;
    private Long codContrato;
    private Long codProveedor;
    private Long codPyto;
}
