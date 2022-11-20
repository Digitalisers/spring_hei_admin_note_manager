package com.note.gestion.mapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class EvaluationMapper {
    private String idEvaluation;
    private LocalDate dateExamen;
    private String idCourse;
    private String idSemestre;
}
