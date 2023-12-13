package com.raja.scol.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.raja.scol.entities.Etudiant;

public interface EtudiantRepository extends JpaRepository<Etudiant, String> {

}
