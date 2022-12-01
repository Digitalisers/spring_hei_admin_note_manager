package com.note.gestion.controller;

import com.note.gestion.model.Mention;
import com.note.gestion.service.MentionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class MentionController {
    private MentionService mentionService;

    @GetMapping("/mention")
    public List<Mention> getAllMention(
            @RequestParam(name = "page")int page,
            @RequestParam(name = "page_size")int pageSize
    ){
        return mentionService.getAllMention(page, pageSize);
    }

    @PostMapping("/mention")
    public Mention insertMention(@RequestParam Mention newMention){
        return mentionService.insertMention(newMention);
    }

    @PutMapping("/mention/{id_mention}")
    public Mention putModification(
            @PathVariable(name = "id_mention")Long idMention,
            @RequestParam Mention mentionModified
    ){
        return mentionService.putModification(idMention, mentionModified);
    }

    @DeleteMapping("/mention/{id_mention}")
    public String deleteMention(@PathVariable(name = "id_mention")Long idMention){
        return mentionService.deleteMention(idMention);
    }
}
