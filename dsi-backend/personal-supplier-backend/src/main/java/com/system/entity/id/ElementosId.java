package com.system.entity.id;
import lombok.*;
import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ElementosId implements Serializable {
    private String codTab;
    private String codElem;
}
