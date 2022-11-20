package com.note.gestion.mapper;

import com.note.gestion.model.Evaluation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class GradeMapper {
    private String idGrade;
    private Float average;
    private String idEvaluation;
}
