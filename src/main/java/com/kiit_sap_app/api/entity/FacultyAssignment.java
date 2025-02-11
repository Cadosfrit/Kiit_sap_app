package com.kiit_sap_app.api.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "faculty_assignments")
public class FacultyAssignment {

    @Id
    private String facultyId; // Faculty unique identifier
    private Map<String, List<String>> sectionCourseMap =new HashMap<>();; // Maps sectionId → List of courseIds

}
