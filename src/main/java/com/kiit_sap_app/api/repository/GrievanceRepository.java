package com.kiit_sap_app.api.repository;

import com.kiit_sap_app.api.entity.Grievance;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrievanceRepository extends MongoRepository<Grievance, String> {
    List<Grievance> findByRollNo(String rollNo); // Fetch grievances by roll number
}
