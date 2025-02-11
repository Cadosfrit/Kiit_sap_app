package com.kiit_sap_app.api.service;

import com.kiit_sap_app.api.entity.FacultyAssignment;
import com.kiit_sap_app.api.repository.FacultyAssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FacultyAssignmentService {

    @Autowired
    private FacultyAssignmentRepository facultyAssignmentRepository;

    /**
     * Get the faculty assignment details by faculty ID.
     */
    public FacultyAssignment getFacultyAssignment(String facultyId) {
        return facultyAssignmentRepository.findById(facultyId).orElse(null);
    }

    /**
     * Assign a course to a faculty for a specific section.
     */
    public void assignCourseToFaculty(String facultyId, String sectionId, String courseId) {
        FacultyAssignment faculty = facultyAssignmentRepository.findById(facultyId)
                .orElseGet(() -> {
                    FacultyAssignment newFaculty = new FacultyAssignment();
                    newFaculty.setFacultyId(facultyId);
                    newFaculty.setSectionCourseMap(new HashMap<>()); // Ensure it's initialized
                    return facultyAssignmentRepository.save(newFaculty);
                });

        // Ensure sectionCourseMap is initialized before using computeIfAbsent
        if (faculty.getSectionCourseMap() == null) {
            faculty.setSectionCourseMap(new HashMap<>());
        }

        faculty.getSectionCourseMap()
                .computeIfAbsent(sectionId, k -> new ArrayList<>())
                .add(courseId);

        facultyAssignmentRepository.save(faculty);
    }


    /**
     * Get the list of courseIds assigned to a faculty for a specific section.
     */
    public List<String> getCoursesForFacultyInSection(String facultyId, String sectionId) {
        Optional<FacultyAssignment> facultyAssignment = facultyAssignmentRepository.findById(facultyId);

        return facultyAssignment.map(assign -> assign.getSectionCourseMap().getOrDefault(sectionId, List.of()))
                .orElse(List.of());
    }

    /**
     * Remove a course assignment for a faculty in a specific section.
     */
    public void removeCourseAssignment(String facultyId, String sectionId, String courseId) {
        facultyAssignmentRepository.findById(facultyId).ifPresent(facultyAssignment -> {
            Map<String, List<String>> sectionCourseMap = facultyAssignment.getSectionCourseMap();
            sectionCourseMap.getOrDefault(sectionId, List.of()).remove(courseId);

            if (sectionCourseMap.get(sectionId).isEmpty()) {
                sectionCourseMap.remove(sectionId);
            }

            facultyAssignmentRepository.save(facultyAssignment);
        });
    }
    public List<String> getAssignedSections(String facultyId) {
        return facultyAssignmentRepository.findById(facultyId)
                .map(faculty -> new ArrayList<>(faculty.getSectionCourseMap().keySet()))
                .orElse(new ArrayList<>());

    }

    public void removeAllAssignments(String facultyId) {
        facultyAssignmentRepository.deleteById(facultyId);
    }

}
