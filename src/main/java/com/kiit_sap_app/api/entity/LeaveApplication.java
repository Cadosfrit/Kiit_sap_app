package com.kiit_sap_app.api.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@NoArgsConstructor
@Document(collection = "leave_applications")
public class LeaveApplication {

    @Id
    private String id; // Unique ID for the leave application

    private String rollNo; // Roll Number from AuthenticationManager
    private Date fromDate;
    private Date toDate;
    private String leaveReason;
    private boolean leaveGranted = false; // Default: false
}

