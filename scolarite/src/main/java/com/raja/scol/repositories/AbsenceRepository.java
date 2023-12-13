package com.raja.scol.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.raja.scol.entities.Absence;
import com.raja.scol.entities.Etudiant;
import com.raja.scol.entities.Matiere;

public interface AbsenceRepository extends JpaRepository<Absence, String> {
    List<Absence> findByStudent(Etudiant student);
    List<Absence> findByStudentAndSubject(Etudiant student, Matiere subject);
}
