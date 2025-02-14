package com.kiit_sap_app.api.controller;

import com.kiit_sap_app.api.entity.StudyMaterial;
import com.kiit_sap_app.api.service.StudyMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/study-materials")
public class StudyMaterialController {

    @Autowired
    private StudyMaterialService studyMaterialService;

    // POST: Admin/Faculty uploads study material
    @PostMapping
    public void addStudyMaterial(@RequestParam String subjectName,
                                 @RequestParam String materialName,
                                 @RequestParam String downloadLink) {
        studyMaterialService.addStudyMaterial(subjectName, materialName, downloadLink);
    }

    // GET: Fetch study material by subject
    @GetMapping("/{subjectName}")
    public List<StudyMaterial> getStudyMaterials(@PathVariable String subjectName) {
        return studyMaterialService.getStudyMaterialsBySubject(subjectName);
    }
}
