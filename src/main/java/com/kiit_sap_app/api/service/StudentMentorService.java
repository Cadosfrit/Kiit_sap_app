package com.kiit_sap_app.api.service;

import com.kiit_sap_app.api.entity.StudentMentorMap;
import com.kiit_sap_app.api.entity.MentorStudentMap;
import com.kiit_sap_app.api.repository.StudentMentorMapRepository;
import com.kiit_sap_app.api.repository.MentorStudentMapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentMentorService {

    @Autowired
    private StudentMentorMapRepository studentMentorMapRepository;

    @Autowired
    private MentorStudentMapRepository mentorStudentMapRepository;

    // Assign or update mentor for student
    public void assignMentor(StudentMentorMap studentMentorMap) {
        String studentId = studentMentorMap.getStudentId();
        String mentorId = studentMentorMap.getMentorId();

        // Check if student is already assigned to a mentor
        Optional<StudentMentorMap> existingMapping = studentMentorMapRepository.findById(studentId);
        if (existingMapping.isPresent()) {
            // Remove student from current mentor's list if changing mentor
            String currentMentorId = existingMapping.get().getMentorId();
            removeStudentFromMentorList(currentMentorId, studentId);
        }

        // Save or update the student-mentor mapping
        studentMentorMapRepository.save(studentMentorMap);

        // Update the mentor-student map
        MentorStudentMap mentorStudentMap = mentorStudentMapRepository.findById(mentorId)
                .orElse(new MentorStudentMap());
        mentorStudentMap.setMentorId(mentorId);
        mentorStudentMap.getStudentIds().add(studentId);
        mentorStudentMapRepository.save(mentorStudentMap);
    }

    // Get mentor ID by student ID
    public String getMentorIdByStudentId(String studentId) {
        Optional<StudentMentorMap> studentMentorMap = studentMentorMapRepository.findById(studentId);
        return studentMentorMap.map(StudentMentorMap::getMentorId).orElse(null);
    }

    // Get list of students by mentor ID
    public List<String> getStudentsByMentorId(String mentorId) {
        Optional<MentorStudentMap> mentorStudentMap = mentorStudentMapRepository.findById(mentorId);
        return mentorStudentMap.map(MentorStudentMap::getStudentIds).orElse(List.of());
    }

    // Remove student from mentor and student-mentor mappings
    public void removeStudentFromMentor(String studentId) {
        // Remove student from the student-mentor map
        studentMentorMapRepository.deleteById(studentId);

        // Remove student from the mentor-student map
        Optional<StudentMentorMap> studentMentorMap = studentMentorMapRepository.findById(studentId);
        studentMentorMap.ifPresent(studentMap -> {
            String mentorId = studentMap.getMentorId();
            removeStudentFromMentorList(mentorId, studentId);
        });
    }

    // Helper method to remove student ID from mentor's list
    private void removeStudentFromMentorList(String mentorId, String studentId) {
        Optional<MentorStudentMap> mentorStudentMap = mentorStudentMapRepository.findById(mentorId);
        mentorStudentMap.ifPresent(map -> {
            map.getStudentIds().remove(studentId);
            mentorStudentMapRepository.save(map);
        });
    }

    public boolean addMentor(MentorStudentMap mentorStudentMap) {
        if (mentorStudentMapRepository.existsById(mentorStudentMap.getMentorId())) {
            // Mentor already exists
            return false;
        }

        // Initialize the student list as empty
        mentorStudentMap.setStudentIds(new ArrayList<>());
        mentorStudentMapRepository.save(mentorStudentMap);
        return true;
    }
}
