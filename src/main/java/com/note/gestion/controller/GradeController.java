package com.note.gestion.controller;

import com.note.gestion.mapper.GradeMapper;
import com.note.gestion.model.Grade;
import com.note.gestion.service.GradeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class GradeController {
    private GradeService gradeService;

    @GetMapping("/grade")
    public List<Grade> getAllGrade(
            @RequestParam(name = "page")int page,
            @RequestParam(name = "page_size")int pageSize
    ){
        return gradeService.getAllGrade(page, pageSize);
    }

    @GetMapping("/course/grade/{id_course}")
    public List<Grade> getGradeByCourse(@PathVariable(name = "id_course")Long idCourse){
        return gradeService.getGradeByCourse(idCourse);
    }

    @PostMapping("/grade")
    public Grade insertGrade(@RequestParam GradeMapper newGrade){
        return gradeService.insertGrade(newGrade);
    }

    @PutMapping("/grade/{id_grade}")
    public Grade putModification(
            @PathVariable(name = "id_grade")Long idGrade,
            @RequestParam GradeMapper gradeModified
    ){
        return gradeService.putModification(idGrade, gradeModified);
    }

    @DeleteMapping("/grade/{id_grade}")
    public String deleteGrade(@PathVariable(name = "id_grade")Long idGrade){
        return gradeService.deleteGrade(idGrade);
    }
}
