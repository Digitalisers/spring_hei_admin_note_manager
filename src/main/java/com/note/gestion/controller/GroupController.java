package com.note.gestion.controller;

import com.note.gestion.model.Group;
import com.note.gestion.service.GroupService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class GroupController {
    private GroupService groupService;

    @GetMapping("/group")
    public List<Group> getAllGroup(
            @RequestParam(name = "page")int page,
            @RequestParam(name = "page_size")int pageSize
    ){
        return groupService.getAllGroup(page, pageSize);
    }

    @PostMapping("/group")
    public Group insertGroup(@RequestParam Group newGroup){
        return groupService.insertGroup(newGroup);
    }

    @PutMapping("/group/{id_group}")
    public Group putModification(
            @PathVariable(name = "id_group")Long idGroup,
            @RequestParam Group groupModified
    ){
        return groupService.putModification(idGroup, groupModified);
    }

    @DeleteMapping("/group/{id_group}")
    public String deleteGroup(@PathVariable(name = "id_group")Long idGroup){
        return groupService.deleteGroup(idGroup);
    }
}
