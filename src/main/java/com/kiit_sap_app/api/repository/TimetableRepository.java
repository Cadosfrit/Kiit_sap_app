package com.kiit_sap_app.api.repository;

import com.kiit_sap_app.api.entity.Timetable;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TimetableRepository extends MongoRepository<Timetable, ObjectId> {

    // Find a timetable by its classID
    Optional<Timetable> findByClassIDAndSemesterNo(String classID, int semesterNo);

}
