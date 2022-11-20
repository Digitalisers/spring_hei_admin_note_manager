package com.note.gestion.service;

import com.note.gestion.model.Semestre;
import com.note.gestion.repository.SemestreRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SemestreService {
    private SemestreRepository semestreRepository;

    //GET mapping
    public List<Semestre> getAllSemestre(int page, int pageSize){
        Pageable pageable = PageRequest.of(page-1, pageSize);
        return semestreRepository.findAll(pageable).toList();
    }

    //POST mapping
    public Semestre insertSemestre(Semestre SEMESTRE){
        Semestre NEW_SEMSTRE = new Semestre();
        NEW_SEMSTRE.setAverage(SEMESTRE.getAverage());
        NEW_SEMSTRE.setMention(SEMESTRE.getMention());
        NEW_SEMSTRE.setName(SEMESTRE.getName());
        semestreRepository.save(NEW_SEMSTRE);
        return NEW_SEMSTRE;
    }

    //PUT mapping
    public Semestre putModification(String idSemestre, Semestre SEMESTRE_MODIFIED){
        Semestre SEMESTRE = semestreRepository.findById(idSemestre).orElseThrow(()-> new NullPointerException("not found"));
        if(SEMESTRE_MODIFIED.getAverage() != SEMESTRE.getAverage()){
            SEMESTRE.setAverage(SEMESTRE_MODIFIED.getAverage());
        }
        if(SEMESTRE_MODIFIED.getMention() != SEMESTRE.getMention()){
            SEMESTRE.setMention(SEMESTRE_MODIFIED.getMention());
        }
        if(SEMESTRE_MODIFIED.getName() != SEMESTRE.getName()){
            SEMESTRE.setName(SEMESTRE_MODIFIED.getName());
        }
        semestreRepository.save(SEMESTRE);
        return SEMESTRE;
    }

    //DELETE mapping
    public String deleteSemestre(String idSemestre){
        semestreRepository.deleteById(idSemestre);
        return "semestre deleted successfully";
    }
}
