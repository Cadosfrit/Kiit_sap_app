package com.kiit_sap_app.api.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "attendance_records")
public class Attendance {

    @Id
    private String id;
    private String sectionId;
    private String courseId;

    private Map<String, StudentAttendance> studentAttendanceRecords = new HashMap<>();

    @Getter
    @Setter
    @NoArgsConstructor
    public static class StudentAttendance {
        private String rollNo;
        private int totalClasses = 0;
        private int presentClasses = 0;

        public double getAttendancePercentage() {
            return (totalClasses == 0) ? 0 : ((double) presentClasses / totalClasses) * 100;
        }

        public void markPresent() {
            presentClasses++;
            totalClasses++;
        }

        public void markAbsent() {
            totalClasses++;
        }
    }
}

