package com.kiit_sap_app.api.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "student-mentor-map")
@Data
public class StudentMentorMap {
    @Id
    private String studentId;  // Reference to student ID
    private String mentorId;   // Reference to mentor ID
}

