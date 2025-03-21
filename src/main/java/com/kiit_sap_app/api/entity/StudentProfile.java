package com.kiit_sap_app.api.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@Document(collection = "student_profiles")
public class StudentProfile {

    @Id
    private String rollNo; // Roll Number as the primary identifier

    // Academic Display
    private String studentName;
    private String regdNo;
    private String programOfStudy;
    private String school;
    private int currentSemester;
    private HashSet<Semester> semesters;

    @Data
    @NoArgsConstructor
    public static class Semester {
        private int semesterNumber;
        private HashSet<Subject> subjects;
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Semester semester = (Semester) o;
            return semesterNumber == semester.semesterNumber;
        }

        @Override
        public int hashCode() {
            return Objects.hash(semesterNumber);
        }
    }

    @Data
    @NoArgsConstructor
    public static class Subject {
        private String courseId;
        private String name;
        private String faculty_name;
        private String academicYear;
        private String academicSession;
        private String grade;
        private boolean backlogStatus;
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Subject subject = (Subject) o;
            return Objects.equals(courseId, subject.courseId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(courseId);
        }
    }

    // Personal Details
    private String firstName;
    private String middleName;
    private String lastName;
    private Date birthDate;
    private String nationality;
    private String gender;
    private String maritalStatus;
    private String fullName;
    private String socialClass;
    private String bloodGroup;

    // Address Details
    private Address permanentAddress;
    private Address correspondenceAddress;

    @Data
    @NoArgsConstructor
    public static class Address {
        private String street1;
        private String street2;
        private String street3;
        private String street4;
        private String city;
        private String postalCode;
        private String state;
        private String country;
    }

    // Hostel Details
    private List<Hostel> hostels;

    @Data
    @NoArgsConstructor
    public static class Hostel {
        private String hostelId;
        private String hostelName;
        private Address hostelAddress;
        private String roomId;
        private Date fromDate;
        private Date toDate;
        private Date lastUpdated;
    }

    // Contact Details
    private String studentEmailId;
    private String studentAltEmailId;
    private String studentMobileNo;
    private String studentAltMobileNo;
    private String whatsappNumber;
    private String parentEmailId;
    private String parentMobileNo;

    // Mentor Details
    private Mentor mentor;
    private Mentor coMentor;

    @Data
    @NoArgsConstructor
    public static class Mentor {
        private String mentorName;
        private String contactNumber;
        private String emailId;
    }
}
