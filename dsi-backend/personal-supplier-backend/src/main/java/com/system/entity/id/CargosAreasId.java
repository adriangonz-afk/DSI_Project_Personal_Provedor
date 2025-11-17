package com.system.entity.id;

import lombok.*;
import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CargosAreasId implements Serializable {
    private Long codCargo;
    private Long codArea;
    private Long codCia;
}
