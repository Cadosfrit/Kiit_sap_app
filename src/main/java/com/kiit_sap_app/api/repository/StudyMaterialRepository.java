package com.kiit_sap_app.api.repository;

import com.kiit_sap_app.api.entity.StudyMaterial;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface StudyMaterialRepository extends MongoRepository<StudyMaterial, String> {
    List<StudyMaterial> findBySubjectNameIgnoreCase(String subjectName);
}
