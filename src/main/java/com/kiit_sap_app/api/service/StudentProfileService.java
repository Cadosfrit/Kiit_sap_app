package com.kiit_sap_app.api.service;

import com.kiit_sap_app.api.entity.StudentProfile;
import com.kiit_sap_app.api.repository.StudentProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentProfileService {

    @Autowired
    private StudentProfileRepository studentProfileRepository;

    /**
     * Get a student profile by Roll Number
     *
     * @param rollNo - Roll Number of the student
     * @return StudentProfile if found
     */
    public StudentProfile getProfileById(String rollNo) {
        return studentProfileRepository.findById(rollNo)
                .orElseThrow(() -> new IllegalArgumentException("Student profile not found for roll number: " + rollNo));
    }

    /**
     * Create or update a student profile
     *
     * @param studentProfile - StudentProfile object
     * @return Saved StudentProfile
     */
    public StudentProfile saveOrUpdateProfile(StudentProfile studentProfile) {
        return studentProfileRepository.save(studentProfile);
    }
}
