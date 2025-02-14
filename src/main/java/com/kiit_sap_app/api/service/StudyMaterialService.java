package com.kiit_sap_app.api.service;

import com.kiit_sap_app.api.entity.StudyMaterial;
import com.kiit_sap_app.api.repository.StudyMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudyMaterialService {

    @Autowired
    private StudyMaterialRepository studyMaterialRepository;

    public void addStudyMaterial(String subjectName, String materialName, String downloadLink) {
        StudyMaterial studyMaterial = new StudyMaterial(subjectName, materialName, downloadLink);
        studyMaterialRepository.save(studyMaterial);
    }

    public List<StudyMaterial> getStudyMaterialsBySubject(String subjectName) {
        return studyMaterialRepository.findBySubjectNameIgnoreCase(subjectName);
    }
}
