package com.raja.scol.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raja.scol.entities.Department;
import com.raja.scol.entities.Class;
import com.raja.scol.repositories.DepartmentRepository;


@RestController
@RequestMapping("/api/scol")
public class DepartmentController {
	
	private final DepartmentRepository departmentRepository;

    public DepartmentController(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @GetMapping("/departments")
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @GetMapping("/departments/{uuid}")
    public Department getDepartmentById(@PathVariable("uuid") String uuid) {
        if (departmentRepository.findById(uuid).isPresent()) {
            return departmentRepository.findById(uuid).get();
        } else {
            return null;
        }
    }

    @PostMapping("/departments")
    public Department createDepartment(@RequestBody Department newDepartment) {
        return departmentRepository.save(newDepartment);
    }

    @PutMapping("/departments/{uuid}")
    public Department updateDepartment(@PathVariable("uuid") String uuid, @RequestBody Department newDepartment) {
        if (departmentRepository.findById(uuid).isPresent()) {
            Department existingDepartment = departmentRepository.findById(uuid).get();
            existingDepartment.setName(newDepartment.getName());
            existingDepartment.setDescription(newDepartment.getDescription());

            return departmentRepository.save(existingDepartment);
        } else {
            return null;
        }
    }

    @DeleteMapping("/departments/{uuid}")
    public String deleteDepartment(@PathVariable("uuid") String uuid) {
        if (departmentRepository.findById(uuid).isPresent()) {
            departmentRepository.deleteById(uuid);
            return "Department deleted successfully";
        } else {
            return null;
        }
    }

    @GetMapping("/departments/{uuid}/classes")
    public List<Class> getClassesByDepartmentId(@PathVariable("uuid") String uuid) {
        if (departmentRepository.findById(uuid).isPresent()) {
            Department department = departmentRepository.findById(uuid).get();
            return department.getClasses();
        } else {
            return null;
        }
    }

}
