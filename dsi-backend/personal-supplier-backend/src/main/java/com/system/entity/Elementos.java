package com.system.entity;
import com.system.entity.id.ElementosId;
import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "ELEMENTOS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Elementos {
    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "codTab", column = @Column(name = "CODTAB")),
        @AttributeOverride(name = "codElem", column = @Column(name = "CODELEM"))
    })
    private ElementosId id;

    @Column(name = "DENELE", nullable = false, length = 50)
    private String denEle;

    @Column(name = "DENCORTA", nullable = false, length = 10)
    private String denCorta;

    @Column(name = "VIGENTE", nullable = false, length = 1)
    private String vigente;

    @MapsId("codTab")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CODTAB", referencedColumnName = "CODTAB", insertable = false, updatable = false)
    private Tabs tabs;
}
