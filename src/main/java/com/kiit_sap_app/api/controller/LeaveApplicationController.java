package com.kiit_sap_app.api.controller;

import com.kiit_sap_app.api.entity.LeaveApplication;
import com.kiit_sap_app.api.service.LeaveApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/leave-applications")
public class LeaveApplicationController {

    @Autowired
    private LeaveApplicationService leaveApplicationService;

    @PostMapping
    public LeaveApplication submitLeaveApplication(@RequestBody LeaveApplication leaveApplication) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        leaveApplication.setRollNo(authentication.getName()); // Extract roll number from AuthenticationManager
        return leaveApplicationService.submitLeaveApplication(leaveApplication);
    }

    @GetMapping
    public List<LeaveApplication> getUserLeaveApplications() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return leaveApplicationService.getLeaveApplicationsByRollNo(authentication.getName());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Optional<LeaveApplication> updateLeaveGrantedStatus(@PathVariable String id, @RequestParam boolean status) {
        return leaveApplicationService.updateLeaveGrantedStatus(id, status);
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public List<LeaveApplication> getAllLeaveApplications() {
        return leaveApplicationService.getAllLeaveApplications();
    }

    @GetMapping("/admin/{rollNo}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<LeaveApplication> getLeaveApplicationsByRollNo(@PathVariable String rollNo) {
        return leaveApplicationService.getLeaveApplicationsByRollNo(rollNo);
    }
}
