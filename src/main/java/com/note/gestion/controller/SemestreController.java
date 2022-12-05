package com.note.gestion.controller;

import com.note.gestion.model.Semestre;
import com.note.gestion.service.SemestreService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class SemestreController {
    private SemestreService semestreService;

    @GetMapping("/semestre")
    public List<Semestre> getAllSemestre(
            @RequestParam(name = "page")int page,
            @RequestParam(name = "page_size")int pageSize
    ){
        return semestreService.getAllSemestre(page, pageSize);
    }

    @PostMapping("/semestre")
    public Semestre insertSemestre(@RequestBody Semestre newSemestre){
        return semestreService.insertSemestre(newSemestre);
    }

    @PutMapping("/semestre/{id_semestre}")
    public Semestre putModification(
            @PathVariable(name = "id_semestre")Long idSemestre,
            @RequestBody Semestre semestreModified
    ){
        return semestreService.putModification(idSemestre, semestreModified);
    }

    @DeleteMapping("/semestre/{id_semestre}")
    public String deleteSemestre(@PathVariable(name = "id_semestre")Long idSemestre){
        return semestreService.deleteSemestre(idSemestre);
    }
}
