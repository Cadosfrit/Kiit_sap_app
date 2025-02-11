package com.kiit_sap_app.api.repository;

import com.kiit_sap_app.api.entity.Attendance;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AttendanceRepository extends MongoRepository<Attendance, String> {
    Optional<Attendance> findBySectionIdAndCourseId(String sectionId, String courseId);
}
