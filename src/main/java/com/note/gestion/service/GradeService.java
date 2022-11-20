package com.note.gestion.service;

import com.note.gestion.mapper.GradeMapper;
import com.note.gestion.model.Evaluation;
import com.note.gestion.model.Grade;
import com.note.gestion.repository.EvaluationRepository;
import com.note.gestion.repository.GradeRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class GradeService {
    private GradeRepository gradeRepository;
    private EvaluationRepository evaluationRepository;

    //GET mapping
        //1.get all grade
    public List<Grade> getAllGrade(int page, int pageSize){
        Pageable pageable = PageRequest.of(page-1, pageSize);
        return gradeRepository.findAll(pageable).toList();
    }
        //2.get all grade of one course in one specific semester
    public List<Grade> getGradeByCourse(String idCourse){
        List<Grade> ALL_GRADE = gradeRepository.findAll();
        return ALL_GRADE.stream().filter(element -> {
            return  element
                    .getEvaluation()
                    .getCourse()
                    .getIdCourse()
                    .equals(idCourse);
        }).toList();
    }
        //3.get all grade of one student during all
    public List<Grade> getAllGradeOfOneStudent(String idStudent){
        List<Grade> ALL_GRADE = gradeRepository.findAll();
        return ALL_GRADE.stream().filter(element -> {
            return element
                    .getStudent()
                    .getIdUser()
                    .equals(idStudent);
        }).toList();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //POST mapping
    public Grade insertGrade(GradeMapper newGrade){
        Grade grade = new Grade();
        Evaluation evaluation = evaluationRepository.findById(newGrade.getIdEvaluation()).orElseThrow(()->new NullPointerException("not found"));

        grade.setAverage(newGrade.getAverage());
        grade.setEvaluation(evaluation);
        gradeRepository.save(grade);
        return grade;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //PUT mapping
    public Grade putModification(String idGrade, GradeMapper GRADE_MODIFIED){
        Grade THIS_GRADE = gradeRepository.findById(idGrade).orElseThrow(()->new NullPointerException("not found"));
        Evaluation EVALUATION = evaluationRepository.findById(GRADE_MODIFIED.getIdEvaluation()).orElseThrow(()-> new NullPointerException("not found"));

        if(GRADE_MODIFIED.getAverage() != THIS_GRADE.getAverage()){
            THIS_GRADE.setAverage(GRADE_MODIFIED.getAverage());
        }
        if(GRADE_MODIFIED.getIdEvaluation() != THIS_GRADE.getEvaluation().getIdEvaluation()){
            THIS_GRADE.setEvaluation(EVALUATION);
        }
        gradeRepository.save(THIS_GRADE);
        return THIS_GRADE;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //DELETE mapping
    public String deleteGrade(String idEvaluation){
        gradeRepository.deleteById(idEvaluation);
        return "evaluation deleted successfully";
    }
}
