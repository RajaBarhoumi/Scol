package com.raja.scol.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.raja.scol.entities.Class;


public interface ClassRepository extends JpaRepository<Class, String> {
	
}
