// TimetableService.java 
package com.kiit_sap_app.api.service;

import com.kiit_sap_app.api.entity.Timetable;
import com.kiit_sap_app.api.repository.TimetableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TimetableService {

    @Autowired
    private TimetableRepository timetableRepository;

    // Upload Timetable (this can be used to upload a default timetable initially)
    public Timetable uploadTimetable(Timetable timetable) {
        return timetableRepository.save(timetable);  // Save the timetable in the DB
    }

    // Edit Timetable (update a specific timeslot based on classID, day, and start time)
    public Timetable editTimetable(String classID, String day, String startTime, String subjectName) {
        // Fetch the timetable by classID
        Optional<Timetable> timetableOptional = timetableRepository.findByClassID(classID);

        if (timetableOptional.isPresent()) {
            Timetable timetable = timetableOptional.get();
            // Find the day from the timetable (Monday-Saturday)
            for (Timetable.Day timetableDay : timetable.getDays()) {
                if (timetableDay.getDay().equalsIgnoreCase(day)) {
                    // Find the timeslot in the day
                    for (Timetable.Day.TimeSlot timeSlot : timetableDay.getTimeSlots()) {
                        if (timeSlot.getStartTime().equals(startTime)) {
                            // Update the subject name for the specific timeslot

                            timeSlot.setSubjectID(subjectName);
                            break;
                        }
                    }
                    break;
                }
            }
            // Save the updated timetable back to the database
            return timetableRepository.save(timetable);
        } else {
            throw new IllegalArgumentException("Timetable not found for class ID: " + classID);
        }
    }

    public Timetable deleteSubject(String classID, String day, String startTime) {
        // Fetch the timetable by classID
        Optional<Timetable> timetableOptional = timetableRepository.findByClassID(classID);

        if (timetableOptional.isPresent()) {
            Timetable timetable = timetableOptional.get();
            // Find the day from the timetable (Monday-Saturday)
            for (Timetable.Day timetableDay : timetable.getDays()) {
                if (timetableDay.getDay().equalsIgnoreCase(day)) {
                    // Find the timeslot in the day
                    for (Timetable.Day.TimeSlot timeSlot : timetableDay.getTimeSlots()) {
                        if (timeSlot.getStartTime().equals(startTime)) {
                            // Update the subject name for the specific timeslot

                            timeSlot.setSubjectID("");
                            break;
                        }
                    }
                    break;
                }
            }
            // Save the updated timetable back to the database
            return timetableRepository.save(timetable);
        } else {
            throw new IllegalArgumentException("Timetable not found for class ID: " + classID);
        }
    }
    // Get Timetable by classID
    public Timetable getTimetableByClassID(String classID) {
        Optional<Timetable> timetableOptional = timetableRepository.findByClassID(classID);
        return timetableOptional.orElseThrow(() -> new IllegalArgumentException("Timetable not found for class ID: " + classID));
    }

    public List<Timetable> getAllTimetables() {
        return timetableRepository.findAll();
    }
}
