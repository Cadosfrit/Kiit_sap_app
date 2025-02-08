package com.kiit_sap_app.api.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@NoArgsConstructor
@Document(collection = "grievances")
public class Grievance {

    @Id
    private String id;  // Auto-generated unique ID

    private String rollNo;  // Extracted from authentication
    private String email;   // Provided email
    private String grievanceDescription;
    private Date createdDate;
    private boolean resolveStatus = false; // Default to false

    public Grievance(String rollNo, String email, String grievanceDescription) {
        this.rollNo = rollNo;
        this.email = email;
        this.grievanceDescription = grievanceDescription;
        this.createdDate = new Date(); // Set creation time
        this.resolveStatus = false;    // Default unresolved
    }
}
