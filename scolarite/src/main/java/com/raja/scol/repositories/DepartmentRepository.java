package com.raja.scol.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.raja.scol.entities.Department;

public interface DepartmentRepository extends JpaRepository<Department, String> {
}
