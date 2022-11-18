package com.note.gestion.service;

import com.note.gestion.mapper.EvaluationMapper;
import com.note.gestion.model.Course;
import com.note.gestion.model.Evaluation;
import com.note.gestion.model.Semestre;
import com.note.gestion.repository.CourseRepository;
import com.note.gestion.repository.EvaluationRepository;
import com.note.gestion.repository.SemestreRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EvaluationService {
    private EvaluationRepository evaluationRepository;
    private SemestreRepository semestreRepository;
    private CourseRepository courseRepository;

    //GET mapping
    public List<Evaluation> getAllEvaluation(int page, int pageSize){
        Pageable pageable = PageRequest.of(page-1, pageSize);
        return evaluationRepository.findAll(pageable).toList();
    }

    //POST mapping
    public Evaluation insertEvaluation(EvaluationMapper newEvaluation){
        Evaluation evaluation = new Evaluation();
        Semestre SEMESTRE = semestreRepository.findById(newEvaluation.getIdSemestre()).orElseThrow(()->new NullPointerException("not found"));
        Course COURSE = courseRepository.findById(newEvaluation.getIdCourse()).orElseThrow(()-> new NullPointerException("not found"));

        evaluation.setDateExamen(newEvaluation.getDateExamen());
        evaluation.setSemestre(SEMESTRE);
        evaluation.setCourse(COURSE);
        evaluationRepository.save(evaluation);
        return evaluation;
    }

    //PUT mapping
    public Evaluation putModification(Long idEvaluation, EvaluationMapper EVALUATION_MODIFIED){
        Evaluation EVALUATION = evaluationRepository.findById(idEvaluation).orElseThrow(()-> new NullPointerException("not found"));
        Semestre SEMESTRE = semestreRepository.findById(EVALUATION_MODIFIED.getIdSemestre()).orElseThrow(()->new NullPointerException("not found"));
        Course COURSE = courseRepository.findById(EVALUATION_MODIFIED.getIdCourse()).orElseThrow(()-> new NullPointerException("not found"));

        if(EVALUATION_MODIFIED.getDateExamen() != EVALUATION.getDateExamen()){
            EVALUATION.setDateExamen(EVALUATION_MODIFIED.getDateExamen());
        }
        if(EVALUATION_MODIFIED.getIdSemestre() != EVALUATION.getSemestre().getIdSemestre()){
            EVALUATION.setSemestre(SEMESTRE);
        }
        if(EVALUATION_MODIFIED.getIdCourse() != EVALUATION.getCourse().getIdCourse()){
            EVALUATION.setCourse(COURSE);
        }
        evaluationRepository.save(EVALUATION);
        return EVALUATION;
    }

    //DELETE mapping
    public String deleteEvaluation(Long idEvaluation){
        evaluationRepository.deleteById(idEvaluation);
        return "evaluation deleted successfully";
    }
}
