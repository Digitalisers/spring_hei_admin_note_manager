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

import java.util.List;

@Service
@AllArgsConstructor
public class GradeService {
    private GradeRepository gradeRepository;
    private EvaluationRepository evaluationRepository;

    //GET mapping
    public List<Grade> getAllGrade(int page, int pageSize){
        Pageable pageable = PageRequest.of(page-1, pageSize);
        return gradeRepository.findAll(pageable).toList();
    }

    //POST mapping
    public Grade insertGrade(GradeMapper newGrade){
        Grade grade = new Grade();
        Evaluation evaluation = evaluationRepository.findById(newGrade.getIdEvaluation()).orElseThrow(()->new NullPointerException("not found"));

        grade.setAverage(newGrade.getAverage());
        grade.setEvaluation(evaluation);
        gradeRepository.save(grade);
        return grade;
    }

    //PUT mapping
    public Grade putModification(Long idGrade, GradeMapper GRADE_MODIFIED){
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

    //DELETE mapping
    public String deleteGrade(Long idEvaluation){
        gradeRepository.deleteById(idEvaluation);
        return "evaluation deleted successfully";
    }
}
