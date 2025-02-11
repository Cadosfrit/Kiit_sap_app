package com.kiit_sap_app.api.repository;

import com.kiit_sap_app.api.entity.Section;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SectionRepository extends MongoRepository<Section, String> {
    List<Section> findByStudentRollNosContaining(String rollNo);
}
