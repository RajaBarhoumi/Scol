package com.raja.scol.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raja.scol.entities.Absence;
import com.raja.scol.entities.Etudiant;
import com.raja.scol.repositories.AbsenceRepository;
import com.raja.scol.repositories.EtudiantRepository;
import com.raja.scol.repositories.MatiereRepository;

@RestController
@RequestMapping("/api/scol")
public class AbsenceController {

	private final AbsenceRepository attendanceRepository;
    private final EtudiantRepository studentRepository;
    private final MatiereRepository subjectRepository;

    public AbsenceController(AbsenceRepository attendanceRepository, EtudiantRepository studentRepository, MatiereRepository subjectRepository) {
        this.attendanceRepository = attendanceRepository;
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
    }

    @PostMapping("/attendances/create")
    public Absence createAttendance(@RequestBody Absence newAttendance) {
        return attendanceRepository.save(newAttendance);
    }

    @GetMapping("/attendances/{studentId}")
    public List<Absence> getAttendanceByStudentId(@PathVariable("studentId") String studentId) {
    	Etudiant existingStudent = studentRepository.findById(studentId).get();
        return attendanceRepository.findByStudent(existingStudent);
    }

    @GetMapping("/attendances/{studentId}/total")
    public Map<String, Double> getTotalHoursBySubjectForStudent(@PathVariable("studentId") String studentId) {
    	Etudiant existingStudent = studentRepository.findById(studentId).orElse(null);
        Map<String, Double> totalHoursBySubject = new HashMap<>();

        if (existingStudent != null) {
            List<Absence> attendances = attendanceRepository.findByStudent(existingStudent);

            for (Absence attendance : attendances) {
                String subjectName = attendance.getSubject().getName();
                Double totalHours = totalHoursBySubject.getOrDefault(subjectName, 0.0);
                totalHours += attendance.getNumberOfHours();
                totalHoursBySubject.put(subjectName, totalHours);
            }
        }

        return totalHoursBySubject;
    }


    @GetMapping("/attendances/{studentId}/heuretotale")
    public Double getTotalHoursByStudentId(@PathVariable("studentId") String studentId) {
        System.out.println("studentId: " + studentId);
        Etudiant existingStudent = studentRepository.findById(studentId).get();
        List<Absence> attendances = attendanceRepository.findByStudent(existingStudent);
        Double totalHours = 0.0;
        for (Absence attendance : attendances) {
            totalHours += attendance.getNumberOfHours();
        }
        return totalHours;
    }
}
