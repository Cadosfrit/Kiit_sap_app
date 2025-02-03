// UserRepository.java 
package com.kiit_sap_app.api.repository;

import com.kiit_sap_app.api.entity.StudentProfile;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentProfileRepository extends MongoRepository<StudentProfile, String> {
}
