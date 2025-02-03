package com.kiit_sap_app.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "timetable")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Timetable {

    @Id
    private ObjectId id;
    private String classID;
    private List<Day> days;  // List of Days (containing a List of TimeSlots)

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Day {

        private String day;  // e.g., Monday, Tuesday, etc.
        private List<TimeSlot> timeSlots;  // List of TimeSlots for that day

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class TimeSlot {

            private String startTime;  // e.g., 9:00 AM
            private String endTime;    // e.g., 10:00 AM
            private String subjectID;  // Subject Identifier
        }
    }
}
