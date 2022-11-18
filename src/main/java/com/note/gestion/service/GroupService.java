package com.note.gestion.service;

import com.note.gestion.model.Group;
import com.note.gestion.repository.GroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GroupService {
    private GroupRepository groupRepository;

    //GET mapping
    public List<Group> getAllGroup(int page, int pageSize){
        Pageable pageable = PageRequest.of(page-1, pageSize);
        return groupRepository.findAll(pageable).toList();
    }

    //POST mapping
    public Group insertGroup(Group newGroup){
        Group group = new Group();
        group.setName(newGroup.getName());
        group.setCreationDatetime(newGroup.getCreationDatetime());
        group.setRef(newGroup.getRef());
        groupRepository.save(group);
        return group;
    }

    //PUT mapping
    public Group putModification(Long idGroup, Group GROUP_MODIFIED){
        Group GROUP = groupRepository.findById(idGroup).orElseThrow(()-> new NullPointerException("not found"));
        if(GROUP_MODIFIED.getName() != GROUP.getName()){
            GROUP.setName(GROUP_MODIFIED.getName());
        }
        if(GROUP_MODIFIED.getRef() != GROUP.getRef()){
            GROUP.setRef(GROUP_MODIFIED.getRef());
        }
        if(GROUP_MODIFIED.getCreationDatetime() != GROUP.getCreationDatetime()){
            GROUP.setCreationDatetime(GROUP_MODIFIED.getCreationDatetime());
        }
        groupRepository.save(GROUP);
        return GROUP;
    }

    //DELETE mapping
    public String deleteGroup(Long idGroup){
        groupRepository.deleteById(idGroup);
        return "group deleted successfully";
    }
}
