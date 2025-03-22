package com.kiit_sap_app.api.controller;

import com.kiit_sap_app.api.entity.Section;
import com.kiit_sap_app.api.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sections")
public class SectionController {

    @Autowired
    private SectionService sectionService;

    /**
     * Get all students in a given section.
     */
    @GetMapping("/{sectionId}/students")
    public List<String> getStudentsInSection(@PathVariable String sectionId) {
        return sectionService.getStudentsInSection(sectionId);
    }
    // Fetch all sections
    @GetMapping("/")
    public List<Section> getAllSections() {
        return sectionService.getAllSections();
    }
    // Create a new section
    @PostMapping("/")
    public Section createSection(@RequestBody Section section) {
        return sectionService.createSection(section);
    }
    // Delete a section
    @DeleteMapping("/{sectionId}")
    public void deleteSection(@PathVariable String sectionId) {
        sectionService.deleteSection(sectionId);
    }
    //get section by student roll no
    @GetMapping("/StudentSection")
    public String findSectionByRollNo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String rollNo=authentication.getName();
        return sectionService.getSectionsByStudentRollNo(rollNo);
    }
}
