package com.kiit_sap_app.api.repository;

import com.kiit_sap_app.api.entity.MentorStudentMap;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MentorStudentMapRepository extends MongoRepository<MentorStudentMap, String> {
    // Additional custom queries can be added here
}
