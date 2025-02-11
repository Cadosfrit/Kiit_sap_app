package com.kiit_sap_app.api.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "sections")
public class Section {

    @Id
    private String sectionId; // e.g., "CS1A"
    private Set<String> studentRollNos=new HashSet<>(); // List of student roll numbers

}
