package com.kiit_sap_app.api.service;

import com.kiit_sap_app.api.entity.LeaveApplication;
import com.kiit_sap_app.api.repository.LeaveApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LeaveApplicationService {

    @Autowired
    private LeaveApplicationRepository leaveApplicationRepository;

    public LeaveApplication submitLeaveApplication(LeaveApplication leaveApplication) {
        return leaveApplicationRepository.save(leaveApplication);
    }

    public List<LeaveApplication> getLeaveApplicationsByRollNo(String rollNo) {
        return leaveApplicationRepository.findByRollNo(rollNo);
    }

    public List<LeaveApplication> getAllLeaveApplications() {
        return leaveApplicationRepository.findAll();
    }

    public Optional<LeaveApplication> updateLeaveGrantedStatus(String id, boolean status) {
        Optional<LeaveApplication> leaveApplication = leaveApplicationRepository.findById(id);
        leaveApplication.ifPresent(la -> {
            la.setLeaveGranted(status);
            leaveApplicationRepository.save(la);
        });
        return leaveApplication;
    }
}
