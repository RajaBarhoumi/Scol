package com.raja.scol.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Absence {
    @Id
    @UuidGenerator
    private String id;

    @ManyToOne
    private Etudiant student;

    @ManyToOne
    private Matiere subject;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    private Double numberOfHours;
}
