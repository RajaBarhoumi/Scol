package com.raja.scol.controller;

import com.raja.scol.entities.Matiere;
import com.raja.scol.repositories.MatiereRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/scol")
public class MatiereController {
	
	private final MatiereRepository subjectRepository;

    public MatiereController(MatiereRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @GetMapping("/subjects")
    public List<Matiere> getAllSubjects() {
        return subjectRepository.findAll();
    }

    @GetMapping("/subjects/{uuid}")
    public Matiere getSubjectById(@PathVariable("uuid") String uuid) {
        if (subjectRepository.findById(uuid).isPresent()) {
            return subjectRepository.findById(uuid).get();
        } else {
            return null;
        }
    }

    @PostMapping("/subjects/create")
    public Matiere createSubject(@RequestBody Matiere newSubject) {
        return subjectRepository.save(newSubject);
    }

    @PutMapping("/subjects/{uuid}")
    public Matiere updateSubject(@PathVariable("uuid") String uuid, @RequestBody Matiere newSubject) {
        if (subjectRepository.findById(uuid).isPresent()) {
        	Matiere existingSubject = subjectRepository.findById(uuid).get();
            existingSubject.setName(newSubject.getName());
            existingSubject.setDescription(newSubject.getDescription());
            return subjectRepository.save(existingSubject);
        } else {
            return null;
        }
    }

    @DeleteMapping("/subjects/{uuid}")
    public String deleteSubject(@PathVariable("uuid") String uuid) {
        if (subjectRepository.findById(uuid).isPresent()) {
            subjectRepository.deleteById(uuid);
            return "Training deleted successfully";
        } else {
            return null;
        }
    }

}
