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

import com.raja.scol.repositories.ClassRepository;
import com.raja.scol.entities.Class;
import com.raja.scol.entities.Etudiant;


@RestController
@RequestMapping("/api/scol")
public class ClassController {
	
	private final ClassRepository classRepository;

    public ClassController(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    @GetMapping("/classes")
    public List<Class> getAllClasses() {
        return classRepository.findAll();
    }

    @GetMapping("/classes/{uuid}")
    public Class getClassById(@PathVariable("uuid") String uuid) {
        if (classRepository.findById(uuid).isPresent()) {
            return classRepository.findById(uuid).get();
        } else {
            return null;
        }
    }

    @PostMapping("/classes/create")
    public Class createClass(@RequestBody Class newClass) {
        return classRepository.save(newClass);
    }

    @PutMapping("/classes/{uuid}")
    public Class updateClass(@PathVariable("uuid") String uuid, @RequestBody Class newClass) {
        if (classRepository.findById(uuid).isPresent()) {
            Class existingClass = classRepository.findById(uuid).get();
            existingClass.setName(newClass.getName());
            existingClass.setDepartment(newClass.getDepartment());
            existingClass.setStudents(newClass.getStudents());
            return classRepository.save(existingClass);
        } else {
            return null;
        }
    }

    @DeleteMapping("/classes/{uuid}")
    public String deleteClass(@PathVariable("uuid") String uuid) {
        if (classRepository.findById(uuid).isPresent()) {
            classRepository.deleteById(uuid);
            return "Class deleted successfully";
        } else {
            return null;
        }
    }

    @GetMapping("/classes/{uuid}/students")
    public List<Etudiant> getStudentsByClassId(@PathVariable("uuid") String uuid) {
        if (classRepository.findById(uuid).isPresent()) {
            Class existingClass = classRepository.findById(uuid).get();
            return existingClass.getStudents();
        } else {
            return null;
        }
    }

}
