package com.kiit_sap_app.api.controller;

import com.kiit_sap_app.api.entity.Timetable;
import com.kiit_sap_app.api.service.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/timetable")
public class TimetableController {

    @Autowired
    private TimetableService timetableService;

    @GetMapping("/health")
    public String healthCheck() {
        return "Server is up and running!";
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Timetable>> getAllTimetables() {
        List<Timetable> timetables = timetableService.getAllTimetables();
        return ResponseEntity.ok(timetables);
    }

    // Endpoint to upload a new timetable (POST)
    @PostMapping("/upload")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Timetable> uploadTimetable(@RequestBody Timetable timetable) {
        Timetable uploadedTimetable = timetableService.uploadTimetable(timetable);
        return ResponseEntity.ok(uploadedTimetable);
    }

    // Endpoint to edit the timetable (POST)
    @PutMapping("/edit")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Timetable> editTimetable(
            @RequestParam String classID,
            @RequestParam String day,
            @RequestParam String startTime,
            @RequestParam String subjectName) {
        Timetable updatedTimetable = timetableService.editTimetable(classID, day, startTime, subjectName);
        return ResponseEntity.ok(updatedTimetable);
    }

    // Endpoint to get timetable by classID (GET)
    @GetMapping("/class/{classID}")
    public ResponseEntity<Timetable> getTimetableByClassID(@PathVariable String classID) {
        Timetable timetable = timetableService.getTimetableByClassID(classID);
        return ResponseEntity.ok(timetable);
    }


    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Timetable> deleteSub(
            @RequestParam String classID,
            @RequestParam String day,
            @RequestParam String startTime) {
        Timetable updatedTimetable = timetableService.deleteSubject(classID, day, startTime);
        return ResponseEntity.ok(updatedTimetable);
    }
}
