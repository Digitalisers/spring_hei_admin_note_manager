package com.note.gestion.mapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class EvaluationMapper {
    private Long idEvaluation;
    private LocalDate dateExamen;
    private Long idCourse;
    private Long idSemestre;
}
