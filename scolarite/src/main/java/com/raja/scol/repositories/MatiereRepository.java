package com.raja.scol.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.raja.scol.entities.Matiere;

public interface MatiereRepository extends JpaRepository<Matiere, String> {
	
}