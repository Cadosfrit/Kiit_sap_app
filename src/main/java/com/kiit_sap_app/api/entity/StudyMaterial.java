package com.kiit_sap_app.api.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "study_materials")
public class StudyMaterial {

    @Id
    private String id;
    private String subjectName;
    private String materialName;
    private String downloadLink;

    public StudyMaterial(String subjectName, String materialName, String downloadLink) {
        this.subjectName = subjectName;
        this.materialName = materialName;
        this.downloadLink = downloadLink;
    }
}

