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

import com.raja.scol.entities.Etudiant;
import com.raja.scol.repositories.EtudiantRepository;

@RestController
@RequestMapping("/api/scol")
public class EtudiantController {
	private final EtudiantRepository studentRepository;

    public EtudiantController(EtudiantRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/students")
    public List<Etudiant> getAllStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/students/{uuid}")
    public Etudiant getStudentById(@PathVariable("uuid") String uuid) {
        if (studentRepository.findById(uuid).isPresent()) {
            return studentRepository.findById(uuid).get();
        } else {
            return null;
        }
    }

    @PostMapping(value = "/students/create", consumes = "application/json", produces = "application/json")
    public Etudiant createStudent(@RequestBody Etudiant newStudent) {
    	Etudiant student = Etudiant.builder()
                .firstName(newStudent.getFirstName())
                .lastName(newStudent.getLastName())
                .dateOfBirth(newStudent.getDateOfBirth())
                .classRoom(newStudent.getClassRoom())
                .build();
        return studentRepository.save(student);
    }

    @PutMapping("/students/{uuid}")
    public Etudiant updateStudent(@PathVariable("uuid") String uuid, @RequestBody Etudiant newStudent) {
        if (studentRepository.findById(uuid).isPresent()) {
        	Etudiant existingStudent = studentRepository.findById(uuid).get();
            existingStudent.setFirstName(newStudent.getFirstName());
            existingStudent.setLastName(newStudent.getLastName());
            existingStudent.setDateOfBirth(newStudent.getDateOfBirth());
            existingStudent.setClassRoom(newStudent.getClassRoom());
            return studentRepository.save(existingStudent);
        } else {
            return null;
        }
    }

    @DeleteMapping("/students/{uuid}")
    public String deleteStudent(@PathVariable("uuid") String uuid) {
        if (studentRepository.findById(uuid).isPresent()) {
            studentRepository.deleteById(uuid);
            return "Student deleted successfully";
        } else {
            return null;
        }
    }
}
