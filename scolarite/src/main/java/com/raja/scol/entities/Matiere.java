package com.raja.scol.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Matiere {
    @Id
    @UuidGenerator
    private String id;
    private String name;
    private String description;
    private Double coefficient;
    private int numberOfHours;
}

