package com.kiit_sap_app.api.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "mentor-student-map")
@Data
public class MentorStudentMap {
    @Id
    private String mentorId;       // Reference to mentor ID
    private List<String> studentIds; // List of student IDs
}
