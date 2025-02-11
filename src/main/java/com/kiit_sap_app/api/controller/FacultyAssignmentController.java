package com.kiit_sap_app.api.controller;

import com.kiit_sap_app.api.entity.FacultyAssignment;
import com.kiit_sap_app.api.service.FacultyAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/faculty-assignments")
public class FacultyAssignmentController {

    @Autowired
    private FacultyAssignmentService facultyAssignmentService;

    /**
     * Get faculty assignment details by faculty ID.
     */
    @GetMapping("/{facultyId}")
    public FacultyAssignment getFacultyAssignmentById(@PathVariable String facultyId) {
        return facultyAssignmentService.getFacultyAssignment(facultyId);
    }

    /**
     * Assign a course to a faculty for a specific section.
     */
    @PostMapping("/assign")
    public void assignCourseToFaculty(@RequestParam String facultyId,
                                      @RequestParam String sectionId,
                                      @RequestParam String courseId) {
        facultyAssignmentService.assignCourseToFaculty(facultyId, sectionId, courseId);
    }

    /**
     * Get the list of course IDs assigned to a faculty for a specific section.
     */
    @GetMapping("/{facultyId}/sections/{sectionId}/courses")
    public List<String> getCoursesForFacultyInSection(@PathVariable String facultyId,
                                                      @PathVariable String sectionId) {
        return facultyAssignmentService.getCoursesForFacultyInSection(facultyId, sectionId);
    }

    /**
     * Remove a course assignment for a faculty in a specific section.
     */
    @DeleteMapping("/remove")
    public void removeCourseAssignment(@RequestParam String facultyId,
                                       @RequestParam String sectionId,
                                       @RequestParam String courseId) {
        facultyAssignmentService.removeCourseAssignment(facultyId, sectionId, courseId);
    }
    // Fetch all sections assigned to a faculty
    @GetMapping("/{facultyId}/sections")
    public List<String> getAssignedSections(@PathVariable String facultyId) {
        return facultyAssignmentService.getAssignedSections(facultyId);
    }
    // Remove all course assignments for a faculty (admin use case)
    @DeleteMapping("/{facultyId}")
    public void removeAllAssignments(@PathVariable String facultyId) {
        facultyAssignmentService.removeAllAssignments(facultyId);
    }

}
