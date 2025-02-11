package com.kiit_sap_app.api.service;

import com.kiit_sap_app.api.entity.Section;
import com.kiit_sap_app.api.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SectionService {

    @Autowired
    private SectionRepository sectionRepository;

    /**
     * Get all students of a given section.
     */
    public List<String> getStudentsInSection(String sectionId) {
        Optional<Section> section = sectionRepository.findById(sectionId);
        return section.map(s -> new ArrayList<>(s.getStudentRollNos()))
                .orElseThrow(() -> new RuntimeException("Section not found with ID: " + sectionId));
    }

    public String getSectionsByStudentRollNo(String rollNo) {
        List<Section> sections = sectionRepository.findByStudentRollNosContaining(rollNo);
        return sections.stream().map(Section::getSectionId).toList().getFirst();
    }

    public List<Section> getAllSections() {
        return sectionRepository.findAll();
    }

    public Section createSection(Section section) {
        return sectionRepository.save(section);
    }

    public void deleteSection(String sectionId) {
        sectionRepository.deleteById(sectionId);
    }

}
