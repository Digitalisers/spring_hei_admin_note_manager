package com.note.gestion.controller;

import com.note.gestion.model.GroupHei;
import com.note.gestion.service.GroupService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class GroupController {
    private GroupService groupService;

    @GetMapping("/groupHei")
    public List<GroupHei> getAllGroup(
            @RequestParam(name = "page")int page,
            @RequestParam(name = "page_size")int pageSize
    ){
        return groupService.getAllGroup(page, pageSize);
    }

    @PostMapping("/groupHei")
    public GroupHei insertGroup(@RequestParam GroupHei newGroupHei){
        return groupService.insertGroup(newGroupHei);
    }

    @PutMapping("/groupHei/{id_group}")
    public GroupHei putModification(
            @PathVariable(name = "id_group")Long idGroup,
            @RequestParam GroupHei groupHeiModified
    ){
        return groupService.putModification(idGroup, groupHeiModified);
    }

    @DeleteMapping("/groupHei/{id_group}")
    public String deleteGroup(@PathVariable(name = "id_group")Long idGroup){
        return groupService.deleteGroup(idGroup);
    }
}
