package com.system.entity.id;

import lombok.*;
import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class DocumentosProveedorId implements Serializable {
    private Long codCia;
    private Long codDocumento;
    private Long codProveedor;
}
