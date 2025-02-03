
package com.kiit_sap_app.api.controller;

import com.kiit_sap_app.api.entity.StudentProfile;
import com.kiit_sap_app.api.service.StudentProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student-profile")
public class StudentProfileController {

    @Autowired
    private StudentProfileService studentProfileService;

    @GetMapping
    public ResponseEntity<StudentProfile> getProfileById() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        StudentProfile studentProfile = studentProfileService.getProfileById(userName);
        return ResponseEntity.ok(studentProfile);
    }

    /**
     * Create or update a student profile
     *
     * @param studentProfile - StudentProfile object
     * @return ResponseEntity containing saved StudentProfile
     */

    @PostMapping("/admin/student-profile")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StudentProfile> saveOrUpdateProfile(@RequestBody StudentProfile studentProfile) {
        StudentProfile savedProfile = studentProfileService.saveOrUpdateProfile(studentProfile);
        return ResponseEntity.ok(savedProfile);
    }
}
