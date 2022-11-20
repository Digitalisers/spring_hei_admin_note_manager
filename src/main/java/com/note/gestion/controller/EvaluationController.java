package com.note.gestion.controller;

import com.note.gestion.mapper.EvaluationMapper;
import com.note.gestion.model.Evaluation;
import com.note.gestion.service.EvaluationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class EvaluationController {
    private EvaluationService evaluationService;

    @GetMapping("/evaluation")
    public List<Evaluation> getAllEvaluation(
            @RequestParam(name = "page")int page,
            @RequestParam(name = "page_size")int pageSize
    ){
        return evaluationService.getAllEvaluation(page, pageSize);
    }

    @PostMapping("/evaluation")
    public Evaluation insertEvaluation(@RequestParam EvaluationMapper newEvaluation){
        return evaluationService.insertEvaluation(newEvaluation);
    }

    @PutMapping("/evaluation/{id_evaluation}")
    public Evaluation putModification(
            @PathVariable(name = "id_evaluation")String idEvaluation,
            @RequestParam EvaluationMapper evaluationModified
    ){
        return evaluationService.putModification(idEvaluation, evaluationModified);
    }

    @DeleteMapping("/evaluation/{id_evaluation}")
    public String deleteEvaluation(@PathVariable(name = "id_evaluation")String idEvaluation){
        return evaluationService.deleteEvaluation(idEvaluation);
    }
}
