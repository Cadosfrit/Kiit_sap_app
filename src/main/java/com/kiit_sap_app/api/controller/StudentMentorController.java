package com.kiit_sap_app.api.controller;

import com.kiit_sap_app.api.entity.StudentMentorMap;
import com.kiit_sap_app.api.entity.MentorStudentMap;
import com.kiit_sap_app.api.service.StudentMentorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student-mentor")
public class StudentMentorController {

    @Autowired
    private StudentMentorService studentMentorService;


    // POST: Assign a mentor to a student or update mentor for existing student
    @PostMapping("/assign")
    public ResponseEntity<String> assignMentorToStudent(@RequestBody StudentMentorMap studentMentorMap) {
        studentMentorService.assignMentor(studentMentorMap);
        return ResponseEntity.ok("Mentor assigned successfully.");
    }

    // GET: Get mentor ID by student ID
    @GetMapping("/mentor/{studentId}")
    public ResponseEntity<String> getMentorByStudentId(@PathVariable String studentId) {
        String mentorId = studentMentorService.getMentorIdByStudentId(studentId);
        if (mentorId != null) {
            return ResponseEntity.ok(mentorId);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // GET: Get list of students by mentor ID
    @GetMapping("/students/{mentorId}")
    public ResponseEntity<List<String>> getStudentsByMentorId(@PathVariable String mentorId) {
        List<String> students = studentMentorService.getStudentsByMentorId(mentorId);
        return ResponseEntity.ok(students);
    }

    // DELETE: Remove student from mentor and student-mentor mapping
    @DeleteMapping("/remove/{studentId}")
    public ResponseEntity<String> removeStudent(@PathVariable String studentId) {
        studentMentorService.removeStudentFromMentor(studentId);
        return ResponseEntity.ok("Student removed successfully.");
    }

    @PostMapping("/add")
    public ResponseEntity<String> addMentor(@RequestBody MentorStudentMap mentorStudentMap) {
        boolean isAdded = studentMentorService.addMentor(mentorStudentMap);
        if (isAdded) {
            return ResponseEntity.ok("Mentor added successfully with an empty student list.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to add mentor.");
        }
    }
}
