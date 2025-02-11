package com.kiit_sap_app.api.service;

import com.kiit_sap_app.api.entity.Attendance;
import com.kiit_sap_app.api.entity.Section;
import com.kiit_sap_app.api.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private SectionService sectionService;

    public Attendance.StudentAttendance getStudentAttendance(String courseId, Authentication authentication) {
        String rollNo = authentication.getName();

        Optional<Attendance> attendanceOpt = attendanceRepository.findBySectionIdAndCourseId(sectionService.getSectionsByStudentRollNo(rollNo), courseId);
        if (attendanceOpt.isEmpty()) {
            throw new RuntimeException("Attendance record not found for the course");
        }

        return attendanceOpt.get().getStudentAttendanceRecords().getOrDefault(rollNo, new Attendance.StudentAttendance());
    }

    public void updateBulkAttendance(String sectionId, String courseId, Map<String, Boolean> studentAttendanceMap) {
        Attendance attendance = attendanceRepository.findBySectionIdAndCourseId(sectionId, courseId)
                .orElseGet(() -> {
                    Attendance newAttendance = new Attendance();
                    newAttendance.setSectionId(sectionId);
                    newAttendance.setCourseId(courseId);
                    return newAttendance;
                });

        // Iterate over each student in the provided map and update attendance
        studentAttendanceMap.forEach((rollNo, isPresent) -> {
            Attendance.StudentAttendance studentAttendance = attendance.getStudentAttendanceRecords()
                    .computeIfAbsent(rollNo, k -> {
                        Attendance.StudentAttendance newRecord = new Attendance.StudentAttendance();
                        newRecord.setRollNo(k); // Set the roll number explicitly
                        return newRecord;
                    });

            if (isPresent) {
                studentAttendance.markPresent();
            } else {
                studentAttendance.markAbsent();
            }

            // Ensure the updated record is placed back in the map (not strictly necessary, but for clarity)
            attendance.getStudentAttendanceRecords().put(rollNo, studentAttendance);
        });

        // Save updated attendance record in one DB call
        attendanceRepository.save(attendance);
    }

    public Map<String, Attendance.StudentAttendance> getFullAttendance(String sectionId, String courseId) {
        Attendance attendance = attendanceRepository.findBySectionIdAndCourseId(sectionId, courseId)
                .orElseThrow(() -> new RuntimeException("No attendance record found."));
        return attendance.getStudentAttendanceRecords();
    }

}
