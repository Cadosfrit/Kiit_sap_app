package com.kiit_sap_app.api.controller;

import com.kiit_sap_app.api.entity.Attendance;
import com.kiit_sap_app.api.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    // Get attendance for the authenticated student for a specific course
    @GetMapping("/{courseId}")
    public Attendance.StudentAttendance getStudentAttendance(@PathVariable String courseId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return attendanceService.getStudentAttendance(courseId, authentication);
    }

    // Update attendance for a student
    @PutMapping("/update")
    public void updateAttendance(@RequestParam String sectionId, @RequestParam String courseId,
                                 @RequestBody Map<String, Boolean> studentAttendanceMap) {
        attendanceService.updateBulkAttendance(sectionId, courseId, studentAttendanceMap);
    }
    // Fetch full attendance for a section and course (faculty/admin)
    @GetMapping("/{sectionId}/{courseId}/full")
    public Map<String, Attendance.StudentAttendance> getFullAttendance(
            @PathVariable String sectionId, @PathVariable String courseId) {
        return attendanceService.getFullAttendance(sectionId, courseId);
    }

}
