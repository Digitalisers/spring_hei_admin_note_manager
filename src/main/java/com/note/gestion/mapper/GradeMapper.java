package com.note.gestion.mapper;

import com.note.gestion.model.Evaluation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class GradeMapper {
    private Long idGrade;
    private Float average;
    private Long idEvaluation;
}
