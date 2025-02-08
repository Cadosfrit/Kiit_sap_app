package com.kiit_sap_app.api.service;

import com.kiit_sap_app.api.entity.Grievance;
import com.kiit_sap_app.api.repository.GrievanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class GrievanceService {

    @Autowired
    private GrievanceRepository grievanceRepository;

    @Autowired
    private JavaMailSender mailSender;

    // Submit a grievance (Student)
    public Grievance submitGrievance(Grievance grievance) {
        grievance.setCreatedDate(new Date());
        grievance.setResolveStatus(false); // Default resolve status

        // Save to database
        Grievance savedGrievance = grievanceRepository.save(grievance);

        // Send confirmation emails
        sendGrievanceConfirmation(grievance);

        return savedGrievance;
    }

    // Fetch all grievances for a specific student (Student)
    public List<Grievance> getStudentGrievances(String rollNo) {
        return grievanceRepository.findByRollNo(rollNo);
    }

    // Fetch all grievances (Admin)
    public List<Grievance> getAllGrievances() {
        return grievanceRepository.findAll();
    }

    // Fetch grievances by student roll number (Admin)
    public List<Grievance> getGrievancesByRollNo(String rollNo) {
        return grievanceRepository.findByRollNo(rollNo);
    }

    // Update grievance status (Admin)
    public boolean updateGrievanceStatus(String serialNo, boolean resolved) {
        Optional<Grievance> grievanceOpt = grievanceRepository.findById(serialNo);
        if (grievanceOpt.isPresent()) {
            Grievance grievance = grievanceOpt.get();
            grievance.setResolveStatus(resolved);
            grievanceRepository.save(grievance);
            return true;
        }
        return false;
    }

    // Send confirmation emails
    private void sendGrievanceConfirmation(Grievance grievance) {
        String studentEmail = grievance.getEmail();
        String generatedEmail = grievance.getRollNo() + "@kiit.ac.in";

        // Send confirmation to provided email
        sendEmail(studentEmail, grievance);

        // If the provided email is different from the generated roll number email, send a copy to that too
        if (!studentEmail.equalsIgnoreCase(generatedEmail)) {
            sendEmail(generatedEmail, grievance);
        }
    }

    private void sendEmail(String recipient, Grievance grievance) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(recipient);
        mailMessage.setSubject("Grievance Submission Confirmation");
        mailMessage.setText("Your grievance has been submitted:\n\n" +
                "Serial No: " + grievance.getId() + "\n" +
                "Description: " + grievance.getGrievanceDescription() + "\n" +
                "Status: Pending");

        mailSender.send(mailMessage);
    }
}
