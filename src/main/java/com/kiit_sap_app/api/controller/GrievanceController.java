package com.kiit_sap_app.api.controller;

import com.kiit_sap_app.api.entity.Grievance;
import com.kiit_sap_app.api.service.GrievanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grievances")
public class GrievanceController {

    @Autowired
    private GrievanceService grievanceService;

    // Submit a grievance (Student)
    @PostMapping
    public Grievance submitGrievance(@RequestBody Grievance grievance) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String rollNo = authentication.getName(); // Extract roll number from authenticated user
        grievance.setRollNo(rollNo);
        return grievanceService.submitGrievance(grievance);
    }

    // Fetch grievances of the logged-in student
    @GetMapping
    public List<Grievance> getStudentGrievances() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String rollNo = authentication.getName();
        return grievanceService.getStudentGrievances(rollNo);
    }

    // Fetch all grievances (Admin only)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public List<Grievance> getAllGrievances() {
        return grievanceService.getAllGrievances();
    }

    // Fetch grievances by roll number (Admin only)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{rollNo}")
    public List<Grievance> getGrievancesByRollNo(@PathVariable String rollNo) {
        return grievanceService.getGrievancesByRollNo(rollNo);
    }

    // Update grievance status (Admin only)
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{serialNo}")
    public boolean updateGrievanceStatus(@PathVariable String serialNo, @RequestParam boolean resolved) {
        return grievanceService.updateGrievanceStatus(serialNo, resolved);
    }
}
