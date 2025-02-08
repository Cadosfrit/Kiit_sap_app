package com.kiit_sap_app.api.repository;

import com.kiit_sap_app.api.entity.LeaveApplication;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface LeaveApplicationRepository extends MongoRepository<LeaveApplication, String> {
    List<LeaveApplication> findByRollNo(String rollNo);
}
