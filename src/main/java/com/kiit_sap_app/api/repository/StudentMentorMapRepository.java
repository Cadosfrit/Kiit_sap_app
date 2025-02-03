package com.kiit_sap_app.api.repository;

import com.kiit_sap_app.api.entity.StudentMentorMap;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentMentorMapRepository extends MongoRepository<StudentMentorMap, String> {
    // Additional custom queries can be added here
}
