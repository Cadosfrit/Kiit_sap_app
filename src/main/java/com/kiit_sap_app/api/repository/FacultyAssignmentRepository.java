package com.kiit_sap_app.api.repository;

import com.kiit_sap_app.api.entity.FacultyAssignment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FacultyAssignmentRepository extends MongoRepository<FacultyAssignment, String> {
}
