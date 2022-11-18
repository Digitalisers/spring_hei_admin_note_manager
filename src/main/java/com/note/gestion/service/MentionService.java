package com.note.gestion.service;

import com.note.gestion.model.Mention;
import com.note.gestion.repository.MentionRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MentionService {
    private MentionRepository mentionRepository;

    //GET mapping
    public List<Mention> getAllMention(int page, int pageSize){
        Pageable pageable = PageRequest.of(page-1, pageSize);
        return mentionRepository.findAll(pageable).toList();
    }

    //POST mapping
    public Mention insertMention(Mention newMention){
        Mention mention = new Mention();
        mention.setName(newMention.getName());
        mention.setStartNote(newMention.getStartNote());
        mentionRepository.save(mention);
        return mention;
    }

    //PUT mapping
    public Mention putModification(Long idMention, Mention MENTION_MODIFIED){
        Mention MENTION = mentionRepository.findById(idMention).orElseThrow(()->new NullPointerException("not found"));
        if(MENTION_MODIFIED.getName() != MENTION.getName()){
            MENTION.setName(MENTION_MODIFIED.getName());
        }
        if(MENTION_MODIFIED.getStartNote() != MENTION.getStartNote()){
            MENTION.setStartNote(MENTION_MODIFIED.getStartNote());
        }
        mentionRepository.save(MENTION);
        return MENTION;
    }

    //DELETE mapping
    public String deleteMention(Long idMention){
        mentionRepository.deleteById(idMention);
        return "mention deleted successfully";
    }
}
